package com.sample.soft_serve_training;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.util.LinkedHashMap;

public class UnescapeJson {

    public static Object parseJayWayJsonAndDecodeToMap(String inputStr) {
        DocumentContext parse = JsonPath.parse(inputStr);
        return parse.json();
    }

    public static String parseJayWayJsonAndDecodeToString(String inputStr) {
        DocumentContext parse = JsonPath.parse(inputStr);
        return parse.jsonString();
    }

    public static String parseJsonWithGSONToPrettyString(String inputStr) {
        Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().setDateFormat("yyyy-MM-dd")
                .setLongSerializationPolicy(LongSerializationPolicy.STRING).setPrettyPrinting().create();

        LinkedHashMap<String, Object> linkedHashMap = gson.fromJson(inputStr, LinkedHashMap.class);

        return gson.toJson(linkedHashMap);
    }
}
