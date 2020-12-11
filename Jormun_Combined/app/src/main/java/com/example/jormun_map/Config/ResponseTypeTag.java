package com.example.jormun_map.Config;

public enum ResponseTypeTag {
    FAILED("#ERROR#", 0),
    SUCCESS("#SUCCESS#", 1);

    private final String sValue;
    private final int iValue;

    ResponseTypeTag(String sValue, int iIndex) {
        this.sValue = sValue;
        this.iValue = iIndex;
    }

    @Override
    public String toString() {
        return sValue;
    }
}
