package com.sample.patterns.behavioral.memento;

public class SaveProjectRunner {

    public static void main(String[] args) {
        Project project = new Project();

        GitHubRepo gitHub = new GitHubRepo();

        System.out.println("Creating new project. Version 1.0");
        project.setVersionAndDate("Version 1.0");

        System.out.println(project);

        System.out.println("Saving current version to GiHub...");
        gitHub.setSaveMemento(project.save());

        System.out.println("Updating project to Version 1.1");

        System.out.println("Writing poor code...");

        project.setVersionAndDate("Version 1.1");

        System.out.println(project);

        System.out.println("Something went wrong...");

        System.out.println("Rolling back to Version 1.0");
        project.load(gitHub.getSaveMemento());

        System.out.println("Project after rollback:");

        System.out.println(project);
    }
}
