package com.sample.java8.forEacheExamples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Sonikb on 30.08.2017.
 */
public class Java8ForEachExample {

    public static void main(String[] args) {

        // создаем какую-то коллекцию, например, List
        List<Integer> myList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) myList.add(i);

        // получаем итератор для работы в while цикле
        Iterator<Integer> it = myList.iterator();
        while (it.hasNext()) {
            Integer i = it.next();
            System.out.println(i);
        }

        // проходим коллекцию с помощью метода forEach с использованием анонимного класса
        myList.forEach(new Consumer<Integer>() {

            public void accept(Integer t) {
                System.out.println(t);
            }

        });

        //обходим с помощью реализации интерфейса Consumer
        MyConsumer action = new MyConsumer();
        myList.forEach(action);

    }

}

//Реализация интерфейса Consumer
class MyConsumer implements Consumer<Integer> {

    public void accept(Integer t) {
        System.out.println(t);
    }


}
