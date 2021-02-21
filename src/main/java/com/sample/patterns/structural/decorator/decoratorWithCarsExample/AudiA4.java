package com.sample.patterns.structural.decorator.decoratorWithCarsExample;

public class AudiA4 extends Car {

    public AudiA4() {
        name = "Audi A4";
    }

    @Override
    public int getPrice() {
        return 15_000;
    }
}
