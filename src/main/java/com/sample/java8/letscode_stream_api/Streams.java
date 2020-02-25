package com.sample.java8.letscode_stream_api;

import com.sample.java8.letscode_stream_api.entities.Department;
import com.sample.java8.letscode_stream_api.entities.Employee;
import com.sample.java8.letscode_stream_api.entities.Event;
import com.sample.java8.letscode_stream_api.entities.Position;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Streams {
    private List<Employee> emps = Arrays.asList(
            new Employee("Michael", "Smith", 243, 43, Position.CHEF),
            new Employee("Jane", "Smith", 523, 40, Position.MANAGER),
            new Employee("Jury", "Gagarin", 6423, 26, Position.MANAGER),
            new Employee("Jack", "London", 5543, 53, Position.WORKER),
            new Employee("Eric", "Jackson", 2534, 22, Position.WORKER),
            new Employee("Andrew", "Bosh", 3456, 44, Position.WORKER),
            new Employee("Joe", "Smith", 723, 30, Position.MANAGER),
            new Employee("Jack", "Gagarin", 7423, 35, Position.MANAGER),
            new Employee("Jane", "London", 7543, 42, Position.WORKER),
            new Employee("Mike", "Jackson", 7534, 31, Position.WORKER),
            new Employee("Jack", "Bosh", 7456, 54, Position.WORKER),
            new Employee("Mark", "Smith", 123, 41, Position.MANAGER),
            new Employee("Jane", "Gagarin", 1423, 28, Position.MANAGER),
            new Employee("Sam", "London", 1543, 52, Position.WORKER),
            new Employee("Jack", "Jackson", 1534, 27, Position.WORKER),
            new Employee("Eric", "Bosh", 1456, 32, Position.WORKER)
    );

    private List<Department> deps = Arrays.asList(
            new Department(1, 0, "Head"),
            new Department(2, 1, "West"),
            new Department(3, 1, "East"),
            new Department(4, 2, "Germany"),
            new Department(5, 2, "France"),
            new Department(6, 3, "China"),
            new Department(7, 3, "Japan")
    );

    @Test
    public void creation() throws IOException {
        Stream<String> lines = Files.lines(Paths.get("some.txt"));
        Stream<Path> list = Files.list(Paths.get("./"));
        Stream<Path> walk = Files.walk(Paths.get("./"), 3);

        IntStream intStream = IntStream.of(1, 2, 3, 4);
        DoubleStream doubleStream = DoubleStream.of(1.2, 3.4);
        IntStream range = IntStream.range(10, 100); // 10 .. 99
        IntStream intStream1 = IntStream.rangeClosed(10, 100);// 10 .. 100

        int[] ints = {1, 2, 3, 4, 5, 6};
        IntStream stream = Arrays.stream(ints);

        Stream<String> stringStream = Stream.of("1", "2", "3");
        Stream<? extends Serializable> stream1 = Stream.of(1, "2", "3");

        Stream<String> build = Stream.<String>builder()
                .add("Mike")
                .add("Joe")
                .build();

        Stream<Employee> stream2 = emps.stream();
        Stream<Employee> employeeStream = emps.parallelStream();

        Stream<Event> generate = Stream.generate(() -> new Event(UUID.randomUUID(), LocalDateTime.now(), ""));

        Stream<Integer> iterate = Stream.iterate(1950, val -> val + 3);

        Stream<String> concat = Stream.concat(stringStream, build);
    }

    @Test
    public void terminate() {
        Stream<Employee> stream = emps.stream();
        stream.count();

        emps.stream().forEach(employee -> System.out.println(employee.getAge()));
        emps.forEach(employee -> System.out.println(employee.getAge()));

        emps.stream().forEachOrdered(employee -> System.out.println(employee.getAge())); // used for a parallelStream, guarantees that order will be one by one

        emps.stream().collect(Collectors.toList());
        emps.stream().toArray();

        Map<Integer, String> collect = emps.stream().collect(Collectors.toMap(
                Employee::getId,
                emp -> String.format("%1$s %2$s", emp.getFirstName(), emp.getLastName())
        ));

        IntStream intStream = IntStream.of(100, 200, 300, 400);
        intStream.reduce((left, right) -> left + right).orElse(0);

        System.out.println(deps.stream().reduce((departmentLeft, departmentRight) -> this.reducer(departmentLeft, departmentRight))); // Using our own this.reducer() for objects to build a nodes tree

        IntStream.of(100, 200, 300, 400).average();
        IntStream.of(100, 200, 300, 400).max();
        IntStream.of(100, 200, 300, 400).min();
        IntStream.of(100, 200, 300, 400).sum();
        IntStream.of(100, 200, 300, 400).summaryStatistics();

        emps.stream().max((o1, o2) -> o1.getAge() - o2.getAge());

        emps.stream().findFirst();
        emps.stream().findAny();

        emps.stream().noneMatch(employee -> employee.getAge() > 60); // true
        emps.stream().anyMatch(employee -> employee.getPosition() == Position.CHEF); // true
        emps.stream().allMatch(employee -> employee.getAge() > 18); // true
    }

    @Test
    public void transform() {
        LongStream longStream = IntStream.of(100, 200, 300, 400).mapToLong(Long::valueOf);

        IntStream.of(100, 200, 300, 400).mapToObj(value ->
                new Event(UUID.randomUUID(), LocalDateTime.of(value,12,22,12,0), "")
        );

        IntStream.of(100, 200, 300, 400, 100, 200, 300, 400).distinct(); // 100, 200, 300, 400 remove duplicates

        Stream<Employee> employeeStream = emps.stream().filter(employee -> employee.getPosition() != Position.CHEF);

        emps.stream()
                .skip(3)
                .limit(5);

        emps.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .peek(employee -> employee.setAge(18))
                .map(employee -> String.format("%1$s %2$s", employee.getFirstName(), employee.getLastName()));

//        emps.stream().takeWhile(employee -> employee.getAge() > 30 ).forEach(System.out::println); // Java 9
//        System.out.println();
//        emps.stream().dropWhile(employee -> employee.getAge() > 30 ).forEach(System.out::println); // Java 9

        IntStream.of(100, 200, 300, 400)
                .flatMap(value -> IntStream.of(value - 50, value))
                .forEach(System.out::println);
    }

    @Test
    public void real(){
        Stream<Employee> sorted = emps.stream()
                .filter(employee ->
                        employee.getAge() <= 30 && employee.getPosition() != Position.WORKER
                )
                .sorted(Comparator.comparing(Employee::getLastName));

        print(sorted);

        Stream<Employee> sorted1 = emps.stream().filter(employee -> employee.getAge() > 40)
                .sorted(Comparator.comparing(Employee::getAge).reversed())
                .limit(4);

        print(sorted1);


        IntSummaryStatistics statistics = emps.stream()
                .mapToInt(Employee::getAge)
                .summaryStatistics();

        System.out.println(statistics);
    }

    private void print (Stream<Employee> stream){
        stream.map(employee -> String.format("%4d | %-15s %-10s age %s %s",
                        employee.getId(),
                        employee.getLastName(),
                        employee.getFirstName(),
                        employee.getAge(),
                        employee.getPosition()
                        ))
                .forEach(System.out::println);

        System.out.println();
    }

    public Department reducer(Department parent, Department child) {
        if (child.getParent() == parent.getId()) {
            parent.getChild().add(child);
        } else {
            parent.getChild().forEach(subParent -> reducer(subParent, child));
        }

        return parent;
    }
}
