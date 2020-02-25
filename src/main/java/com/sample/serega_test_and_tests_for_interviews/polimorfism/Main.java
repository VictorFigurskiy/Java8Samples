package com.sample.serega_test_and_tests_for_interviews.polimorfism;

/**
 * Created by Sonik on 01.11.2017.
 */
public class Main {

    //определить что выдаст нам код:

    public static void main(String[] args) {
        ClassB ob3 = new ClassB();
        ClassA ob1 = new ClassA();

        ob3.publicValue = 4;
        ob3.setSuperV(1);

        ClassA ob2 = ob3;
        ob1.publicValue = 2;

        ob1.showV(); // this is classA, value=2
        ob2.showV(); // this is classA, value=1
        ob3.showV(); // this is classA, value=1
        System.out.println(ob3.publicValue); // 4

        // Полиморфны только методы! Конструкторы не полиморфны. Поля не полиморфны.
        // Весь прикол в одинковых названиях полей, дает переопределить поле, но на любом классе родителе или наследнике полиморфно не вызвется,
        // а вызвет родительский в любом случае
    }
}
