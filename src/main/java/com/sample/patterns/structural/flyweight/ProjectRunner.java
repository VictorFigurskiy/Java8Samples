package com.sample.patterns.structural.flyweight;

import java.util.ArrayList;

public class ProjectRunner {

    public static void main(String[] args) {
        DeveloperFactoryFlyweight developerFactoryFlyweight = new DeveloperFactoryFlyweight();

        ArrayList<Developer> developers = new ArrayList<>();

        developers.add(developerFactoryFlyweight.getDevelopersBySpeciality("java"));
        developers.add(developerFactoryFlyweight.getDevelopersBySpeciality("java"));
        developers.add(developerFactoryFlyweight.getDevelopersBySpeciality("java"));
        developers.add(developerFactoryFlyweight.getDevelopersBySpeciality("java"));
        developers.add(developerFactoryFlyweight.getDevelopersBySpeciality("java"));
        developers.add(developerFactoryFlyweight.getDevelopersBySpeciality("java"));
        developers.add(developerFactoryFlyweight.getDevelopersBySpeciality("java"));
        developers.add(developerFactoryFlyweight.getDevelopersBySpeciality("c++"));
        developers.add(developerFactoryFlyweight.getDevelopersBySpeciality("c++"));
        developers.add(developerFactoryFlyweight.getDevelopersBySpeciality("c++"));
        developers.add(developerFactoryFlyweight.getDevelopersBySpeciality("c++"));
        developers.add(developerFactoryFlyweight.getDevelopersBySpeciality("c++"));
        developers.add(developerFactoryFlyweight.getDevelopersBySpeciality("c++"));

        developers.forEach(Developer::writeCode);
    }
}
