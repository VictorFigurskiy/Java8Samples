package com.sample.patterns.creational.factory.factories;

import com.sample.patterns.creational.factory.factoryInstances.CppDeveloper;
import com.sample.patterns.creational.factory.factoryInstances.Developer;

public class CppDeveloperFactory implements DeveloperFactory{
    @Override
    public Developer createDeveloper() {
        return new CppDeveloper();
    }
}
