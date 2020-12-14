package com.example.jormun_map.Config;

public enum CommunicationDividor {
    READ_PARAM("\\?",0),
    WRITE_PARAM("?",1),
    COORD(";",2),
    BOUND_DIVIDOR("@",5),
    PARAM_DIVIDOR("&",6);

    private String sValue;
    private int iValue;

    private CommunicationDividor(String sValue,int iValue){
        this.iValue = iValue;
        this.sValue = sValue;
    }

    @Override
    public String toString() {
        return sValue;
    }
}
