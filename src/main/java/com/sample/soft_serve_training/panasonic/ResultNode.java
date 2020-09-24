package com.sample.soft_serve_training.panasonic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.apache.commons.lang3.StringUtils;

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

//    @Override
//    public String toString() {
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().setDateFormat("yyyy-MM-dd").create();
//
//        StringBuilder keyValuesJson = new StringBuilder();
//        for (Map.Entry<String, String> entry : fields.entrySet()) {
//            keyValuesJson.append("   \"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",\n");
//        }
//
//        StringBuilder innerNodeJson = new StringBuilder();
//        for (ResultNode subRecord : subRecords) {
//            innerNodeJson.append(subRecord.toString());
//        }
//
//
//        String json;
//        if (subRecords.isEmpty()){
//            json = "{\n" +
//                    "   \"Name\":\"" + record + "\",\n" +
//                    keyValuesJson.toString() +
//                    "},";
//        } else {
//            if (subRecords.size() == 1) {
//                json = "{\n" +
//                        "   \"Name\":\"" + record + "\",\n" +
//                        keyValuesJson.toString() +
//                        "   \"subRecords\":[\n" +
//                        innerNodeJson +
//                        "   ]\n" +
//                        "},";
//            } else {
//                json = "{\n" +
//                        "   \"Name\":\"" + record + "\",\n" +
//                        keyValuesJson.toString() +
//                        "   \"subRecords\":[\n" +
//                        innerNodeJson +
//                        "   ]\n" +
//                        "}";
//            }
//        }
//
//
//        return json;
//    }
}
