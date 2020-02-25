package com.sample.java8.functional_interfaces_testing;

import java.util.function.Function;

/**
 * Created by Sonikb on 30.08.2017.
 */
public class FunctionTest {
    private static int staticOuterNum;


    public static void main(String[] args) {

        final int outerNum = 11;  // lambda can use only final variable

    //Function<T, R> - интерфейс, с помощью которого реализуется функция, получающая на ввод экземпляр класса T
        // и возвращающая на выходе экземпляр класса R(По сути конвертер).

        Function<Integer, String> function = integer -> {
            staticOuterNum = integer + 12;
            return String.valueOf(staticOuterNum);
        };
        System.out.println(function.apply(10));



        Function<Integer, String> function1 = integer -> {
            return String.valueOf(outerNum);
        };
        System.out.println(function1.apply(122));





    }
}
