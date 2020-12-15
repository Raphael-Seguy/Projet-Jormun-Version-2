package com.example.jormun_map.Config;

public enum CornerId {
    NL("NL",0),
    NR("NR",1),
    FL("FL",2),
    FR("FR",3);

    private String sValue;
    private int iValue;

    private CornerId(String sValue,int iValue){
        this.iValue = iValue;
        this.sValue = sValue;
    }

    @Override
    public String toString() {
        return sValue;
    }
}
