package com.sample.serega_test_and_tests_for_interviews;

/**
 * Created by Sonik on 28.10.2017.
 */
public class CountChars {
    public static void main(String[] args) {

        countCharsInLine("aaaaaaaccddsdsda");

    }

    public static void countCharsInLine(String str) {

        str.chars().distinct().forEach(value -> {
            long count = str.chars().filter(value1 -> value == value1).count();
            System.out.print(count+""+(char) value+"    ");
        });
    }
}
