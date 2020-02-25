package com.sample.serega_test_and_tests_for_interviews;

import java.util.Arrays;

/**
 * Created by Sonik on 28.10.2017.
 */
public class AnagramTest {
    public static void main(String[] args) {

        String one = "ЛІТО";
        String two = "ТІЛО";

        char[] charsOne = one.toLowerCase().toCharArray();
        char[] charsTwo = two.toLowerCase().toCharArray();

        Arrays.sort(charsOne);
        Arrays.sort(charsTwo);

        // First variant
        boolean equals = Arrays.equals(charsOne, charsTwo);
        System.out.println(equals ? "They are equals" : "They are not equals");

        // Second
        String result = "";

        if (charsOne.length == charsTwo.length) {
            for (int i = 0; i < charsOne.length; i++) {
                if (charsOne[i] == charsTwo[i]) {
                    result = "equals";
                } else result = "Not equals";
                break;
            }
        } else result = "Не равны";

        System.out.println(result);
    }
}
