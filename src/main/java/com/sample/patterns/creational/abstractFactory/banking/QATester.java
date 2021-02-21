package com.sample.patterns.creational.abstractFactory.banking;

import com.sample.patterns.creational.abstractFactory.Tester;

public class QATester implements Tester {
    @Override
    public void testCode() {
        System.out.println("QA tester tests banking code...");
    }
}
