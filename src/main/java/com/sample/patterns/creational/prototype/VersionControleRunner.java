package com.sample.patterns.creational.prototype;

public class VersionControleRunner {
    public static void main(String[] args) {
        Project master = new Project(1, "SuperCode", "SourceCode sourceCode = new SourceCode();");

        System.out.println(master);

        ProjectFactory projectFactory = new ProjectFactory();
        projectFactory.setProject(master);

        Project masterClone = projectFactory.cloneProject();

        System.out.println("\n======================================\n");
        System.out.println(masterClone);
    }
}
