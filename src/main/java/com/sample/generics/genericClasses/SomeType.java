package com.sample.generics.genericClasses;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SomeType<T> {

    public <E> void test(Collection<E> collection) {
        for (E element : collection) {
            System.out.println(element);
        }
    }
    public void test(List<Integer> collection) {
        for (Integer element : collection) {
            System.out.println(element);
        }
    }

    public static void main(String []args) {
        SomeType<String> st = new SomeType<>();
        List<String> list = Arrays.asList("test");
        st.test(list);
        //Он отработает хорошо. Компилятор видит, что есть List из стрингов и Collection типа String. Но что если мы сотрём дженерики и сделаем так:

//        SomeType st = new SomeType();
//        List<String> list = Arrays.asList("test");
//        st.test(list);

        // Мы получим ошибку: java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer

        // Опять стирание типов. Поскольку у класса больше нет дженерика, компилятор решает: раз мы передали List, метод с List<Integer> более подходящий. И мы падаем с ошибкой.

        // Поэтому, правило #2: Если класс типизирован, всегда указывать тип в дженерике.
    }
}
