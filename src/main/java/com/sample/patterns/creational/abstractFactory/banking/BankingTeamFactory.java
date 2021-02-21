package com.sample.patterns.creational.abstractFactory.banking;

import com.sample.patterns.creational.abstractFactory.Developer;
import com.sample.patterns.creational.abstractFactory.ProjectManager;
import com.sample.patterns.creational.abstractFactory.ProjectTeamFactory;
import com.sample.patterns.creational.abstractFactory.Tester;

public class BankingTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getDeveloper() {
        return new JavaDeveloper();
    }

    @Override
    public Tester getTester() {
        return new QATester();
    }

    @Override
    public ProjectManager getProjectManager() {
        return new BankingPM();
    }
}
