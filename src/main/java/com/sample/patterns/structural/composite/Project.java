package com.sample.patterns.structural.composite;

public class Project {
    public static void main(String[] args) {
        Team mainProjectTeam = new Team();

        Developer firsJavaDeveloper = new JavaDeveloper();
        Developer secondJavaDeveloper = new JavaDeveloper();
        Developer cppDeveloper = new CppDeveloper();

        mainProjectTeam.addDeveloper(firsJavaDeveloper);
        mainProjectTeam.addDeveloper(secondJavaDeveloper);
        mainProjectTeam.addDeveloper(cppDeveloper);


        Team anotherPartTimeTeam = new Team();

        Developer thirdJavaDeveloper = new JavaDeveloper();
        Developer secondCppDeveloper = new CppDeveloper();

        anotherPartTimeTeam.addDeveloper(thirdJavaDeveloper);
        anotherPartTimeTeam.addDeveloper(secondCppDeveloper);

        mainProjectTeam.addDeveloper(anotherPartTimeTeam);

        mainProjectTeam.createProject();
    }
}
