package com.example.jormun_map.model;

import android.database.sqlite.SQLiteDatabase;

public class InternalDbManager {
    private static final int VERSION_BDD = 1;

    private static final String NOM_BDD = "game.db";

    private SQLiteDatabase sqltdtbsInternalBd;

    private InternalDbManager(){}

    private static class InternalDbManagerHolder{
        private static final InternalDbManager intrndbmgrInstance = new InternalDbManager();
    }

    public InternalDbManager getInstance(){
        return InternalDbManagerHolder.intrndbmgrInstance;
    }
}
