package com.sample.patterns.creational.builder.builderWithSettersChain;

class Main {
    public static void main(String[] args) {
        GoodClass buildedObj = new GoodClass.Builder(20, 30)
                .optFieldOne(7)
                .optFieldTwo(10)
                .optFieldThird(111)
                .optFieldFour(13)
                .build();

        System.out.println(buildedObj);
    }
}
