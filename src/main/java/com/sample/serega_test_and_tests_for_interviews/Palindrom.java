package com.sample.serega_test_and_tests_for_interviews;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Palindrom {

    public static void main(String[] args) {
        System.out.println("Only palindroms: " + returnListOnlyWithPalindromes(Arrays.asList("диороид", "потоп", "хахахах", "kljsdjgflsd", "skhjdf7e")));
    }

    public static List<String> returnListOnlyWithPalindromes(List<String> stringList){
        List<String> tmpList = new LinkedList<>();

        for (String str : stringList) {
            if (isPalindrome(str)){
                tmpList.add(str);
            }
        }

        return tmpList;
    }

    public static boolean isPalindrome(String str){
        char[] charArray = str.toLowerCase().toCharArray();
        boolean isPolindrom = false;

        for (int i = 0; i < charArray.length; i++) {
            if(charArray[i] == charArray[charArray.length - 1 - i]){
                isPolindrom = true;
            } else {
                isPolindrom = false;
            }
        }

        return isPolindrom;
    }
}
