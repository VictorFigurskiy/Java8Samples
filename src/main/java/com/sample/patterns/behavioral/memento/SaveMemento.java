package com.sample.patterns.behavioral.memento;

import java.util.Date;

public class SaveMemento {

    private final String version;
    private final Date date;

    public SaveMemento(String version, Date date) {
        this.version = version;
        this.date = date;
    }

    public String getVersion() {
        return version;
    }

    public Date getDate() {
        return date;
    }
}
