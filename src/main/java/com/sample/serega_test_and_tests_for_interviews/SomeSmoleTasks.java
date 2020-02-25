package com.sample.serega_test_and_tests_for_interviews;

import java.util.*;

/**
 * Created by Sonik on 20.11.2017.
 */
public class SomeSmoleTasks {
    public static void main(String[] args) {
//       interestingIncrement();
//        mapTests();
//        listTests();

//       Formatter f = null;
//       TestNPE.handle(f,"string");

//        numberWorking();

//        charNumbers();


        int a = 10;
        int b = 100;
        double c = (double) (a/b);
        String str = String.format("%1.4f", c);
        System.out.println(str);

    }

    class Formatter {
        public String format(String value) {
            return "[" + value + "]";
        }
    }

    public static class TestNPE {
        public static String handle(Formatter f, String s) {
            if (s.isEmpty()) {
                return "(none)";
            }
            return f.format(s.trim());
        }
    }

    private static void charNumbers(){
        char c1 = '1';
        char c2 = '\u0031';
        char c3 = 49;
        System.out.println(c1 + c2 + c3); //Пояснение: Важно знать, что значение для переменной типа char можно задать несколькими способами: непосредственно символом, unicode-кодом этого символа, а также целым числом. Так же важно знать, что значения типа char не "склеиваются", а складываются.
    }

    private static void numberWorking() {
        int i1 = 10;
        int i2 = 10;

        Integer in1 = 10;
        Integer in2 = 10;

        Integer in3 = 129;
        Integer in4 = 129;

        Float fl1 = 10f;
        Float fl2 = 10f;

        Double d1 = 10.0;
        Double d2 = 10.0;

        char c1 = 'a';
        char c2 = 'a';

        float f1 = 0.8f;
        float f2 = 0.8f;

        String str1 = "Bar";
        String str2 = "Bar";

        System.out.println(new Float(10) / new Float(0)); // Infinity
        System.out.println(new Float(0) / new Float(0)); // NaN
//        System.out.println(15/0); // ArithmeticException: / by zero


        System.out.println(i1 == i2); //true
        System.out.println(in1 == in2); //true
        System.out.println(in3 == in4); //false
        System.out.println(fl1 == fl2); //false
        System.out.println(d1 == d2); //false
        System.out.println(c1 == c2); //true
        System.out.println(f1 == f2); //true
        System.out.println(str1 == str2); //true
    }

    private static void listTests() {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);


//        list.removeAll(list.subList(2, 5));
        list.subList(2, 5).clear();

        System.out.println(list);
    }

    public static void interestingIncrement() {
        int s = 0;
        for (int i = 0; i < 10; i++) {
            s = s + s++;
        }
        System.out.println(s);
    }

    public static void mapTests() {
        Map<Integer, String> treeMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < 12; i++) {
            treeMap.put(i, i + "");
        }

        treeMap.put(6, "MyMessage");

        System.out.println(treeMap);
    }
}
