package com.sample.serega_test_and_tests_for_interviews.nodes_stepan_example;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sonik on 03.11.2017.
 */
public class Main {
    public static void main(String[] args) {
        Node mainNode = getTree();

        System.out.println(findMax(mainNode));
    }

    private static int findMax(Node mainNode) {
        int maxValue = mainNode.getValue();

        if (mainNode.getChildrens() != null) {
            for (Node node : mainNode.getChildrens()) {
                if (node.getValue() > maxValue) {
                    maxValue = node.getValue();
                }
                if (node.getChildrens() != null) {
                    int numberCheck = findMax(node);
                    if (numberCheck > maxValue) {
                        maxValue = numberCheck;
                    }
                }
            }
        }
        return maxValue;
    }


    private static Node getTree() {
        List<Node> rootTree = new LinkedList<>();
        List<Node> childrenTree = new LinkedList<>();
        List<Node> subChildren = new LinkedList<>();

        Node mainNode = new Node(25, rootTree);

        rootTree.add(new Node(24, childrenTree));
        rootTree.add(new Node(20, null));

        childrenTree.add(new Node(23, null));
        childrenTree.add(new Node(21, subChildren));

        subChildren.add(new Node(22, null));


        return mainNode;
    }

}
