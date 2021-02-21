package com.sample.patterns.behavioral.memento;

import java.util.Date;

public class Project {

    private String version;
    private Date date;

    public void setVersionAndDate(String version){
        this.version = version;
        this.date = new Date();
    }

    public SaveMemento save(){
        return new SaveMemento(this.version, this.date);
    }

    public void load(SaveMemento saveMemento){
        version = saveMemento.getVersion();
        date = saveMemento.getDate();
    }

    @Override
    public String toString() {
        return "\nProject:" +
                "\nversion: " + version +
                "\ndate: " + date + "\n";
    }
}
