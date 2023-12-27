package com.example.dataanalyzer.model;

public enum MeasurementType {

    TEMPERATURE("TEMPERATURE"),
    VOLTAGE("VOLTAGE"),
    POWER("POWER");

    private final String value;
    MeasurementType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}