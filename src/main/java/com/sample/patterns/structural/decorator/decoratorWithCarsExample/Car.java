package com.sample.patterns.structural.decorator.decoratorWithCarsExample;

public abstract class Car implements Vehicle{
    String name = "Unnamed Car";

    public String getInfo(){
        return name;
    }

    public abstract int getPrice();
}
