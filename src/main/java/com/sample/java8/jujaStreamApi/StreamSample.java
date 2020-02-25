package com.sample.java8.jujaStreamApi;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Sonikb on 30.08.2017.
 */
public class StreamSample {

    enum Role {
        ADMIN, USER, GUEST
    }

    static class User {
        private long id;
        private String name;
        private Role role;

        public User(long id, String name, Role role) {
            this.id = id;
            this.name = name;
            this.role = role;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Role getRole() {
            return role;
        }

        @Override
        public String toString() {
            return "id=" + id + ", name=" + name;
        }
    }

    @Test
    public void testSimpleStreamDemo_java7() {
        // пример на джава 7
        Collection<User> users = Arrays.asList(
                new User(1, "Jhon Yastreb", Role.ADMIN),
                new User(12, "Unus Tornov", Role.GUEST),
                new User(33, "Julia Coulson", Role.USER),
                new User(13, "Natasha Polyakova", Role.USER),
                new User(22, "Jimmy Jim", Role.USER));

        // filtering
        List<User> filtered = new LinkedList<>();
        for (User user : users) {
            if (user.getRole() == Role.USER)
                filtered.add(user);
        }

        // sorting
        Collections.sort(filtered, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Long.compare(o2.getId(), o1.getId());
            }
        });

        // map - функция высшего порядка, которая значит - "применить ко всем"
        List<String> names = new LinkedList<>();
        for (User user : filtered) {
            names.add(user.getName());
        }

        Assert.assertEquals("[Julia Coulson, Jimmy Jim, Natasha Polyakova]", names.toString());
    }

    @Test
    public void testSimpleStreamDemo_java8() {
        // Given
        Collection<User> users = Arrays.asList(
                new User(1, "Jhon Yastreb", Role.ADMIN),
                new User(12, "Unus Tornov", Role.GUEST),
                new User(33, "Julia Coulson", Role.USER),
                new User(13, "Natasha Polyakova", Role.USER),
                new User(22, "Jimmy Jim", Role.USER));

        List<String> names = users.stream()
                .filter(user -> user.getRole() == Role.USER)
                // промежуточные операции, ничего не делают - мы только "билдим query"
                // можно сказать они "lazy"
                .sorted((o1, o2) -> Long.compare(o2.getId(), o1.getId()))
                .map((user) -> user.getName())
                // терминальная операция - приводит к вычислениям
                .collect(Collectors.toList());
//                .collect(ArrayList::new, ArrayList::add,
//                        ArrayList::addAll);


        // еще сокращено
        List<String> names2 = users.stream()
                .filter(user -> user.getRole() == Role.USER)
                .sorted(Comparator.comparingLong(User::getId).reversed())
                .map(User::getName)
                .collect(Collectors.toList());

        Assert.assertEquals("[Julia Coulson, Jimmy Jim, Natasha Polyakova]", names.toString());
        Assert.assertEquals("[Julia Coulson, Jimmy Jim, Natasha Polyakova]", names2.toString());
    }


    @Test
    public void testFilter() {
        // Given
        Collection<User> users = Arrays.asList(
                new User(1, "USER1", Role.USER),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.USER),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));

        List<User> result = users.stream()
                // отфильтруем только четные значения
                .filter(user -> user.getId() % 2 == 0)
                .collect(Collectors.toList());

        Assert.assertEquals("[id=2, name=USER2, id=4, name=USER4]", result.toString());

    }


    static class EqualsUser extends User {


        public EqualsUser(long id, String name, Role role) {
            super(id, name, role);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            return getId() == user.getId();
        }

        @Override
        public int hashCode() {
            return (int) (getId() ^ (getId() >>> 32));
        }

    }


    // Stream<T> distinct()
    // S unordered()
    // возвращает уникальные елементы, опираясь на метод equals()
    @Test
    public void testDistinct() {
        // Given
        Collection<User> users = Arrays.asList(
                new EqualsUser(1, "USER1", Role.USER),
                new EqualsUser(2, "USER2", Role.USER),
                new EqualsUser(3, "USER3", Role.USER),
                new EqualsUser(4, "USER4", Role.USER),
                // дублирующаяся запись по айдишке относительно переопределенного equals()
                new EqualsUser(2, "USER2 UPDATED", Role.USER),
                new EqualsUser(3, "USER2 UPDATED", Role.USER));

        Collection<User> result = users.stream()
                .unordered() // если не важен порядок, то будет лучше по производительности если сначала сделать неупорядоченный
                .distinct() // удаляем дубликаты
                .collect(Collectors.toList());

        Assert.assertEquals("[id=1, name=USER1, id=2, name=USER2, id=3, name=USER3, id=4, name=USER4]", result.toString());
    }


    // Stream<T> limit(long maxSize)
    // убирает лишнее в хвосте
    @Test
    public void testLimit() {
        // Given
        Collection<User> users = Arrays.asList(
                new EqualsUser(1, "USER1", Role.USER),
                new EqualsUser(2, "USER2", Role.USER),
                new EqualsUser(3, "USER3", Role.USER),
                new EqualsUser(4, "USER4", Role.USER),
                new EqualsUser(5, "USER5", Role.USER));

        List<User> result = users.stream()
                .limit(2) // берем только первые два
                .collect(Collectors.toList());


        // Важно! limit() - short-circuiting (коротко-замкнутая) stateful intermediate operation - для бесконечного потока она может вернуть конечный поток,
        //           т.е. отрезая хвост освобождает от необходимости просчитывать предыдущие операции на всей коллекции.
        // stateful - для работы ей может потребоватся все данные, обработанные на прошлом этапе.
        // intermediate operation - она не терминальная, т.е. в ходе ее выпольнения мы получаем другой Stream, а не данные.

        Assert.assertEquals("[id=1, name=USER1, id=2, name=USER2]", result.toString());
    }


    // Stream<T> skip(long n)
    // убирает лишнее на старте
    @Test
    public void testSkip() {
        // Given
        Collection<User> users = Arrays.asList(
                new EqualsUser(1, "USER1", Role.USER),
                new EqualsUser(2, "USER2", Role.USER),
                new EqualsUser(3, "USER3", Role.USER),
                new EqualsUser(4, "USER4", Role.USER),
                new EqualsUser(5, "USER5", Role.USER));

        List<User> result = users.stream()
                .skip(2) // исключаем первых два
                .collect(Collectors.toList());


        // skip() - уже "stateful intermediate operation",
        // в отличие от лимита, которая еще и short-circuiting (коротко-замкнутая), все потому что работает
        // со всеми данными стрима, отрезая только первых несколько

        Assert.assertEquals("[id=3, name=USER3, id=4, name=USER4, id=5, name=USER5]", result.toString());
    }


    // Stream<T> sorted(Comparator<? super T> comparator)
    // сортировка, как и раньше варианта 2 с Comparator или объект реализует Comparable
    @Test
    public void testSort() {
        // Given
        Collection<User> users = Arrays.asList(
                new EqualsUser(1, "USER1", Role.USER),
                new EqualsUser(4, "USER4", Role.USER),
                new EqualsUser(3, "USER3", Role.USER),
                new EqualsUser(2, "USER2", Role.USER),
                new EqualsUser(5, "USER5", Role.USER));

        List<User> result = users.stream()
                // сортировка с компаратором
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());

        Assert.assertEquals("[id=1, name=USER1, id=2, name=USER2, id=3, name=USER3, id=4, name=USER4, id=5, name=USER5]", result.toString());
    }


    /*TODO --------------- Промежуточные и терминальные операции в Stream ----------------------- TODO */

    @Test
    public void testIntermediateAndTerminalOperations() {

        List<String> phases = new LinkedList<>();
        Collection<Integer> users = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> names = users.stream()
                // промежуточные операции, гичего не делают - мы только "билдим query"
                // можно сказать они "lazy"
                .filter(n -> {
                    phases.add("f-" + n);
                    return n % 2 == 0;
                })
                .map(n -> {
                    phases.add("m-" + n);
                    return n * n;
                })
                .sorted((n1, n2) -> {
                    phases.add("s-" + n1 + "-" + n2);
                    return Integer.compare(n2, n1);
                })
                // некоторые функции влияют на выполнение других
                .limit(2)
                // и только "терминальная операция" - приводит к вычислению
                .collect(Collectors.toList());


//        Assert.assertEquals("[4, 16, 36, 64][f-1, f-2, m-2, f-3, f-4, m-4, f-5, f-6, m-6, f-7, f-8, m-8]", names.toString() + phases.toString());


        // Если расскоментировать .limit(2), получим
        // Случилось это из-за того, что .limit - short-circuiting операция
        // результатом ее выполнения может быть меньшая коллекция
        // а значит нет смысла выполнять все предварительные вычисления
//        Assert.assertEquals("[4, 16][f-1, f-2, m-2, f-3, f-4, m-4]", names.toString() + phases.toString());


        // Добавляем sorted() получаем
        // обрати внимание что идет фильтрация и меппинг всех елементов снова
        // это случилось из за того, что sorting - это stateful операция!
        Assert.assertEquals("[64, 36][f-1, f-2, m-2, f-3, f-4, m-4, f-5, f-6, m-6, f-7, f-8, m-8, s-16-4, s-36-16, s-64-36]", names.toString() + phases.toString());


        // Если теперь заменить users.stream() на users.parallelStream(), то угадать исход вычислений мы не сможем -
        // они юудут выполнятся паралельно на всех ядрах процессора
    }


    /*TODO -------------------------- Перебор элементов в Stream ----------------------- TODO */

    // Stream<T> peek(Consumer<? super T> action)
    // своеобразный "сачок", который мы подставляем в промежуточные вычисления,
    // для того, чтобы скормить все данные на этот момент в Consumer
    @Test
    public void testPeek() {

        Collection<User> users = Arrays.asList(
                new EqualsUser(1, "USER1", Role.USER),
                new EqualsUser(4, "USER4", Role.USER),
                new EqualsUser(3, "USER3", Role.USER),
                new EqualsUser(2, "USER2", Role.USER),
                new EqualsUser(5, "USER5", Role.USER));

        List<User> sorted = new LinkedList<>();

        // when
        List<String> names = users.stream()
                // посортировали в обратном порядкепо айди
                .sorted(Comparator.comparing(User::getId).reversed())
                // и перегонякм юзеров паралельно в другой список
                // c помощью Consumer(принимает один параметр - ничего не возвращает, чаще используется для вывода чего то через sout)
                // В основном предназначен для дебага, чтобы посмотреть данные в определенном месте
                .peek(user -> sorted.add(user))
                // а дальше мы берем только их имена
                .map(User::getName)
                // собираем этот список
                .collect(Collectors.toList());

        Assert.assertEquals("[USER5, USER4, USER3, USER2, USER1]", names.toString());
        Assert.assertEquals("[id=5, name=USER5, id=4, name=USER4, id=3, name=USER3, id=2, name=USER2, id=1, name=USER1]", sorted.toString());

    }


    // void forEach(Consumer<? super T> action)
    // Терминальная операция, результатом выполнения которой будет перенаправление всего потока на конкретный Consumer,
    // который принимает решение что делать с данными дальше.
    // Функция не гарантирует порядка обработки элементов - не стоит на это полагатся.
    @Test
    public void testForEach() {

        Collection<User> users = Arrays.asList(
                new EqualsUser(1, "USER1", Role.USER),
                new EqualsUser(4, "USER4", Role.USER),
                new EqualsUser(3, "USER3", Role.USER),
                new EqualsUser(2, "USER2", Role.USER),
                new EqualsUser(5, "USER5", Role.USER));

        List<String> names = new LinkedList<>();

        users.stream()
                .map(User::getName)
                // после всех операций отправляем все данные на Consumer
                // Функция терминальная
                .forEach(name -> names.add(name));

        Assert.assertEquals("[USER1, USER4, USER3, USER2, USER5]", names.toString());

    }


    // void forEachOrdered(Consumer<? super T> action)
    // Такой же терминальный как forEach, но в отличие от нее
    // обрабатывает элементы в порядке потока(если этот порядок есть)
    // жертвуя при этом эффективностью паралелизма.
    @Test
    public void testForEachOrdered() {

        List<User> users = Arrays.asList(
                new EqualsUser(1, "USER1", Role.USER),
                new EqualsUser(2, "USER2", Role.USER),
                new EqualsUser(3, "USER3", Role.USER),
                new EqualsUser(4, "USER4", Role.USER),
                new EqualsUser(5, "USER5", Role.USER));

        List<String> names = new LinkedList<>();

        users.stream()
                .map(User::getName)
                // после всех операций отправляем все данные на Consumer
                // Функция терминальная
                .forEachOrdered(names::add);

        Assert.assertEquals("[USER1, USER2, USER3, USER4, USER5]", names.toString());

    }


    // R Stream<R> map(Function<? super T, ? extends R> mapper)
    // Функция высшего порядка, которая значит "Применить ко всем"
    // результатом выполнения будет другой стрим длинной в N элементов.
    @Test
    public void testMap() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));

        List<String> names = users.stream()
                .map(User::getName)
                .collect(Collectors.toList());


        Assert.assertEquals("[USER1, USER2, USER3, USER4, USER5]", names.toString());

    }


    // LongStream mapToLong(ToLongFunction<? super T> mapper);
    //
    // для продолжения работы как LongStream
    @Test
    public void testMapToLong() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));

        long[] ids = users.stream()
                .sorted(Comparator.comparing(User::getRole))
                // преобразовываем в айди, преобразователем будет ToLongFunction
                .mapToLong(User::getId)
                // далее мы получаем LongStream
                // поток, который работает несколько иначе чем [Object]Stream
                .toArray();


        Assert.assertEquals("[1, 3, 2, 4, 5]", Arrays.toString(ids));

    }


    // LongStream mapToLong(ToLongFunction<? super T> mapper);
    //
    // для продолжения работы как LongStream
    @Test
    public void testMapToInt_sum_average_summaryStatistics() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));

        Assert.assertEquals(15, users.stream()
                .mapToInt(user -> (int) user.getId())
                .sum());

        Assert.assertEquals("OptionalDouble[3.0]", users.stream()
                .mapToInt(user -> (int) user.getId())
                // Среднее арифметическое
                // результатом будет OptionalDouble
                .average().toString());

        Assert.assertEquals("IntSummaryStatistics{count=5, sum=15, min=1, average=3,000000, max=5}", users.stream()
                .mapToInt(user -> (int) user.getId())
                // можно достать все в одном объекте
                .summaryStatistics().toString());
    }


    // <U> Stream<U> mapToObj(IntFunction<? extends U> mapper);
    //
    // нужен для преобразования числового ряда в объектный
    @Test
    public void testMapToInt_mapToObject() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));

        List<User> result = users.stream()
                .mapToInt(user -> (int) user.getId())
                // получаем IntStream
                // и делаем обратное
                .mapToObj(id -> new User(id, "USER " + id, users.get(id - 1).getRole()))
                .collect(Collectors.toList());

        Assert.assertEquals("[id=1, name=USER 1, id=2, name=USER 2, id=3, name=USER 3, id=4, name=USER 4, id=5, name=USER 5]", result.toString());
    }


    // <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
    //
    // превращает стрим оъектов (длинной в N) в стрим других объектов (длинной >> N),
    // элементы которого строятся фенкцией обрабатывающей исходные обьекты.
    // Другими словами мы превращаем дерево в проскую структуру.
    @Test
    public void testFlatMap() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));

        List<String> data = users.stream()
                .sorted(Comparator.comparing(User::getRole))
                // Развараивает список из списка.
                // Разворачивает дерево в некую плоскую структуру
                // для этого нам нужна Function принимающая объект "стрима"
                // и возвращающая "стрим" результатов
                // flatMap объеденит все эти stream в один результирующий
                .flatMap(user -> Stream.of(  // в данном случае схитрили и создали стрим стрингов из обьектов
                        "id: " + user.getId(),
                        "role: " + user.getRole(),
                        "name:" + user.getName()))
                .collect(Collectors.toList());

        Assert.assertEquals("[id: 1, role: ADMIN, name:USER1, " +
                "id: 3, role: ADMIN, name:USER3, " +
                "id: 2, role: USER, name:USER2, " +
                "id: 4, role: USER, name:USER4, " +
                "id: 5, role: USER, name:USER5]", data.toString());
    }


    // <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
    //
    // превращает стрим оъектов (длинной в N) в стрим других объектов (длинной >> N),
    // элементы которого строятся фенкцией обрабатывающей исходные обьекты.
    // Другими словами мы превращаем дерево в проскую структуру.
    @Test
    public void testFlatMapToInt() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));

        List<Integer> data = users.stream()
                .sorted(Comparator.comparing(User::getRole))
                // flatMapToInt объеденит все эти stream в один результирующий IntStream
                .flatMapToInt(user -> IntStream.of(
                        (int) user.getId(),
                        user.getRole().name().length(),
                        user.getName().length()))
                .collect(() -> new LinkedList<Integer>(),       //    <R> R collect(Supplier<R> supplier,   - Supplier порождает контейнер куда нужно что-то складывать
                        (integers, e) -> integers.add(e),       //                  ObjIntConsumer<R> accumulator,  - ObjIntConsumer - accumulator с помощью которого будут добавлятся данные в Supplier
                        (integers, c) -> integers.addAll(c));    //                  BiConsumer<R, R> combiner);   - BiConsumer - BiConsumer делает addAll() мержит результаты, то есть добавляет все из ObjIntConsumer в контейнер Supplier

        Assert.assertEquals("[1, 5, 5, 3, 5, 5, 2, 4, 5, 4, 4, 5, 5, 4, 5]", data.toString());
    }


    // <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
    //
    // превращает стрим оъектов (длинной в N) в стрим других объектов (длинной >> N),
    // элементы которого строятся фенкцией обрабатывающей исходные обьекты.
    // Другими словами мы превращаем дерево в проскую структуру.
    @Test
    public void testToArrayWithIntFunction() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));


        String[] sorted = users.stream()
                .sorted(Comparator.comparing(User::getRole))
                .map(User::toString)
                // Для получения массива
                .toArray(length -> new String[length]);

        Assert.assertEquals("[id=1, name=USER1, id=3, name=USER3, id=2, name=USER2, id=4, name=USER4, id=5, name=USER5]", Arrays.toString(sorted));
    }


    //     <R> R collect(Supplier<R> supplier,  - Supplier порождает контейнер куда нужно что-то складывать
    //                   ObjIntConsumer<R> accumulator,  - ObjIntConsumer - accumulator с помощью которого будут добавлятся данные в Supplier
    //                   BiConsumer<R, R> combiner);  - BiConsumer - BiConsumer делает addAll() мержит результаты, то есть добавляет все из ObjIntConsumer в контейнер Supplier
    //
    // Функция позволяет собрать элементы "стрима" в любой контейнер
    @Test
    public void testCollect() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));


        List<String> result = users.stream()
                .sorted(Comparator.comparing(User::getRole))
                .map((user) -> user.toString())
                // Для получения массива
                .collect(() -> new LinkedList<String>(),
                        (container, name) -> container.add(name),
                        (container, all) -> container.addAll(all));
        // или так
//                .collect((Supplier<LinkedList<String>>) LinkedList::new,      //  LinkedList<String>::new
//                        LinkedList::add,
//                        LinkedList::addAll);

        Assert.assertEquals("[id=1, name=USER1, id=3, name=USER3, id=2, name=USER2, id=4, name=USER4, id=5, name=USER5]", result.toString());
    }


    //  <R, A> R collect(Collector<? super T, A, R> collector);
    //
    // Функция позволяет собрать элементы "стрима" в любой контейнер
    // Самая общая реализация - DIY
    @Test
    public void testCollectWithCollector_withFinisher() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));


        Set<String> result = users.stream()
                .sorted(Comparator.comparing(User::getRole))
                .map(User::toString)
                // Для получения массива
                .collect(Collector.of(
                        // Этот Supplier создает контейнер
                        LinkedList<String>::new, // это по сути рабочая колекция
                        // этот BiConsumer (Accumulator) добавляет один элемент в контейнер
                        List::add,
                        // этот BinaryOperator (Combiner) добавляет один элемент в контейнер
                        (container, sub) -> {
                            container.addAll(sub);
                            return container;
                        },
                        // эта Function перепаковывает рабочий контейнер в результат
                        (container) -> new LinkedHashSet<String>() {{ // а эта колекция уже финальная
                            addAll(container);
                        }},
                        // далее характеристики (их можно использовать несколько)
                        // http://www.izebit.ru/2016/01/collect.html
                        // говорит, что коллектор многопоточнобезопасный
                        // безопасно дольжно быть в combiner
                        Collector.Characteristics.CONCURRENT,

                        // Говорит что функция finisher может быть пропущена,
                        // перед получением итогового результата
                        // но так как мы указали finisher Function то нам он не нужен
//                        Collector.Characteristics.IDENTITY_FINISH,

                        // говорит, что если на входе поток был упорядоченный,
                        // то на выходе он не будет таковым.
                        Collector.Characteristics.UNORDERED
                ));


        Assert.assertEquals("[id=1, name=USER1, id=3, name=USER3, id=2, name=USER2, id=4, name=USER4, id=5, name=USER5]", result.toString());
    }


    //  <R, A> R collect(Collector<? super T, A, R> collector);
    //
    // public static <T, K, U>
    // Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper,
    //                                  Function<? super T, ? extends U> valueMapper) {
    //     return toMap(keyMapper, valueMapper, throwingMerger(), HashMap::new);
    // }
    //
    // вариант коллектора toMap
    @Test
    public void testCollectWithCollector_toMapCollector() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));


        Map<Long, String> result = users.stream()
                .sorted(Comparator.comparing(User::getRole))
                .collect(Collectors.toMap(
                        (user) -> user.getId(),
                        (user) -> user.toString()));

        Assert.assertEquals(5, result.size());
        Assert.assertEquals("id=1, name=USER1", result.get(1L));
        Assert.assertEquals("{1=id=1, name=USER1, 2=id=2, name=USER2, 3=id=3, name=USER3, 4=id=4, name=USER4, 5=id=5, name=USER5}", result.toString());
    }



    /*TODO-------------------------- Reduce Преобразования в STREAM ----------------------------TODO*/

    //  Optional<T> reduce(BinaryOperator<T> accumulator);
    //
    // преобразовывает весь поток к одному обьекту
    // результат Optional - т.е. его может не быть
    @Test
    public void testReduceOptional() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));


        Optional<Long> result = users.stream()
                .map(User::getId)
                .reduce((a, b) -> a + b);
        // или проще
        // .reduce(Long::sum)


        // ищем самое большое число в списке или наоборот при необхоимости
        Optional<Long> reduce = users.stream().distinct().map(user -> user.id).reduce((left, right) -> left > right ? left : right);

        // Implementation of reduce method to get the product of all numbers in given range.
        OptionalInt reduce1 = IntStream.range(2, 28).reduce((num1, num2) -> num1 * num2);

        int asInt = reduce1.isPresent() ? reduce1.getAsInt() : -1;

        // реализация ручками функционального интерфейса, который к примеру можно заюзать в reduce() -> OptionalInt reduce(IntBinaryOperator op);
        IntBinaryOperator a = (left, right) -> left - right;
        int i = a.applyAsInt(4, 2);


        // wiht identity - что то типа дефолтного значения + второй аргумент то же самое BiFunction() работающий как аккумулятор.
        Long reduce2 = users.stream().distinct().map(user -> user.id).reduce(0L, (left, right) -> left > right ? left : right);

        // результат Optional - т.е. его может не быть
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(15L, result.get().longValue());
    }


    //  T reduce(T identity, BinaryOperator<T> accumulator);
    //
    // преобразовывает весь поток к одному обьекту
    // и этот поток точно есть, потому что начинаем со значения identity
    @Test
    public void testReduceWithIdentity() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));


        Long result = users.stream()
                .map(User::getId)
                .reduce(300L, Long::sum); // здесь уже не Optional, потому что тип как минимум задан первым значение

        Assert.assertEquals(315L, result.longValue());
    }


    // <U> U reduce(U identity,
    //              BiFunction<U, ? super T, U> accumulator,
    //              BinaryOperator<U> combiner);
    //
    // преобразовывает весь поток к одному обьекту по несколько другому алгоритму
    // и этот объект точно есть, потому что начинаем со значения identity
    @Test
    public void testReduce() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));


        StringBuilder result = users.stream()
                .reduce(
                        new StringBuilder(),
                        (builder, user) -> builder.append(user.toString()),
                        (builder, anotherBuilder) -> builder.append(anotherBuilder.toString()));// здесь уже не Optional, потому что тип как минимум задан первым значение

        Assert.assertEquals("id=1, name=USER1id=2, name=USER2id=3, name=USER3id=4, name=USER4id=5, name=USER5", result.toString());
    }


    // <U> U reduce(U identity,
    //              BiFunction<U, ? super T, U> accumulator,
    //              BinaryOperator<U> combiner);
    //
    // преобразовывает весь поток к одному обьекту по несколько другому алгоритму
    // и этот объект точно есть, потому что начинаем со значения identity
    @Test
    public void testMin() {

        List<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));

        Optional<User> result = users.stream()
                .min(Comparator.comparing(User::getId)
                        .thenComparing(Comparator.comparing(User::getName)
                                .reversed()));

        Optional<User> result2 = users.stream()
                .max(Comparator.comparing(User::getId).reversed());

        // count()
        long count = users.stream().limit(4).count();

        // anyMatch()
        boolean isMatch = users.stream().anyMatch((user) -> user.getRole() == Role.GUEST);

        boolean isMatch2 = users.stream().anyMatch((user) -> user.getName().contains("6"));

        boolean isMatch3 = users.stream().anyMatch((user) -> user.getId() % 2 == 0);

        boolean isMatch4 = users.stream().anyMatch((user) -> user.getName().contains("USER"));

        // allMatch()
        boolean isMatch5 = users.stream().allMatch((user) -> user.getName().contains("USER"));

        boolean isMatch6 = users.stream().allMatch((user) -> user.getId() % 2 == 0);

        // noneMatch
        boolean noneMatch = users.stream().noneMatch(user -> user.getId() == 6);

        // findFirst
        Optional<User> first = users.stream().findFirst();

        // findAny
        Optional<User> any = users.stream().findAny();  // не детерминирован, то есть вернуть может не обязательно первый элемент


        Assert.assertEquals("Optional[id=1, name=USER1]", result.toString());
        Assert.assertEquals("Optional[id=1, name=USER1]", result2.toString());
        Assert.assertEquals(4, count);
        Assert.assertFalse(isMatch);
        Assert.assertFalse(isMatch2);
        Assert.assertTrue(isMatch3);
        Assert.assertTrue(isMatch4);
        Assert.assertTrue(isMatch5);
        Assert.assertFalse(isMatch6);
        Assert.assertTrue(noneMatch);
        Assert.assertEquals("Optional[id=1, name=USER1]", first.toString());
        Assert.assertEquals(true, any.toString().contains("USER"));

    }


    /*TODO------------------------------ Фабрика Stream ----------------------------------TODO*/


    // static<T> Stream<T> empty()
    //
    // А вдруг нам понадобится пустой стрим?
    @Test
    public void testEmptyStream() {

        Stream<Object> empty = Stream.empty();

        Optional<Object> result = empty.findAny();

        Assert.assertEquals("Optional.empty", result.toString());
    }


    // static<T> Stream<T> of(T... values)
    // static<T> Stream<T> of(T t)
    //
    // преобразовывает весь поток к одному обьекту по несколько другому алгоритму
    // и этот объект точно есть, потому что начинаем со значения identity
    @Test
    public void testStreamOfElements() {

        Stream<User> stream = Stream.of(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));

        Optional<User> any = stream.findAny();

        Assert.assertEquals(true, any.toString().contains("USER"));
    }


    // static<T> Builder<T> builder()
    //
    // Еще один способ собрать стрим из елементов
    @Test
    public void testStreamBuilder() {
        // builder() для стрима
        Stream<User> stream = Stream.<User>builder()
                .add(new User(1, "USER1", Role.ADMIN))
                .add(new User(2, "USER2", Role.USER))
                .add(new User(3, "USER3", Role.ADMIN))
                .add(new User(4, "USER4", Role.USER))
                .add(new User(5, "USER5", Role.USER)).build();

        Optional<User> any = stream.findAny();
        // Переганяем из стрима в массив
//        User[] users = stream.toArray(User[]::new);
        // И обратно в стрим из массива
//        Stream<User> stream1 = Arrays.stream(users);

        Assert.assertEquals(true, any.toString().contains("USER"));

    }


    // static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
    //
    // Позволяет создать бесконечный Стрим
    @Test
    public void testStreamIterate() {

        int start = 1234;

        List<Integer> result = Stream
                // Сиракузская последовательность
                //https://ru.wikipedia.org/wiki/%D0%93%D0%B8%D0%BF%D0%BE%D1%82%D0%B5%D0%B7%D0%B0_%D0%9A%D0%BE%D0%BB%D0%BB%D0%B0%D1%82%D1%86%D0%B0
                .iterate(start, n -> (n % 2 == 0) ? (n / 2) : (n * 3 + 1))
                // ограничиваем ряд
                .limit(150)
                // приводим к списку
                .collect(Collectors.toList());

        Assert.assertEquals("[1234, 617, 1852, 926, 463, 1390, 695, 2086, 1043, 3130, 1565, 4696, 2348, 1174, 587, 1762, " +
                "881, 2644, 1322, 661, 1984, 992, 496, 248, 124, 62, 31, 94, 47, 142, 71, 214, 107, 322, 161, 484, 242, 121, 364, 182, " +
                "91, 274, 137, 412, 206, 103, 310, 155, 466, 233, 700, 350, 175, 526, 263, 790, 395, 1186, 593, 1780, 890, 445, 1336, " +
                "668, 334, 167, 502, 251, 754, 377, 1132, 566, 283, 850, 425, 1276, 638, 319, 958, 479, 1438, 719, 2158, 1079, 3238, " +
                "1619, 4858, 2429, 7288, 3644, 1822, 911, 2734, 1367, 4102, 2051, 6154, 3077, 9232, 4616, 2308, 1154, 577, 1732, 866, " +
                "433, 1300, 650, 325, 976, 488, 244, 122, 61, 184, 92, 46, 23, 70, 35, 106, 53, 160, 80, 40, 20, 10, 5, 16, 8, 4, 2, 1, " +
                "4, 2, 1, 4, 2, 1, 4, 2, 1, 4, 2, 1, 4, 2, 1, 4, 2]", result.toString());
    }


    // static<T> Stream<T> generate(Supplier<T> s)
    //
    // Генерируем произвольные стрим с помощью Supplier
    @Test
    public void testStreamGenerator() {
        // builder() для стрима
        List<Integer> result = Stream
                // рендомный ряд
                .generate(() -> new Random().nextInt())
                // ограничиваем ряд
                .limit(150)
                .collect(Collectors.toList());

        Assert.assertEquals(true, result.stream().anyMatch(integer -> integer != 0));
    }


    // static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
    //
    // обединаяем стримы
    @Test
    public void testStreamConcat() {
        // builder() для стрима
        Stream<Integer> stream1 = Stream.iterate(1, integer -> integer * 2).limit(10);
        Stream<Integer> stream2 = Stream.iterate(1, integer -> integer * 3).limit(10);

        Stream<Integer> concatStream = Stream.concat(stream1, stream2);

        List<Integer> result = concatStream.collect(Collectors.toList());

        Assert.assertEquals("[1, 2, 4, 8, 16, 32, 64, 128, 256, 512, " +
                "1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683]", result.toString());
    }


    /*TODO------------------------------ Параллелизм в Stream ----------------------------------TODO*/


    // Stream<E> stream()
    // Stream<E> parallelStream()
    //
    // S parallel();
    // S sequential();
    // Для распределенного вычисления можно использовать фишки паралеллизма в stream
    @Test
    public void testParallelSequential() {
        // builder() для стрима
        Collection<User> users = Arrays.asList(
                new User(1, "USER1", Role.ADMIN),
                new User(2, "USER2", Role.USER),
                new User(3, "USER3", Role.ADMIN),
                new User(4, "USER4", Role.USER),
                new User(5, "USER5", Role.USER));

        List<String> names = new LinkedList<>();

        // изначально sequentialStream
        // но мог быть parallel
        // user.parallelStream()
        StringBuilder result = users.stream()
                // эта операция будет выполнятся последовательно
                .peek(user -> names.add(user.getName()))
                // Сделали parallelStream()
                .parallel()
                // Эта операция будет выполнятся паралельно
                .map(user -> user.getName())
                // потом снова sequential Stream
                .sequential()
                // эта операция будет выполнятся снова последовательно
                .reduce(new StringBuilder(),
                        (stringBuilder, name) -> stringBuilder.append(name + " "),
                        ((stringBuilder, stringBuilder2) -> stringBuilder.append(stringBuilder2)));


        Assert.assertEquals("USER1 USER2 USER3 USER4 USER5 ", result.toString());

        Stream<Object> stream1 = Stream.empty();

        Assert.assertFalse(stream1.isParallel());

        Stream<Object> stream2 = stream1.parallel();

        Assert.assertTrue(stream1.isParallel());

        Stream<Object> stream3 = stream2.sequential();

        Assert.assertFalse(stream1.isParallel());

    }

    /*TODO------------------------------ Optional объект в Stream ----------------------------------TODO*/


    // boolean isPresent
    // T get()
    // T orElse(T other)
    // T orElseGet(Supplier<? extends T> other)
    // <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X
    // void ifPresent(Consumer<? super T> consumer)
    //
    // можно также узнать какой стрим сейчас Parallel или Sequential

    private String memory;

    @Test
    public void testOptional() {
        // Допустим у нас есть объект Optional
        Optional<String> value = Stream.of("1", "2", "3").findFirst();

        // мы можем узнать есть ли у в нем какое-то значение
        Assert.assertEquals(true, value.isPresent());

        // мы можем также получить его
        Assert.assertEquals("1", value.get());

        // если его там нет, то можем получить вместо него default value
        Assert.assertEquals("1", value.orElse("0"));

        // ... или получить это default value в момент получения значения
        Assert.assertEquals("1", value.orElseGet(() -> "0"));  // разница с orElse() в том что можно подставить значение из метода например value.orElseGet(() -> method()));

        // ... или получить исключение в момент получения значения
        Assert.assertEquals("1", value.orElseThrow(() -> new RuntimeException()));


        // а если оно там есть, то скормить его Consumer
        this.memory = null;
        value.ifPresent(data -> this.memory = data);
        Assert.assertEquals("1", this.memory);

        // как вот тут: допустим у нас есть объект Optional
        Optional<String> empty =
                Stream.of("1", "2", "3").filter(s -> s.equals("5")).findFirst();

        // мы точно знаем что там его нет
        Assert.assertFalse(empty.isPresent());
        try {
            // если мы попытаемся его получить, то получим исключение
            Assert.assertEquals(null, empty.get());
            Assert.fail("Ждем исключение");
        } catch (NoSuchElementException e) {
            Assert.assertEquals("No value present", e.getMessage());
        }

        // default значение
        Assert.assertEquals("0", empty.orElse("0"));

        // или default value в формате lazy
        Assert.assertEquals("0", empty.orElseGet(() -> "0"));


        try {
            // или исключение
            Assert.assertEquals(null, empty.orElseThrow(() -> new RuntimeException("Something wrong")));
            Assert.fail("Ждем исключение");
        } catch (RuntimeException e) {
            Assert.assertEquals("Something wrong", e.getMessage());
        }

        // Consumer не получить ничего!
        this.memory = null;
        empty.ifPresent(data -> this.memory = data);
        Assert.assertEquals(null, this.memory);
    }


    /*TODO------------------------------ Другие вкусности в Stream ----------------------------------TODO*/

    // мы можем прочитать файл в стрим
    @Test
    public void testStreamFromFile() throws IOException {

        Stream<String> stream = Files.lines(Paths.get("src/main/resources/text.txt"));

        Assert.assertEquals("[This is training]", stream.collect(() -> new ArrayList<String>()
                , (strings, s) -> strings.add(s)
                , (strings, strings2) -> strings.addAll(strings2)).toString());

    }


    // мы можем получить строку в стрим
    @Test
    public void testStreamFromString() {

        // chars() возвращает коды символов
        IntStream stream = "Hello World".chars();

        Assert.assertEquals("[72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100]", Arrays.toString(stream.toArray()));

    }
}
