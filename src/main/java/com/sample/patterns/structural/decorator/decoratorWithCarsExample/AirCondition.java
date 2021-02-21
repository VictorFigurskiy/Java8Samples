package com.sample.patterns.structural.decorator.decoratorWithCarsExample;

public class AirCondition extends CarDecorator {

    private Vehicle car;

    public AirCondition(Vehicle car) {
        this.car = car;
    }

    @Override
    public String getInfo() {
        return car.getInfo() + " + Air Conditioning";
    }

    @Override
    public int getPrice() {
        return car.getPrice() + 1000;
    }
}
