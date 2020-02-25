package com.sample.serega_test_and_tests_for_interviews;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Sonik on 27.10.2017.
 */
public class CountCharsInString {
    public static void main(String[] args) {

        String someString = "askjdaklsdjfhg";

        char[] chars = someString.toCharArray();

        char tmp;
        String result = "";


        for (int i = 0; i < chars.length; i++) {
            tmp = chars[i];
            int amount = 0;
            for (int j = 0; j < chars.length; j++) {

                if (tmp == chars[j]) {
                    amount += 1;
                }
            }
            result += tmp + "" + amount + ':';
        }

        String[] split = result.split(":");

        List<String> list = Arrays.asList(split);

        Set<String> superSet = new TreeSet<>(list);

        System.out.println(superSet);

    }
}
