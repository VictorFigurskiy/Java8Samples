package com.sample.serega_test_and_tests_for_interviews;

/**
 * Created by Sonik on 22.11.2017.
 */
public class Tenor extends Singer {
    public static String sing() {
        return "fa";
    }

    public static void main(String[] args) {
        Tenor t = new Tenor();
        Singer s = new Tenor();
        System.out.println(t.sing() + " " + s.sing());
    }
}

class Singer {
    public static String sing() {
        return "la";
    }
}
