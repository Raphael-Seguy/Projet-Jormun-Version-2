package com.example.jormun_map.Config;

public enum MessageType {
    UPDATEPOS("#UPDATEPOS#",0),
    UPDATEOTHERCLIENT("#UPDATECLIENT#",1),
    CHECKCONNECTION("#CheckConnection#",2),
    CHECKCONNECTIONRESPONSE("#ConnectionEstablished#",3),
    CLOSE("#CLOSE#",4),
    CONNECTIONREQUEST("#Connection#",5);

    private String sValue;
    private int iValue;

    private MessageType(String sValue,int iValue){
        this.iValue=iValue;
        this.sValue = sValue;
    }

    @Override
    public String toString() {
        return sValue;
    }
}
