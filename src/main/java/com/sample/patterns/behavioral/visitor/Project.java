package com.sample.patterns.behavioral.visitor;

import java.util.stream.Stream;

public class Project implements ProjectElement {

    private ProjectElement[] projectElements;

    public Project() {
        this.projectElements = new ProjectElement[]{
                new ProjectClass(),
                new Database(),
                new Test()
        };
    }

    @Override
    public void beWritten(Developer developer) {
        Stream.of(projectElements).forEach(projectElement -> projectElement.beWritten(developer));
    }
}
