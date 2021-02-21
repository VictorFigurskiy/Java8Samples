package com.sample.patterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class DeveloperFactoryFlyweight {

    private static final Map<String, Developer> developersMap = new HashMap<>();

    public Developer getDevelopersBySpeciality(String speciality){
        Developer developer = developersMap.get(speciality);

        if (developer == null) {
            switch (speciality) {
                case "java":
                    System.out.println("Hiring Java Developer...");
                    developer = new JavaDeveloper();
                    break;
                case "c++":
                    System.out.println("Hiring C++ Developer...");
                    developer = new CppDeveloper();
                    break;
            }
            developersMap.put(speciality, developer);
        }
        return developer;
    }
}
