package com.sample.serega_test_and_tests_for_interviews;

/**
 * Created by Sonik on 22.11.2017.
 */
public class labelShowTest {
    public static void main(String[] args) {
        label:for (int k = 1; k <= 10; k++) {
            if (k % 5 == 0) break label;
            System.out.print(k + " ");
        }

        label:
        {
            System.out.print("stop!");
        }
    }
}
