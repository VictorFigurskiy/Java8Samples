package com.sample.patterns.creational.abstractFactory.banking;

import com.sample.patterns.creational.abstractFactory.Developer;

public class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Java developer writes banking code...");
    }
}
