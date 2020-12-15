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
        ContentValues cvMonster;

        try {
            cvMonster = new ContentValues();
            cvMonster.put(TablesColumn.COL_MONSTER_PATHJSON.toString(),sPathJson);
            cvMonster.put(TablesColumn.COL_MONSTER_IDREL.toString(),iId);
            cvMonster.put(TablesColumn.COL_MONSTER_IDUSER.toString(),iUserId);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_MONSTER.toString(),"",cvMonster);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoHero(String sPathJson,int iId,int iUserId){
        ContentValues cvCurrentHero;

        try {
            cvCurrentHero = new ContentValues();
            cvCurrentHero.put(TablesColumn.COL_HERO_PATHJSON.toString(),sPathJson);
            cvCurrentHero.put(TablesColumn.COL_HERO_IDREL.toString(),iId);
            cvCurrentHero.put(TablesColumn.COL_HERO_IDUSER.toString(),iUserId);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_HERO.toString(),"",cvCurrentHero);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoVillage(String sPathJson,int iId,int iUserId){
        ContentValues cvCurrentVillage;

        try {
            cvCurrentVillage = new ContentValues();
            cvCurrentVillage.put(TablesColumn.COL_VILLAGE_PATHJSON.toString(),sPathJson);
            cvCurrentVillage.put(TablesColumn.COL_VILLAGE_IDREL.toString(),iId);
            cvCurrentVillage.put(TablesColumn.COL_VILLAGE_IDUSER.toString(),iUserId);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_VILLAGE.toString(),"",cvCurrentVillage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoCurrentTeam(String sPathJson,int iId,int iUserId){
        ContentValues cvCurrentTeam;

        try {
            cvCurrentTeam = new ContentValues();
            cvCurrentTeam.put(TablesColumn.COL_CURRENTTEAM_PATHJSON.toString(),sPathJson);
            cvCurrentTeam.put(TablesColumn.COL_CURRENTTEAM_IDREL.toString(),iId);
            cvCurrentTeam.put(TablesColumn.COL_CURRENTTEAM_IDUSER.toString(),iUserId);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_CURRENTTEAM.toString(),"",cvCurrentTeam);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoStructure(String sPathJson,int iId){
        ContentValues cvCurrentStructure;

        try {
            cvCurrentStructure = new ContentValues();
            cvCurrentStructure.put(TablesColumn.COL_STRUCTURE_PATHJSON.toString(),sPathJson);
            cvCurrentStructure.put(TablesColumn.COL_STRUCTURE_IDREL.toString(),iId);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_STRUCTURE.toString(),"",cvCurrentStructure);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoRessourceZone(String sPathJson,int iId){
        ContentValues cvRessourceZone;

        try {
            cvRessourceZone = new ContentValues();
            cvRessourceZone.put(TablesColumn.COL_STRUCTURE_PATHJSON.toString(),sPathJson);
            cvRessourceZone.put(TablesColumn.COL_STRUCTURE_IDREL.toString(),iId);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_STRUCTURE.toString(),"",cvRessourceZone);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoWorldMapMin(String sReference,float fLat,float fLong,int iId,int iIdUser){
        ContentValues cvWorldMapMin;

        try {
            cvWorldMapMin = new ContentValues();
            cvWorldMapMin.put(TablesColumn.COL_WORLDMAPMIN_IDREF.toString(),iId);
            cvWorldMapMin.put(TablesColumn.COL_WORLDMAPMIN_OBJECTREF.toString(),sReference);
            cvWorldMapMin.put(TablesColumn.COL_WORLDMAPMIN_POINTLAT.toString(),fLat);
            cvWorldMapMin.put(TablesColumn.COL_WORLDMAPMIN_POINTLONG.toString(),fLong);
            cvWorldMapMin.put(TablesColumn.COL_WORLDMAPMIN_IDUSER.toString(),iIdUser);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_WORLDMAPMIN.toString(),"",cvWorldMapMin);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertIntoUserInfo(String sUsername,String sToken){
        ContentValues cvUserInfo;

        try {
            cvUserInfo = new ContentValues();
            cvUserInfo.put(TablesColumn.COL_USERINFO_USERNAME.toString(),sUsername);
            cvUserInfo.put(TablesColumn.COL_USERINFO_TOKEN.toString(),sToken);
            getSqltdtbsInternalBd().insert(TablesName.TABLE_USERINFO.toString(),"",cvUserInfo);
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
    public void RegisterMapElement(TablesName tbleCurrent,String sPath,int iId) {
        switch (tbleCurrent){
            case TABLE_STRUCTURE:
                InsertIntoStructure(sPath,iId);
                break;
            case TABLE_RESSOURCEZONE:
                InsertIntoRessourceZone(sPath,iId);
            case TABLE_BOSS:
                InsertIntoBoss(sPath,iId);
                break;
        }
    }
    public void RegisterUserElement(TablesName tbleCurrent,String sPath,int iIdUser,int iId) {
        switch (tbleCurrent){
            case TABLE_CURRENTTEAM:
                InsertIntoCurrentTeam(sPath,iId,iIdUser);
                break;
            case TABLE_VILLAGE:
                InsertIntoVillage(sPath,iId,iIdUser);
                break;
            case TABLE_MONSTER:
                InsertIntoMonster(sPath, iId,iIdUser);
                break;
            case TABLE_HERO:
                InsertIntoHero(sPath,iId,iIdUser);
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
