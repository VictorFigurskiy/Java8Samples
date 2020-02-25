package com.sample.serega_test_and_tests_for_interviews.playtech_examples;

/**
 * Created by Sonik on 04.11.2017.
 */
public class IntegerTest {
    public static void main(String[] args) {
//        integerComparison();
//        nullIntegerComp();
//        floatIntegerComp();
//        increamentTest();
        System.out.println(exceptionTest());
    }

    private static int exceptionTest() {
        try {
            return 1;
        } catch (Exception e) {     //you    //me
        } finally {                 // 2       2
            return 2;
        }
    }

    private static void increamentTest() {
        int i = 5;
        int x = i++ + ++i;
        System.out.println(x); // 11   // right 12

//        int y = ++i++;     // compilation error
//        System.out.println(y);

    }

    private static void floatIntegerComp() {
        int i1 = 1;
        float f1 = i1;
        Integer i2 = 100;
//        Float f2 = i2;        //you    //me
        System.out.println(f1); //1.0      1.0
//        System.out.println(f2);
    }

    private static void nullIntegerComp() {
        Integer i1 = null;
        int i2 = 0;                 //you      //me   //right
        System.out.println(i1 == i2); //false     false   NullPointerException
    }

    private static void integerComparison() {
        Integer i1 = 25;
        Integer i2 = 25;
        Integer i3 = 1000;
        Integer i4 = 1000;
        int i5 = 25;
        int i6 = 1000;
        //you      //me   //right
        System.out.println(i1 == i2);  //  true       true     true
        System.out.println(i3 == i4);  //  false      true     false
        System.out.println(i1 == i5);  //  true       true     true
        System.out.println(i3 == i6);  //  false      true     true
    }
}
