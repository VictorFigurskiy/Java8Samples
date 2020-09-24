package com.sample.soft_serve_training.panasonic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LogRecordParser {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().setDateFormat("yyyy-MM-dd").create();

    public static void main(String[] args) {
        String inputData = "{@BATCH|5d26423||1423|1||btest|200818031916||Agilent5|5d26423|0000|5d26423||5D26423G1303\n" +
                "{@BTEST|5D26423G130301203202930|10|200818045942|000039|0|analog||n||200818050021||002|5D261562 1\n" +
                "{@BLOCK|2%fb1|00\n" +
                "{@A-JUM|0|-5.563842E+07}\n" +
                "}\n" +
                "{@BLOCK|2%fb9|00\n" +
                "{@A-JUM|0|+3.347043E+00}\n" +
                "}\n" +
                "{@BLOCK|2%r17|00\n" +
                "{@A-JUM|0|+1.368866E+06}\n" +
                "}\n" +
                "{@BLOCK|2%r67|00\n" +
                "{@A-JUM|0|-5.701481E+06}\n" +
                "}\n" +
                "{@BLOCK|2%r77|00\n" +
                "{@A-JUM|0|+2.964413E+04}\n" +
                "}\n" +
                "{@BLOCK|2%fb2|00\n" +
                "{@A-JUM|0|+9.437868E+00}\n" +
                "}\n" +
                "{@BLOCK|2%fb3|00\n" +
                "{@A-JUM|0|+3.392322E+00}\n" +
                "}\n" +
                "{@BLOCK|2%fb4|01\n" +
                "{@A-JUM|1|-2.349295E+04}\n" +
                "}\n" +
                "{@BLOCK|2%fb5|00\n" +
                "{@A-JUM|0|+2.764369E+00}\n" +
                "}\n" +
                "{@BLOCK|2%fb7|01\n" +
                "{@A-JUM|1|-3.134484E+04}\n" +
                "}\n" +
                "{@BLOCK|2%fb8|00\n" +
                "{@A-JUM|0|+3.362644E+00}\n" +
                "}\n" +
                "{@BLOCK|2%fb15|01\n" +
                "{@A-JUM|1|-2.349295E+04}\n" +
                "}\n" +
                "{@BLOCK|2%fb16|00\n" +
                "{@A-JUM|0|+3.469503E+00}\n" +
                "}\n" +
                "{@BLOCK|2%r18|00\n" +
                "{@A-JUM|0|+3.069154E+00}\n" +
                "}\n" +
                "{@BLOCK|2%r20|00\n" +
                "{@A-JUM|0|-1.978521E+07}\n" +
                "}\n" +
                "{@BLOCK|2%r27|00\n" +
                "{@A-JUM|0|-2.919026E+07}\n" +
                "}\n" +
                "{@BLOCK|2%r38|00\n" +
                "{@A-JUM|0|+2.854547E+00}\n" +
                "}\n" +
                "{@BLOCK|2%r47|00\n" +
                "{@A-JUM|0|-2.919026E+07}\n" +
                "}\n" +
                "{@BLOCK|2%r72|00\n" +
                "{@A-JUM|0|+2.964288E+00}\n" +
                "}\n" +
                "}}\n" +
                "{@BATCH|IPM|X4|14|1||btest|191115135840||hprp5800-0b6a2d|IPM|RevA|IPM|X4|500637_01R02_Complete\n" +
                "{@BTEST|60171AI2P2-A1990000504|06|191115140108|000018|0|analog||n|n|191115140126||002|60171AI2P2-A1990000504\n" +
                "{@BLOCK|1%r28|01\n" +
                "{@A-JUM|1|-4.666946E+04}\n" +
                "}\n" +
                "}\n" +
                "{@BTEST|60171AI2P2-A1990000504|06|191115140108|000018|0|analog||n|n|191115140126||002|60171AI2P2-A1990000504\n" +
                "{@BLOCK|1%r28|01\n" +
                "{@A-JUM|1|-4.666946E+04}\n" +
                "{@A-RES|1|1.246700E+01\n" +
                "{@A-INNER|1|-4.666946E+04}\n" +
                "}\n" +
                "}\n" +
                "}}";


        System.out.println(parseIctFile(inputData));
    }

    public static String parseIctFile(String inputData){
        LogRecordParser parser = new LogRecordParser();

        List<Node> rootNodesList = parser.getNodes(inputData);

        List<ResultNode> resultNodes = new LinkedList<>();

        for (Node rootNode : rootNodesList) {
            resultNodes.add(parser.prepareJsonView(rootNode));
        }


//        String s = resultNodes.toString();
//        System.out.println(s);

        return gson.toJson(resultNodes);
    }

    private List<Node> getNodes(String inputData) {
        LinkedList<String> stringsCollection = inputData.lines().collect(Collectors.toCollection(LinkedList::new));
//        LinkedList<String> stringsCollection2 = Arrays.stream(inputData.split("\\r?\\n")).collect(Collectors.toCollection(LinkedList::new));

        List<Node> rootNodesList = new LinkedList<>();

        int nodeDepth = -1;

        Iterator<String> iterator = stringsCollection.iterator();

        return parseToTree(iterator, nodeDepth, rootNodesList);
    }

    private List<Node> parseToTree(Iterator<String> iterator, int nodeDepth, List<Node> rootNodesList) {

        Node rootNode = null;

        while (iterator.hasNext()) {

            String line = iterator.next();

            if (StringUtils.startsWith(line, "{") && StringUtils.endsWith(line, "}")) {
                nodeDepth++;

                List<String> objAndValues = Arrays.stream(StringUtils.splitByWholeSeparatorPreserveAllTokens(StringUtils.substringBetween(line, "{", "}"), "|")).collect(Collectors.toCollection(LinkedList::new));
                Node newNode = new Node(objAndValues.get(0), createCorrespondenceMap(objAndValues.get(0), objAndValues.subList(1, objAndValues.size())), nodeDepth);
                rootNode = addToNode(rootNode, newNode);

                nodeDepth--;
            } else if (StringUtils.startsWith(line, "{")) {
                nodeDepth++;

                List<String> objAndValues = Arrays.stream(StringUtils.splitByWholeSeparatorPreserveAllTokens(StringUtils.replace(line, "{", ""), "|")).collect(Collectors.toCollection(LinkedList::new));
                Node newNode = new Node(objAndValues.get(0), createCorrespondenceMap(objAndValues.get(0), objAndValues.subList(1, objAndValues.size())), nodeDepth);

                if (rootNode == null) {
                    rootNode = newNode;
                } else {
                    rootNode = addToNode(rootNode, newNode);
                }

            } else if (StringUtils.startsWith(line, "}")) {
                nodeDepth--;

                if (line.equals("}}")) {
                    nodeDepth--;
                }
            }

            if (nodeDepth == -1) {
                rootNodesList.add(rootNode);
                rootNode = null;
            }
        }
        return rootNodesList;
    }

    private Node addToNode(Node rootNode, Node innerNode) {
//=========================================================
        int depth = innerNode.getDepth();

        if (innerNode.getDepth() > 2) {
            Node tmpNode = rootNode;
            for (int i = 0; i < depth - 1; i++) {

                tmpNode = getChildList(tmpNode);

                if (i == depth - 2) {
                    tmpNode.addChild(innerNode);
                }
            }
        }

//=========================================================

        switch (innerNode.getDepth()) {
            case 0:
                rootNode = innerNode;
                break;
            case 1:
                rootNode.addChild(innerNode);
                break;
            case 2:
                rootNode.getChildList().get(rootNode.getChildList().size() - 1).addChild(innerNode);
                break;
        }

        return rootNode;
    }

    private Node getChildList(Node node) {
        return node.getChildList().get(node.getChildList().size() - 1);
    }

    private LinkedHashMap<String, String> createCorrespondenceMap(String objName, List<String> values) {

        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();

        String cleanedObjName = StringUtils.replaceEach(objName, new String[]{"@", "-"}, new String[]{"", ""});

        boolean isEnumContainsObjName = Arrays.stream(FieldNameEnum.values()).anyMatch(fileObjName -> StringUtils.equalsIgnoreCase(fileObjName.name(), cleanedObjName));

        if (StringUtils.isNotBlank(cleanedObjName) && isEnumContainsObjName) {

            List<String> keys = FieldNameEnum.valueOf(cleanedObjName).getFieldNames();

            if (keys.size() != values.size()) {

                int iterAmount = keys.size() - values.size();
                for (int i = 0; i < iterAmount; i++) {
                    values.add("");
                }
            }

            for (int i = 0; i < keys.size(); i++) {
                stringStringLinkedHashMap.put(keys.get(i), values.get(i));
            }

        } else {
            LinkedHashMap<String, String> errorMap = new LinkedHashMap<>();

            if (values.isEmpty()) {
                errorMap.put("error", "Payload is empty or null");
            } else if (objName == null) {
                errorMap.put("error", "fileTypeName is empty or null");
            } else if (!isEnumContainsObjName) {
                errorMap.put("error", "fileTypeName not present in FieldNameEnum");
            } else {
                errorMap.put("error", "unknown error");
            }

            stringStringLinkedHashMap = errorMap;
        }

        return stringStringLinkedHashMap;
    }

    private ResultNode prepareJsonView(Node mainNode) {
        ResultNode resultNode = new ResultNode(mainNode.getNodeName(), mainNode.getValues());

        if (!mainNode.getChildList().isEmpty()) {
            for (Node node : mainNode.getChildList()) {
                ResultNode innerNode = new ResultNode(node.getNodeName(), node.getValues());

                if (!node.getChildList().isEmpty()) {
                    resultNode.addSubRecordToList(prepareJsonView(node));
                } else {
                    resultNode.addSubRecordToList(innerNode);
                }
            }
        }

        return resultNode;
    }
}
