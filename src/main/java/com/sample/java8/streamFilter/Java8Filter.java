package com.sample.java8.streamFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Sonikb on 30.08.2017.
 */
public class Java8Filter {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("php");
        list.add("python");
        list.add("lisp");
        list.add("c++");

        //filter function
        Stream<String> stream = list.stream().filter(p -> p.length() > 3);
        String[] arr = stream.toArray(String[]::new);

        System.out.println(Arrays.toString(arr));


        list.stream().filter(x -> x.charAt(0) == 'p').collect(Collectors.toList()).forEach(System.out::println);
    }
}
/*
    String[] stringArr = { "a", "b", "c", "d" };
    Stream<String> stream = Stream.of(stringArr);
    String[] arr = stream.toArray(size -> new String[size]);
    System.out.println(Arrays.toString(arr));
*/