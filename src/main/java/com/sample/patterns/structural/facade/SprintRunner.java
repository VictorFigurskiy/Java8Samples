package com.sample.patterns.structural.facade;

public class SprintRunner {
    public static void main(String[] args) {
        WorkFlowFacade workFlowFacade = new WorkFlowFacade();

        workFlowFacade.solveProblem();
    }
}
