package com.sample.soft_serve_training.panasonic;

import java.util.Arrays;
import java.util.List;

public enum  FieldNameEnum {

    BATCH(Arrays.asList("UutType", "UutTypeRev","fixtureId", "testheadNumber", "testheadType", "processStep", "batchId", "operatorId", "controller", "testplanId", "testplanRev", "parentPanelType", "parentPanelTypeRev", "versionLabel")),
    BTEST(Arrays.asList("boardId", "testStatus", "startDatetime", "duration", "multipleTest", "logLevel", "logSet", "learning", "knownGood", "endDatetime", "statusQualifier", "boardNumber", "parentPanelId")),
    BLOCK(Arrays.asList("blockDesignator", "blockStatus")),
    AJUM(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    ARES(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    ;


    private List<String> fieldNames;

    FieldNameEnum(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }
}
