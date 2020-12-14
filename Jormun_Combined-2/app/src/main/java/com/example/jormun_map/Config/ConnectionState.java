package com.example.jormun_map.Config;

public enum ConnectionState {
    CONNECTION_REQUEST("#Connection#",0),
    CONNECTION_REQUEST_ACCEPT("#ConnectionAccepted#",1),
    CONNECTION_REQUEST_DENY("#ConnectionDeny#",2),
    CHECKCONNECTION("#CheckConnection#",3),
    CHECKCONNECTIONRESPONSE("#ConnectionEstablished#",4);

    private String sValue;
    private int iValue;

    private ConnectionState(String sValue, int iValue){
        this.iValue = iValue;
        this.sValue = sValue;
    }

    @Override
    public String toString() {
        return  sValue;
    }
}
