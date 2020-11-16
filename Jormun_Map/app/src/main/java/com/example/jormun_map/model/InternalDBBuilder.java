package com.example.jormun_map.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jormun_map.Config.PrebuildCreationRequest;
import com.example.jormun_map.Config.TablesName;

public class InternalDBBuilder extends SQLiteOpenHelper {
    public InternalDBBuilder(Context context,String sDBName,SQLiteDatabase.CursorFactory crsrfctCurrent,int iVersion){
        super(context, sDBName, crsrfctCurrent, iVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase dbGame) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void InitializeTables(SQLiteDatabase db) {
        TablesName[] a_tblnmDatabase = {TablesName.TABLE_INFOUSER, TablesName.TABLE_WORLDMAPMIN, TablesName.TABLE_VILLAGE, TablesName.TABLE_MONSTER, TablesName.TABLE_HERO, TablesName.TABLE_CURRENTTEAM,TablesName.TABLE_STRUCTURE};
        for (TablesName tblnmTable : a_tblnmDatabase) {
            System.out.println(tblnmTable+" Building");
            db.execSQL(CreateAssembler(tblnmTable));

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
            case TABLE_INFOUSER:
                sAnswer = PrebuildCreationRequest.CREATE_INFOUSER.toString();
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
            default:
                sAnswer="";
                break;
        }
        return sAnswer;

    }
    public boolean DropTables(SQLiteDatabase db){
        String[] a_sDatabase = {TablesName.TABLE_INFOUSER.toString(), TablesName.TABLE_WORLDMAPMIN.toString(), TablesName.TABLE_VILLAGE.toString(), TablesName.TABLE_MONSTER.toString(), TablesName.TABLE_HERO.toString(), TablesName.TABLE_CURRENTTEAM.toString(),TablesName.TABLE_STRUCTURE.toString()};

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
}
