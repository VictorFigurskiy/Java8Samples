package com.sample.patterns.behavioral.memento;

public class GitHubRepo {

    private SaveMemento saveMemento;

    public SaveMemento getSaveMemento() {
        return saveMemento;
    }

    public void setSaveMemento(SaveMemento saveMemento) {
        this.saveMemento = saveMemento;
    }
}
