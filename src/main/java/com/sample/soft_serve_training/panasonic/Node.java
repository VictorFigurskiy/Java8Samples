package com.sample.soft_serve_training.panasonic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {
    private String nodeName;
    private int depth;
    private Map<String, String> values;
    private List<Node> childList;

    public Node(String nodeName, Map<String, String> values, int depth) {
        this.nodeName = nodeName;
        this.values = values;
        this.depth = depth;
        this.childList = new LinkedList<>();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    public List<Node> getChildList() {
        return childList;
    }

    public void addChild(Node node) {
        this.childList.add(node);
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
