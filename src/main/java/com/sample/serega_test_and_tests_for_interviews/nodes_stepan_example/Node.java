package com.sample.serega_test_and_tests_for_interviews.nodes_stepan_example;

import java.util.List;

/**
 * Created by Sonik on 03.11.2017.
 */
public class Node {
    private int value;
    private List<Node> childrens;

    public Node(int value, List<Node> childrens) {
        this.value = value;
        this.childrens = childrens;
    }

    public int getValue() {
        return value;
    }

    public List<Node> getChildrens() {
        return childrens;
    }
}
