package com.example.jormun_map.Config;

public enum CommunicationVariables {
    POS("psPos=",0),
    BOUNDS("psBound=",1),
    USERNAME("sUsername=",2),
    TOKENKEY("sTokenKey=",3),
    TOKENUSER("sTokenUser=",4);

    private String sValue;
    private int iValue;

    private CommunicationVariables(String sValue,int iValue){
        this.iValue = iValue;
        this.sValue = sValue;
    }

    @Override
    public String toString() {
        return sValue;
    }
}
