package com.sample.patterns.behavioral.observer;

public class JobSearch {

    public static void main(String[] args) {
        JavaDeveloperJobSite jobSite = new JavaDeveloperJobSite();

        jobSite.addVacancy("First Java Position");
        jobSite.addVacancy("Second Java Position");

        Observer firstSubscriber = new Subscriber("Ivan Panin");
        Observer secondSubscriber = new Subscriber("Yuryi Durov");

        jobSite.addObserver(firstSubscriber);
        jobSite.addObserver(secondSubscriber);

        jobSite.addVacancy("Absolutely new Java Position");

        jobSite.removeVacancy("First Java Position");
    }
}
