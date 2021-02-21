package com.sample.patterns.structural.decorator.decoratorWithCarsExample;

public class Main {
    public static void main(String[] args) {
        Vehicle car1 = new AudiA3();
        System.out.println(car1.getInfo());
        System.out.println(car1.getPrice());

        car1 = new GPS(car1);
        System.out.println(car1.getInfo());
        System.out.println(car1.getPrice());

        car1 = new AirCondition(car1);
        System.out.println(car1.getInfo());
        System.out.println(car1.getPrice());

        Vehicle car2 = new AirCondition(new GPS(new AudiA4()));
        System.out.println(car2.getInfo());
        System.out.println(car2.getPrice());

        Vehicle car3 = new AllWheelDrive(new AirCondition(new GPS(new AudiA4())));
        System.out.println(car3.getInfo());
        System.out.println(car3.getPrice());

        Vehicle car4 = new GPS(new AllWheelDrive(new AirCondition(new GPS(new AudiA4())))); // Двойное добавление GPS ни к чему не приводит, так как это проверяется
        System.out.println(car4.getInfo());
        System.out.println(car4.getPrice());

    }
}
