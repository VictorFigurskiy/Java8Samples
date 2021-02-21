package com.sample.patterns.creational.abstractFactory.website;

import com.sample.patterns.creational.abstractFactory.Developer;
import com.sample.patterns.creational.abstractFactory.ProjectManager;
import com.sample.patterns.creational.abstractFactory.ProjectTeamFactory;
import com.sample.patterns.creational.abstractFactory.Tester;

public class WebsiteTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getDeveloper() {
        return new PhpDeveloper();
    }

    @Override
    public Tester getTester() {
        return new ManualTester();
    }

    @Override
    public ProjectManager getProjectManager() {
        return new WebsitePM();
    }
}
