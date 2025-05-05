package com.example.DemoServiceSpring.Service.Enum;

public enum FarmaEnum {
    FARMATODO("farmatodo"),
    FARMABIEN("farmabien"),
    FARMACIASAS("farmaciasas"),
    LOCATEL("locatel"),
    FARMAHORRO("farmahorro");

    private final String value;

    FarmaEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
