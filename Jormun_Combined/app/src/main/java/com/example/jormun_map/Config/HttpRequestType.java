package com.example.jormun_map.Config;

public enum HttpRequestType {
    GET_REQUEST("GET", 0),
    POST_REQUEST("POST", 1),
    PATCH_REQUEST("PATCH", 2),
    INSERT_REQUEST("INSERT", 3),
    UPDATE_REQUEST("UPDATE", 4),
    DELETE_REQUEST("DELETE", 5);


    private final String sValue;
    private final int iValue;

    HttpRequestType(String sValue, int iIndex) {
        this.sValue = sValue;
        this.iValue = iIndex;
    }

    @Override
    public String toString() {
        return sValue;
    }
}
