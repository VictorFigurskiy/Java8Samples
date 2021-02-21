package com.sample.patterns.structural.decorator.decoratorWithDevExample;

public class Task {

    public static void main(String[] args) {

        System.out.println("Team are working...");

        Developer developer = new JavaDeveloper();

        Developer seniorDeveloper = new SeniorJavaDeveloper(new JavaDeveloper());

        Developer teamLeadDeveloper = new JavaTeamLead(new SeniorJavaDeveloper(new JavaDeveloper()));

        System.out.println("Developer duties: " + developer.makeJob());
        System.out.println("Senior duties: " + seniorDeveloper.makeJob());
        System.out.println("Team Lead duties: " + teamLeadDeveloper.makeJob());
    }
}
