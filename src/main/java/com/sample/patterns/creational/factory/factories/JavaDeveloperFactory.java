package com.sample.patterns.creational.factory.factories;

import com.sample.patterns.creational.factory.factoryInstances.Developer;
import com.sample.patterns.creational.factory.factoryInstances.JavaDeveloper;

public class JavaDeveloperFactory implements DeveloperFactory {
    @Override
    public Developer createDeveloper() {
        return new JavaDeveloper();
    }
}
