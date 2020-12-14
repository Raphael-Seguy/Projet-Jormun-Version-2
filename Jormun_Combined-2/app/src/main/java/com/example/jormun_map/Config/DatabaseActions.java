package com.example.jormun_map.Config;

public enum DatabaseActions {
    ACTION_CONVERIF("CheckConnection", 0),
    ACTION_REGISTER("AddUser", 1),
    ACTION_GRABSIMPLEFILE("FetchSimpleFile", 2),
    ACTION_GRABCOMPLEXFILE("FetchComplexFile", 3),
    ACTION_LOGIN("ConnectUser", 4);
    private final String sValue;
    private final int iValue;

    DatabaseActions(String sValue, int iValue) {
        this.sValue = sValue;
        this.iValue = iValue;
    }

    @Override
    public String toString() {
        return sValue;
    }
}
