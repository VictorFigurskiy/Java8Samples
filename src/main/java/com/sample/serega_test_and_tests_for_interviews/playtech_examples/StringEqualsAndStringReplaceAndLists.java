package com.sample.serega_test_and_tests_for_interviews.playtech_examples;

import java.util.*;

/**
 * Created by Sonik on 04.11.2017.
 */
public class StringEqualsAndStringReplaceAndLists {
    public static void main(String[] args) {
//        stringTableEquals();
//        stringReplace();
        whatInlist();

    }

    private static void whatInlist() {
        List list = new ArrayList();
        List<String> listOfStrings = new ArrayList<>();

        //1
        list.add(new Integer(1));
        //2
//        listOfStrings.add(new Integer(1));
        //3
        list = listOfStrings;
        list.add(new Integer(2));
        System.out.println(list.get(0));          // 2
        System.out.println(listOfStrings.get(0)); // throw ClassCastException

    }

    private static void stringReplace() {
        String string = "abcxyz";
        string.replace("a", "1");
        System.out.println(string);  // me abcxyz   you abcxyz
    }

    private static void stringTableEquals() {
        String string1 = "str";
        String string2 = "str";

        String string3 = new String("str");
        String string4 = new String("str");

        System.out.println(string1 == string2); // me true   you true
        System.out.println(string3 == string4); //    false      false
        System.out.println(string2 == string3); //    false      false
    }
}
