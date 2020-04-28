package com.sample.serega_test_and_tests_for_interviews;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JupiterTest {

    //Нужно найти все Pair, second которых делится на 3 и сргупировать их по first что бы получилось Map<String, List<Pair<String, Integer>>>
    //generatedData
    //На выходе нужно Map<String, List<Pair<String, Integer>>>
    //типа фильтрануть все Pair, second которых делится на 3
    //а потом сргупировать по first в мапу
    //Map<String, List<Pair<String, Integer>>> вот это на выходе

    public static void main(String[] args) {
        List<List<Pair<String, Integer>>> generatedData = generate();

        LinkedHashMap<String, List<Pair<String, Integer>>> resultMap = new LinkedHashMap<>();

        generatedData.stream()
                .map(pairsList -> pairsList.stream()
                        .filter(stringIntegerPair -> stringIntegerPair.getSecond() % 3 == 0)
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream).forEach(stringIntegerPair -> {

            if (resultMap.containsKey(stringIntegerPair.getFirst())) {

                List<Pair<String, Integer>> pairs = resultMap.get(stringIntegerPair.getFirst());
                pairs.add(stringIntegerPair);

                resultMap.put(stringIntegerPair.getFirst(), pairs);

            } else {
                List<Pair<String, Integer>> newPairsList = new ArrayList<>();
                newPairsList.add(stringIntegerPair);

                resultMap.put(stringIntegerPair.getFirst(), newPairsList);
            }

        });

        Map<String, List<Pair<String, Integer>>> collect = generatedData.stream()
                .map(pairsList -> pairsList.stream()
                        .filter(stringIntegerPair -> stringIntegerPair.getSecond() % 3 == 0)
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Pair::getFirst, LinkedHashMap::new, Collectors.toList()));

        Map<String, List<Pair<String, Integer>>> collect2 = generatedData.stream()
                .flatMap(Collection::stream)
                .filter(stringIntegerPair -> stringIntegerPair.getSecond() % 3 == 0)
                .collect(Collectors.groupingBy(Pair::getFirst, LinkedHashMap::new, Collectors.toList()));

        System.out.println(resultMap);
        System.out.println(collect2);

    }

    private static List<List<Pair<String, Integer>>> generate() {
        List<List<Pair<String, Integer>>> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            List<Pair<String, Integer>> list = new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                list.add(Pair.of(String.valueOf(i), j));
            }
            result.add(list);
        }
        return result;
    }

    public static class Pair<T, K> {
        T first;
        K second;

        public static <T, K> Pair<T, K> of(T t, K k) {
            return new Pair<>(t, k);
        }

        public Pair(T first, K second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public void setFirst(T first) {
            this.first = first;
        }

        public K getSecond() {
            return second;
        }

        public void setSecond(K second) {
            this.second = second;
        }
    }

}
