package com.sample.patterns.creational.prototype;

public class ProjectFactory {

    private Project project;

    public ProjectFactory() {
    }

    public void setProject(Project project) {
        this.project = project;
    }

    Project cloneProject(){
        return (Project) project.copy();
    }
}
