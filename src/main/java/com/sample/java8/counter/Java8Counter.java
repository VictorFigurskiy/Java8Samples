package com.sample.java8.counter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Sonikb on 30.08.2017.
 */
public class Java8Counter {
    public static void main(String[] args) {
        String[] arr = { "program", "creek", "program", "creek", "java", "web",
                "program" };
        Stream<String> stream = Stream.of(arr).parallel();
        Map<String, Long> counter = stream.collect(Collectors.groupingBy((s) -> s.toString(), Collectors.counting()));
        System.out.println(counter.get("creek"));
    }
}
/*
        Ссылка и соответствующее лямбда-выражение
        String::valueOf       x -> String.valueOf(x)
        Object::toString      x -> x.toString()
        x::toString           () -> x.toString()
        ArrayList::new        () -> new ArrayList<>()

        java.util.function
        Еще одно нововведение JDK - множество функциональных интерфейсов, которые будут весьма полезны. Рассмотрим некоторые из них:
        Function<T, R> - интерфейс, с помощью которого реализуется функция, получающая на ввод экземпляр класса T и возвращающая на выходе экземпляр класса R. Далее не настолько подробно.
        Predicate<T> - на ввод - T, возвращает результат типа boolean.
        Consumer<T> - на ввод - T, производит некое действие и ничего не возвращает.
        Supplier<T> - ничего не принимает на ввод, возвращает T
        BinaryOperator<T> - на ввод - два экземпляра T, возвращает один T

        Пакет также снабжен примитивными реализациями данных интерфейсов для типов int, long и double.


*/