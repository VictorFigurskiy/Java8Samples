package com.sample.generics;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericTest {
    public static void main(String[] args) {

        List<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);

        List<? extends Number> nums = new ArrayList<>();
        nums.add(null);
//        nums.add(3.14); // compile-time error
        // Если контейнер объявлен с wildcard ? extends, то можно только читать значения.
        // В список нельзя ничего добавить, кроме null.
        // Для того чтобы добавить объект в список нам нужен другой тип wildcard — ? super


        Number someDouble = 12d;

        List<? super Double> nums2 = new ArrayList<>();
//        nums2.add(someDouble); // compile-time error
        nums2.add(3.14D);
        nums2.add(12D);

        nums2.forEach(System.out::println);



        List<Number> ints5 = new ArrayList<>();
        ints5.add(1);
        List<? super Double> nums5 = ints5;
        nums5.add(12D);
        Object object = nums5.get(0);



        Object firstSuper = getFirstSuper(nums2);
        Number firstExtends = getFirstExtends(nums);

        System.out.println(firstSuper);
        System.out.println(firstExtends);


        Double ceilValue = ceilValue(3.14D);
        System.out.println(ceilValue);


        try {
//            Double multiplyOn2Value = multiplyValue("Hello");
            Double multiplyOn2Value = multiplyValue(3.321554);
            System.out.println(multiplyOn2Value);
        } catch (RuntimeException e){
            e.printStackTrace();
        }

        List<Integer> integersCopy = copyListReferenceOnly(ints, new ArrayList<>());
        System.out.println(integersCopy);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("first");
        strings.add("second");

        List<String> stringsCopy = copyListReferenceOnly(strings, new ArrayList<>());
        System.out.println(stringsCopy);

        try {
            List<String> stringsCopyFull = copyListWithObjectsFullCopy(stringsCopy, new ArrayList<>());
            System.out.println(stringsCopyFull);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

//    public static <T> T getFirst(List<? super T> list) {
//        return list.get(0); // compile-time error
//    }


    public static <T> T getFirstSuper(List<? super T> list) {
        return (T) list.get(0);
    }


    public static <T> T getFirstExtends(List<? extends T> list) {
        return list.get(0);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T ceilValue(T value) {
        return (T) Double.valueOf(Math.ceil(value.doubleValue()));
    }


    public static <T> Double multiplyValue(T value) throws RuntimeException {
        if (value instanceof Number){
            return ((Number) value).doubleValue() * 2;
        } else {
            throw new RuntimeException("Value is not a number type");
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> copyListReferenceOnly(List<? extends T> fromCopy, List<? super T> toCopy){
//        Collections.copy();
//        List.copyOf();
        toCopy.addAll(fromCopy); // скопировал только ссылки
        return (List<T>) toCopy;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> copyListWithObjectsFullCopy(List<? extends T> fromCopy, List<? super T> toCopy) throws IOException, ClassNotFoundException {
        // Вариант полного копирования обекта через сериализацию (еще варик через интерфейс Cloneable, но там его нужно добавить клониуемому кклассу)
        for (T obj : fromCopy) {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream ous = new ObjectOutputStream(baos);

            //сохраняем состояние obj в поток и закрываем его(поток)
            ous.writeObject(obj);
            ous.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);

            //создаём новый objCopy для опытов и инициализируем его состояние из obj
            T objCopy = (T)ois.readObject();

            toCopy.add(objCopy);
        }

        return (List<T>) toCopy;
    }

    public static <T> void addDouble(T d) {
        List<? super T> nums2 = new ArrayList<>();
        nums2.add(d);
    }

    public static <T> T save(List<? super T> list, T element) {
        list.add(element);
        return element;
    }
}
