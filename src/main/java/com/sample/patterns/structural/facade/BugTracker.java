package com.sample.patterns.structural.facade;

public class BugTracker {
    private boolean activeSprint;

    public boolean isActiveSprint(){
        return this.activeSprint;
    }

    public void startSprint(){
        System.out.println("Sprint is active.");
        activeSprint = Boolean.TRUE;
    }

    public void finishSprint(){
        System.out.println("Sprint is not active.");
        activeSprint = Boolean.FALSE;
    }
}
