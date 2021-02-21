package com.sample.patterns.structural.decorator.decoratorWithCarsExample;

public class GPS extends CarDecorator {

    private Vehicle car;

    public GPS(Vehicle car) {
        this.car = car;
    }

    @Override
    public String getInfo() {

        if (car.getInfo().contains("GPS")){
            return car.getInfo();
        }

        return car.getInfo() + " + GPS";
    }

    @Override
    public int getPrice() {
        if (car.getInfo().contains("GPS")){
            return car.getPrice();
        }
        return car.getPrice() + 1500;
    }
}
