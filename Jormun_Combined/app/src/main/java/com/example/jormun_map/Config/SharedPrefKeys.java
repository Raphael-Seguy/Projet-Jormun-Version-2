package com.example.jormun_map.Config;

public enum SharedPrefKeys {
    SESSION("SESSION",0),
    USERNAMEKEY("USERNAME",1),
    POSITIONKEY("POSERVKEY",2),
    POSITIONLOGIN("POSERVLOG",3);

    private String sValue;
    private int iValue;

    private SharedPrefKeys(String sValue,int iValue){
        this.iValue = iValue;
        this.sValue = sValue;
    }

    @Override
    public String toString() {
        return sValue;
    }
}
