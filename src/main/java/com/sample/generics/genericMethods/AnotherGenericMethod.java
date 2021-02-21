package com.sample.generics.genericMethods;

import java.util.Arrays;
import java.util.List;

public class AnotherGenericMethod {

    public static class Util {

        public static <T> T getValue(Object obj, Class<T> clazz) {
            return (T) obj;
        }

        public static <T> T getValue(Object obj) {
            return (T) obj;
        }
    }

    public static void main(String []args) {
        List list = Arrays.asList("Author", "Book");
        for (Object element : list) {
            String data = Util.getValue(element, String.class);
            System.out.println(data);
            System.out.println(Util.<String>getValue(element));
        }
    }
}

// Если посмотреть на класс Util, видим в нём два типизированных метода. Благодаря возможности выведения типов мы можем
// предоставить определение типа непосредственно компилятору, а можем сами это указать. Оба варианта представлены в примере.
//
//Кстати, синтаксис весьма логичен, если подумать. При типизировании метода мы указываем дженерик ДО метода, потому что
// если мы будем использовать дженерик после метода, Java не сможет понять, какой тип использовать. Поэтому сначала объявляем,
// что будем использовать дженерик T, а потом уже говорим, что этот дженерик мы собираемся возвращать.
//
//Естественно, Util.<Integer>getValue(element, String.class) упадёт с ошибкой incompatible types: Class<String> cannot be converted to Class<Integer>.