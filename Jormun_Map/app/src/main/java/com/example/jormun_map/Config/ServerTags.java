package com.example.jormun_map.Config;

public enum ServerTags {
    DONE("DONE", 0);

    private final String sValue;
    private final int iValue;

    ServerTags(String sValue, int iIndex) {
        this.sValue = sValue;
        this.iValue = iIndex;
    }

    @Override
    public String toString() {
        return sValue;
    }
}
