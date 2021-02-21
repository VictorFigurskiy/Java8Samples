package com.sample.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Team implements Developer {

    private List<Developer> developers = new ArrayList<>();

    public void addDeveloper(Developer developer){
        developers.add(developer);
    }

    public void removeDeveloper(Developer developer){
        developers.remove(developer);
    }

    public void createProject(){
        System.out.println("Team creates project...");
        this.writeCode();
    }

    @Override
    public void writeCode() {
        developers.forEach(Developer::writeCode);
    }
}
