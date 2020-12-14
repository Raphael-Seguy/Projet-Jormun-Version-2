package com.example.jormun_map.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jormun_map.Config.PrebuildCreationRequest;
import com.example.jormun_map.Config.TablesName;

public class InternalDBBuilder extends SQLiteOpenHelper {
    SQLiteDatabase dbCurrent;
    public InternalDBBuilder(Context context,String sDBName,SQLiteDatabase.CursorFactory crsrfctCurrent,int iVersion){
        super(context, sDBName, crsrfctCurrent, iVersion);
        System.out.println("Building Database");
        setDbCurrent(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase dbGame) {
        System.out.println("Initialize DB");
        InitializeTables(dbGame);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void InitializeTables(SQLiteDatabase db) {
        TablesName[] a_tblnmDatabase = {TablesName.TABLE_USERINFO, TablesName.TABLE_WORLDMAPMIN, TablesName.TABLE_VILLAGE, TablesName.TABLE_MONSTER, TablesName.TABLE_HERO, TablesName.TABLE_CURRENTTEAM,TablesName.TABLE_STRUCTURE, TablesName.TABLE_BOSS,TablesName.TABLE_RESSOURCEZONE};
        for (TablesName tblnmTable : a_tblnmDatabase) {
            System.out.println(tblnmTable+" Building");
            db.execSQL(CreateAssembler(tblnmTable));
            System.out.println(tblnmTable+" Build");

        }
    }
    private String CreateAssembler(TablesName tblnmTable){
        String sAnswer;
        switch (tblnmTable) {
            case TABLE_CURRENTTEAM:
                sAnswer = PrebuildCreationRequest.CREATE_CURRENTTEAM.toString();
                break;
            case TABLE_HERO:
                sAnswer = PrebuildCreationRequest.CREATE_HERO.toString();
                break;
            case TABLE_USERINFO:
                sAnswer = PrebuildCreationRequest.CREATE_USERINFO.toString();
                break;
            case TABLE_MONSTER:
                sAnswer = PrebuildCreationRequest.CREATE_MONSTER.toString();
                break;
            case TABLE_VILLAGE:
                sAnswer = PrebuildCreationRequest.CREATE_VILLAGE.toString();
                break;
            case TABLE_WORLDMAPMIN:
                sAnswer = PrebuildCreationRequest.CREATE_WORLDMAPMIN.toString();
                break;
            case TABLE_STRUCTURE:
                sAnswer = PrebuildCreationRequest.CREATE_STRUCTURE.toString();
                break;
            case TABLE_BOSS:
                sAnswer = PrebuildCreationRequest.CREATE_BOSS.toString();
                break;
            case TABLE_RESSOURCEZONE:
                sAnswer=PrebuildCreationRequest.CREATE_RESSOURCEZONE.toString();
                break;
            default:
                sAnswer="";
                break;
        }
        return sAnswer;

    }
    public boolean DropTables(SQLiteDatabase db){
        String[] a_sDatabase = {TablesName.TABLE_USERINFO.toString(), TablesName.TABLE_WORLDMAPMIN.toString(), TablesName.TABLE_VILLAGE.toString(), TablesName.TABLE_MONSTER.toString(), TablesName.TABLE_HERO.toString(), TablesName.TABLE_CURRENTTEAM.toString(),TablesName.TABLE_STRUCTURE.toString(),TablesName.TABLE_BOSS.toString(),TablesName.TABLE_RESSOURCEZONE.toString()};

        Boolean bDropped;

        bDropped=true;
        for (String sTable : a_sDatabase) {
            try{
                db.execSQL(DropTable(sTable));
            }catch (SQLiteException e){
            }
        }
        return bDropped;
    }
    private String DropTable(String sTable){
        String sAnswer;

        sAnswer = "DROP TABLE "+sTable;

        return sAnswer;

    }

    public SQLiteDatabase getDbCurrent() {
        return dbCurrent;
    }

    public void setDbCurrent(SQLiteDatabase dbCurrent) {
        this.dbCurrent = dbCurrent;
    }
}
