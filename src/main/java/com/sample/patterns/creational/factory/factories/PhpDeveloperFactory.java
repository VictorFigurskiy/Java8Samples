package com.sample.patterns.creational.factory.factories;

import com.sample.patterns.creational.factory.factoryInstances.Developer;
import com.sample.patterns.creational.factory.factoryInstances.PhpDeveloper;

public class PhpDeveloperFactory implements DeveloperFactory{
    @Override
    public Developer createDeveloper() {
        return new PhpDeveloper();
    }
}
