package com.example.jormun_map.client;

import android.content.SharedPreferences;

import com.example.jormun_map.Config.SharedPrefKeys;

public class PositionServerClient {
    private final static String sIP = "192.168.31.57";

    private final static int iPort = 3995;

    private static ServerCommunication svrcomServer;

    private SharedPreferences shrdprefSetting;

    private PositionServerClient(){};
    private static class ClientHolder{
        public final static PositionServerClient pstsrvrcltInstance = new PositionServerClient();
    }
    public static PositionServerClient getInstance(){
        return ClientHolder.pstsrvrcltInstance;
    }
    public void connect(){
        System.out.println(" USERNAMEKEY "+getShrdprefSetting().getAll().get(SharedPrefKeys.USERNAMEKEY.toString()));
        System.out.println(" POSITIONKEY "+getShrdprefSetting().getAll().get(SharedPrefKeys.POSITIONKEY.toString()));
        System.out.println(" POSITIONLOGIN "+getShrdprefSetting().getAll().get(SharedPrefKeys.POSITIONLOGIN.toString()));
        try{
            if(getSvrcomServer()!=null){
                System.out.println("Server is online");
            }else{
                setSvrcomServer(new ServerCommunication());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getsIP() {
        return sIP;
    }

    public static int getiPort() {
        return iPort;
    }

    public static ServerCommunication getSvrcomServer() {
        return svrcomServer;
    }

    public SharedPreferences getShrdprefSetting() {
        return shrdprefSetting;
    }

    public static void setSvrcomServer(ServerCommunication svrcomServer) {
        PositionServerClient.svrcomServer = svrcomServer;
    }

    public void setShrdprefSetting(SharedPreferences shrdprefSetting) {
        this.shrdprefSetting = shrdprefSetting;
    }
}
