package com.sample.soft_serve_training.panasonic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ResultNode {

    private String record;
    private Map<String, String> fields;
    private List<ResultNode> subRecords;

    public ResultNode(String record, Map<String, String> fields) {
        this.record = record;
        this.fields = fields;
        this.subRecords = new LinkedList<>();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    public List<ResultNode> getSubRecords() {
        return subRecords;
    }

    public void addSubRecordToList(ResultNode childResultNode) {
        this.subRecords.add(childResultNode);
    }
}
