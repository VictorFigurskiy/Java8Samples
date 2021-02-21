package com.sample.patterns.behavioral.observer;

public interface Observed {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
