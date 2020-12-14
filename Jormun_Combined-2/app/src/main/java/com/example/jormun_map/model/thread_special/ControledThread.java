package com.example.jormun_map.model.thread_special;

public abstract class ControledThread extends Thread{
    protected boolean bCheck;

    public abstract void setbCheck(boolean bValue);
    public boolean isbCheck(){
        return bCheck;
    }
}
