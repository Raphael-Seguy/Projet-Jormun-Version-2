package com.example.jormun_map.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jormun_map.Config.TablesColumn;
import com.example.jormun_map.Config.TablesName;

public class InternalDbManager {
    private static final int VERSION_BDD = 1;

    private static final String NOM_BDD = "game.db";

    private SQLiteDatabase sqltdtbsInternalBd;

    private InternalDBBuilder intDBBuilder;

    private InternalDbManager(){}

    //Insert Request
    public void InsertIntoBoss(String sPathJson,int iId){
        ContentValues cvCurrentBoss;

        try {
            cvCurrentBoss = new ContentValues();
            cvCurrentBoss.put(TablesColumn.COL_BOSS_PATHJSON.toString(),sPathJson);
            cvCurrentBoss.put(TablesColumn.COL_BOSS_IDREL.toString(),iId);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_BOSS.toString(),"",cvCurrentBoss);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoMonster(String sPathJson,int iId,int iUserId){
        ContentValues cvCurrentBoss;

        try {
            cvCurrentBoss = new ContentValues();
            cvCurrentBoss.put(TablesColumn.COL_MONSTER_PATHJSON.toString(),sPathJson);
            cvCurrentBoss.put(TablesColumn.COL_MONSTER_IDREL.toString(),iId);
            cvCurrentBoss.put(TablesColumn.COL_MONSTER_IDUSER.toString(),iUserId);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_MONSTER.toString(),"",cvCurrentBoss);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoHero(String sPathJson,int iId,int iUserId){
        ContentValues cvCurrentBoss;

        try {
            cvCurrentBoss = new ContentValues();
            cvCurrentBoss.put(TablesColumn.COL_HERO_PATHJSON.toString(),sPathJson);
            cvCurrentBoss.put(TablesColumn.COL_HERO_IDREL.toString(),iId);
            cvCurrentBoss.put(TablesColumn.COL_HERO_IDUSER.toString(),iUserId);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_HERO.toString(),"",cvCurrentBoss);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoVillage(String sPathJson,int iId,int iUserId){
        ContentValues cvCurrentBoss;

        try {
            cvCurrentBoss = new ContentValues();
            cvCurrentBoss.put(TablesColumn.COL_VILLAGE_PATHJSON.toString(),sPathJson);
            cvCurrentBoss.put(TablesColumn.COL_VILLAGE_IDREL.toString(),iId);
            cvCurrentBoss.put(TablesColumn.COL_VILLAGE_IDUSER.toString(),iUserId);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_VILLAGE.toString(),"",cvCurrentBoss);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoCurrentTeam(String sPathJson,int iId,int iUserId){
        ContentValues cvCurrentBoss;

        try {
            cvCurrentBoss = new ContentValues();
            cvCurrentBoss.put(TablesColumn.COL_CURRENTTEAM_PATHJSON.toString(),sPathJson);
            cvCurrentBoss.put(TablesColumn.COL_CURRENTTEAM_IDREL.toString(),iId);
            cvCurrentBoss.put(TablesColumn.COL_CURRENTTEAM_IDUSER.toString(),iUserId);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_CURRENTTEAM.toString(),"",cvCurrentBoss);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoStructure(String sPathJson,int iId){
        ContentValues cvCurrentBoss;

        try {
            cvCurrentBoss = new ContentValues();
            cvCurrentBoss.put(TablesColumn.COL_STRUCTURE_PATHJSON.toString(),sPathJson);
            cvCurrentBoss.put(TablesColumn.COL_STRUCTURE_IDREL.toString(),iId);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_STRUCTURE.toString(),"",cvCurrentBoss);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoWorldMapMin(String sReference,float fLat,float fLong,int iId,int iIdUser){
        ContentValues cvCurrentBoss;

        try {
            cvCurrentBoss = new ContentValues();
            cvCurrentBoss.put(TablesColumn.COL_WORLDMAPMIN_IDREF.toString(),iId);
            cvCurrentBoss.put(TablesColumn.COL_WORLDMAPMIN_OBJECTREF.toString(),sReference);
            cvCurrentBoss.put(TablesColumn.COL_WORLDMAPMIN_POINTLAT.toString(),fLat);
            cvCurrentBoss.put(TablesColumn.COL_WORLDMAPMIN_POINTLONG.toString(),fLong);
            cvCurrentBoss.put(TablesColumn.COL_WORLDMAPMIN_IDUSER.toString(),iIdUser);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_WORLDMAPMIN.toString(),"",cvCurrentBoss);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoUserInfo(String sUsername,String sToken){
        ContentValues cvCurrentBoss;

        try {
            cvCurrentBoss = new ContentValues();
            cvCurrentBoss.put(TablesColumn.COL_USERINFO_USERNAME.toString(),sUsername);
            cvCurrentBoss.put(TablesColumn.COL_USERINFO_TOKEN.toString(),sToken);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_USERINFO.toString(),"",cvCurrentBoss);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String GrabFile(TablesName tblCurrent,int iId){
        Cursor crsrCurrent;
        switch (tblCurrent){
            case TABLE_BOSS:
                crsrCurrent = getSqltdtbsInternalBd().rawQuery("SELECT "+TablesColumn.COL_BOSS_PATHJSON+" FROM "+TablesName.TABLE_BOSS+" WHERE "+TablesColumn.COL_BOSS_IDBOSS+"=?",new String[]{""+iId});
                crsrCurrent.moveToFirst();
                return crsrCurrent.getString(crsrCurrent.getColumnIndex(TablesColumn.COL_BOSS_PATHJSON.toString()));
            case TABLE_HERO:
                crsrCurrent = getSqltdtbsInternalBd().rawQuery("SELECT "+TablesColumn.COL_HERO_PATHJSON+" FROM "+TablesName.TABLE_HERO+" WHERE "+TablesColumn.COL_HERO_IDHERO+"=?",new String[]{""+iId});
                crsrCurrent.moveToFirst();
                return crsrCurrent.getString(crsrCurrent.getColumnIndex(TablesColumn.COL_HERO_PATHJSON.toString()));
            case TABLE_MONSTER:
                crsrCurrent = getSqltdtbsInternalBd().rawQuery("SELECT "+TablesColumn.COL_MONSTER_IDUSER+" FROM "+TablesName.TABLE_MONSTER+" WHERE "+TablesColumn.COL_MONSTER_IDUSER+"=?",new String[]{""+iId});
                crsrCurrent.moveToFirst();
                return crsrCurrent.getString(crsrCurrent.getColumnIndex(TablesColumn.COL_MONSTER_PATHJSON.toString()));
            case TABLE_VILLAGE:
                crsrCurrent = getSqltdtbsInternalBd().rawQuery("SELECT "+TablesColumn.COL_VILLAGE_PATHJSON+" FROM "+TablesName.TABLE_VILLAGE+" WHERE "+TablesColumn.COL_VILLAGE_IDVILLAGE+"=?",new String[]{""+iId});
                crsrCurrent.moveToFirst();
                return crsrCurrent.getString(crsrCurrent.getColumnIndex(TablesColumn.COL_HERO_PATHJSON.toString()));
            case TABLE_CURRENTTEAM:
                crsrCurrent = getSqltdtbsInternalBd().rawQuery("SELECT "+TablesColumn.COL_CURRENTTEAM_PATHJSON+" FROM "+TablesName.TABLE_CURRENTTEAM+" WHERE "+TablesColumn.COL_CURRENTTEAM_IDMEMBER+"=?",new String[]{""+iId});
                crsrCurrent.moveToFirst();
                return crsrCurrent.getString(crsrCurrent.getColumnIndex(TablesColumn.COL_CURRENTTEAM_PATHJSON.toString()));
            case TABLE_STRUCTURE:
                crsrCurrent = getSqltdtbsInternalBd().rawQuery("SELECT "+TablesColumn.COL_STRUCTURE_PATHJSON+" FROM "+TablesName.TABLE_STRUCTURE+" WHERE "+TablesColumn.COL_STRUCTURE_IDSTRUCTURE+"=?",new String[]{""+iId});
                crsrCurrent.moveToFirst();
                return crsrCurrent.getString(crsrCurrent.getColumnIndex(TablesColumn.COL_STRUCTURE_PATHJSON.toString()));
            default:
                return "";
        }
    }
    public void RegisterElement(TablesName tbleCurrent,int iId) {
        switch (tbleCurrent){
            case TABLE_STRUCTURE:
                break;
            case TABLE_CURRENTTEAM:
                break;
            case TABLE_INVENTORY:
                break;
            case TABLE_BUILDING:
                break;
            case TABLE_VILLAGE:
                break;
            case TABLE_MONSTER:
                break;
            case TABLE_QUEST:
                break;
            case TABLE_HERO:
                break;
            case TABLE_ITEM:
                break;
            case TABLE_BOSS:
                break;
        }
    }

    private static class InternalDbManagerHolder{
        private static final InternalDbManager intrndbmgrInstance = new InternalDbManager();
    }

    public static InternalDbManager getInstance(){
        return InternalDbManagerHolder.intrndbmgrInstance;
    }
    public void CreateDatabase(Context ctxtCurrent){
        System.out.println("Making the world");
        intDBBuilder = new InternalDBBuilder(ctxtCurrent,NOM_BDD,null,VERSION_BDD);
    }

    public static int getVersionBdd() {
        return VERSION_BDD;
    }

    public static String getNomBdd() {
        return NOM_BDD;
    }

    public SQLiteDatabase getSqltdtbsInternalBd() {
        return sqltdtbsInternalBd;
    }

    public InternalDBBuilder getIntDBBuilder() {
        return intDBBuilder;
    }

    public void setSqltdtbsInternalBd(SQLiteDatabase sqltdtbsInternalBd) {
        this.sqltdtbsInternalBd = sqltdtbsInternalBd;
    }

    public void setIntDBBuilder(InternalDBBuilder intDBBuilder) {
        this.intDBBuilder = intDBBuilder;
    }
}
