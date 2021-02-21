package com.sample.generics.genericClasses;

public class GenericLimitations {
    public static class NumberContainer<T extends Number & Comparable> {
        private T number;

        public NumberContainer(T number)  {
            this.number = number;
        }

        public void print() {
            System.out.println(number);
        }
    }

    public static void main(String []args) {
        NumberContainer number1 = new NumberContainer(2L);
        NumberContainer number2 = new NumberContainer(1);
//        NumberContainer number3 = new NumberContainer("f"); // COMPILE ERROR: Error:(19, 55) java: incompatible types: java.lang.String cannot be converted to java.lang.Number
    }

}
