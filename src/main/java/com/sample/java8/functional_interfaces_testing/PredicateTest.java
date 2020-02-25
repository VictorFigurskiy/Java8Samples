package com.sample.java8.functional_interfaces_testing;

import java.util.function.Predicate;

/**
 * Created by Sonikb on 30.08.2017.
 */
public class PredicateTest {
    public static void main(String[] args) {

        //Predicate<T> - на ввод - T, возвращает результат типа boolean.

        Predicate<Integer> predicate = (integer) -> integer > 10;

        System.out.println("Greater than 10? - Answer = " + predicate.test(15));

        System.out.println("Greater than 10? - Answer is negated = " + predicate.negate().test(15));

        System.out.println("If both Predicates are true then will be true = " + predicate.and(integer -> integer < 15).test(12));

        System.out.println("Greater then 10 or less then 5 will be true = " + predicate.or(integer -> integer < 5).test(4));

        System.out.println("Is equal = " + Predicate.isEqual(12).test(12));

    }
}