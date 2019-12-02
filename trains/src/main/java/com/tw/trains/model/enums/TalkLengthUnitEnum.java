package com.tw.trains.model.enums;

public enum TalkLengthUnitEnum {
    MINUTES("min"), LIGHTNING("lightning");

    private String value;

    private TalkLengthUnitEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static int converToMinuts(TalkLengthUnitEnum unit) {
        switch (unit) {
            case MINUTES:
                return 1;
            case LIGHTNING:
                return 5;
            default:
                throw new IllegalArgumentException("no corresponding talkLength unit");
        }
    }
}
