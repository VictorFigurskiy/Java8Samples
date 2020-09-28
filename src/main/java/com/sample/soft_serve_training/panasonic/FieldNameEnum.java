package com.sample.soft_serve_training.panasonic;

import java.util.Arrays;
import java.util.List;

public enum  FieldNameEnum {

    BATCH(Arrays.asList("UutType", "UutTypeRev","fixtureId", "testheadNumber", "testheadType", "processStep", "batchId", "operatorId", "controller", "testplanId", "testplanRev", "parentPanelType", "parentPanelTypeRev", "versionLabel")),
    BTEST(Arrays.asList("boardId", "testStatus", "startDatetime", "duration", "multipleTest", "logLevel", "logSet", "learning", "knownGood", "endDatetime", "statusQualifier", "boardNumber", "parentPanelId")),
    BLOCK(Arrays.asList("blockDesignator", "blockStatus")),
    AJUM(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    ARES(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    ACAP(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    ADIO(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    AFUS(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    AIND(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    AMEA(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    ANFE(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    ANPN(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    APFE(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    APNP(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    APOT(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    ASWI(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    AZEN(Arrays.asList("testStatus", "measuredValue", "subtestDesignator")),
    ALM(Arrays.asList("alarmType", "alarmStatus", "datetimeDetected", "boardType", "boardTypeRev", "alarmLimit", "detectedValue", "controller", "testheadNumber")),
    AID(Arrays.asList("datetimeDetected", "subtestDesignator")),
    ARRAY(Arrays.asList("subtestDesignator", "status", "failureCount", "samples")),
    BSCON(Arrays.asList("testDesignator", "status", "shortsCount", "opensCount")),
    BSO(Arrays.asList("firstDeviceName", "firstDevicePin", "secondDeviceName", "secondDevicePin")),
    BSS(Arrays.asList("cause", "nodeList")),
    DPIN(Arrays.asList("deviceName", "nodePinList", "thruDevnodeList")),
    DPLD(Arrays.asList("Filename", "Action", "ActionReturnCode", "ResultMessageString", "PlayerProgramCounter")),
    DT(Arrays.asList("testStatus", "testSubstatus", "failingVectorNumber", "pinCount", "testDesignator")),
    INDICT(Arrays.asList("technique", "deviceList", "estResistance", "estCapacitance", "estInductance", "estModel")),
    LIM2(Arrays.asList("highLimit", "lowLimit")),
    LIM3(Arrays.asList("nominalValue", "highLimit", "lowLimit")),
    NETV(Arrays.asList("datetime", "testSystem", "repairSystem", "source")),
    NODE(Arrays.asList("nodeList")),
    PCHK(Arrays.asList("testStatus", "testDesignator")),
    PIN(Arrays.asList("pinList")),
    PF(Arrays.asList("designator", "testStatus", "totalPins")),
    PRB(Arrays.asList("testStatus", "pinCount", "testDesignator")),
    RETEST(Arrays.asList("datetime")),
    RPT(Arrays.asList("message")),
    TJET(Arrays.asList("testStatus", "pinCount", "testDesignator")),
    TS(Arrays.asList("testStatus", "shortsCount", "opensCount", "phantomsCount", "designator")),
    TSD(Arrays.asList("destinationList")),
    TSO(Arrays.asList("sourceNode", "destinationNode", "deviation")),
    TSP(Arrays.asList("deviation")),
    TSS(Arrays.asList("shortsCount", "phantomsCount", "sourceNode")),
    ;


    private List<String> fieldNames;

    FieldNameEnum(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }
}
