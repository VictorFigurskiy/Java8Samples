package com.sample.serega_test_and_tests_for_interviews;

public class SearchAndCountWords {

    public static void main(String[] args) {
        System.out.println(searchWordInStringAndCount("The quick brown fox jumps over fox the lazy dog quick. q!uick quick", "quick"));
    }

    public static int searchWordInStringAndCount(String inputString, String searchedWord) {
        char[] inputArray = inputString.toCharArray();
        char[] searchedArray = searchedWord.toCharArray();

        boolean isFound = false;
        int resultCount = 0;

        int searchedArrayLength = searchedArray.length;
        int counter = 0;

        for (int i = 0; i < inputArray.length; i++) {

            if (inputArray[i] == searchedArray[0] && counter == 0) {
                isFound = true;
                counter++;
            } else if (inputArray[i] == searchedArray[counter] && isFound) {
                counter++;
            } else {
                isFound = false;
                counter = 0;
            }

            if (counter == searchedArrayLength) {
                resultCount++;
                counter = 0;
            }
        }


        return resultCount;
    }
}
