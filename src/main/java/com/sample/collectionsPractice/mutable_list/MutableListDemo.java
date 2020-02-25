package com.sample.collectionsPractice.mutable_list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonikb on 02.09.2017.
 */
public class MutableListDemo {

    private static String staticString = "staticString";

    public static void main(String[] args) {
        List<Element> elementList = new ArrayList<>();
        final Element element = new Element("Element");
        elementList.add(element);
        System.out.println(elementList + " + HashCode: " + elementList.get(0).getValue().hashCode());
        element.setValue("New Element");
        System.out.println(elementList + " + HashCode: " + elementList.get(0).getValue().hashCode());


        System.out.println("----------------------------");

        final List<String> list = new ArrayList<>();
        list.add(staticString);
        System.out.println(list);
        staticString = "new staticString";
        list.add(staticString);
        System.out.println(list);
        System.out.println(list.get(0).hashCode() + "      " + list.get(1).hashCode());

    }

    static class Element {
        String value;

        public Element(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }
}
