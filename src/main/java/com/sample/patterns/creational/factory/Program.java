package com.sample.patterns.creational.factory;

import com.sample.patterns.creational.factory.factories.CppDeveloperFactory;
import com.sample.patterns.creational.factory.factories.DeveloperFactory;
import com.sample.patterns.creational.factory.factories.JavaDeveloperFactory;
import com.sample.patterns.creational.factory.factories.PhpDeveloperFactory;
import com.sample.patterns.creational.factory.factoryInstances.Developer;

public class Program {

    public static void main(String[] args) {
        DeveloperFactory developerFactory = createDeveloperFactoryBySpeciality("php");

        Developer developer = developerFactory.createDeveloper();

        developer.writeCode();
    }

    static DeveloperFactory createDeveloperFactoryBySpeciality(String speciality){
        if (speciality.equalsIgnoreCase("java")){
            return new JavaDeveloperFactory();
        } else if (speciality.equalsIgnoreCase("C++")){
            return new CppDeveloperFactory();
        } else if(speciality.equalsIgnoreCase("php")){
            return new PhpDeveloperFactory();
        } else {
            throw new RuntimeException(speciality + " is unknown specialty.");
        }
    }
}
