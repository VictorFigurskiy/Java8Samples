package com.sample.patterns.structural.decorator.decoratorWithCarsExample;

public class AudiA3 extends Car {

    public AudiA3() {
        this.name = "Audi A3";
    }

    @Override
    public int getPrice() {
        return 10_000;
    }
}
