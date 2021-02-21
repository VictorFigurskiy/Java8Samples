package com.sample.patterns.structural.decorator.decoratorWithCarsExample;

public class AllWheelDrive extends CarDecorator {

    private Vehicle car;

    public AllWheelDrive(Vehicle car) {
        this.car = car;
    }

    @Override
    public String getInfo() {
        return car.getInfo() + " + All Wheel Drive";
    }

    @Override
    public int getPrice() {
        return car.getPrice() + 2500;
    }
}
