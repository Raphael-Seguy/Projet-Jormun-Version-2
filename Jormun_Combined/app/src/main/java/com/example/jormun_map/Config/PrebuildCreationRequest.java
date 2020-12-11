package com.example.jormun_map.Config;

public enum PrebuildCreationRequest {
    CREATE_CURRENTTEAM("CREATE TABLE if not exists " + TablesName.TABLE_CURRENTTEAM.toString() + "(" +
            TablesColumn.COL_CURRENTTEAM_IDMEMBER.toString() + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TablesColumn.COL_CURRENTTEAM_PATHJSON.toString() + " VARCHAR(255) NOT NULL," +
            TablesColumn.COL_CURRENTTEAM_IDUSER.toString() + " INTEGER references " + TablesName.TABLE_USERINFO.toString() + "(" + TablesColumn.COL_USERINFO_IDUSER.toString() + ") ON UPDATE CASCADE ON DELETE CASCADE," +
            TablesColumn.COL_CURRENTTEAM_IDREL.toString()+" INTEGER NOT NULL)",0),
    CREATE_HERO("CREATE TABLE if not exists " + TablesName.TABLE_HERO.toString() + "(" +
            TablesColumn.COL_HERO_IDHERO.toString() + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TablesColumn.COL_HERO_PATHJSON.toString() + " VARCHAR(255) NOT NULL," +
            TablesColumn.COL_HERO_IDUSER.toString() + " INTEGER references " + TablesName.TABLE_USERINFO.toString() + "(" + TablesColumn.COL_USERINFO_IDUSER + ") ON UPDATE CASCADE ON DELETE CASCADE," +
            TablesColumn.COL_HERO_IDREL.toString()+" INTEGER NOT NULL)",1),
    CREATE_USERINFO("CREATE TABLE if not exists " + TablesName.TABLE_USERINFO.toString() + "(" +
            TablesColumn.COL_USERINFO_IDUSER.toString() + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TablesColumn.COL_USERINFO_TOKEN.toString() + " VARCHAR(255) NOT NULL,"+
            TablesColumn.COL_USERINFO_USERNAME.toString() + " VARCHAR(255) NOT NULL)",2),
    CREATE_MONSTER("CREATE TABLE if not exists " + TablesName.TABLE_MONSTER.toString() + "(" +
            TablesColumn.COL_MONSTER_IDMONSTER.toString() + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TablesColumn.COL_MONSTER_PATHJSON.toString() + " VARCHAR(255) NOT NULL," +
            TablesColumn.COL_MONSTER_IDUSER.toString() + " INTEGER references " + TablesName.TABLE_USERINFO.toString() + "(" + TablesColumn.COL_USERINFO_IDUSER.toString() + ") ON UPDATE CASCADE ON DELETE CASCADE)",3),
    CREATE_VILLAGE("CREATE TABLE if not exists " + TablesName.TABLE_VILLAGE.toString() + "(" +
            TablesColumn.COL_VILLAGE_IDVILLAGE.toString() + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TablesColumn.COL_VILLAGE_PATHJSON.toString() + " VARCHAR(255) NOT NULL," +
            TablesColumn.COL_VILLAGE_IDUSER.toString() + " INTEGER references " + TablesName.TABLE_USERINFO.toString() + "(" + TablesColumn.COL_USERINFO_IDUSER.toString() + ") ON UPDATE CASCADE ON DELETE CASCADE," +
            TablesColumn.COL_VILLAGE_IDREL.toString()+" INTEGER NOT NULL)",4),
    CREATE_WORLDMAPMIN("CREATE TABLE if not exists " + TablesName.TABLE_WORLDMAPMIN.toString() + "(" +
            TablesColumn.COL_WORLDMAPMIN_IDPOINT.toString() + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TablesColumn.COL_WORLDMAPMIN_POINTLAT.toString() + " REAL NOT NULL," +
            TablesColumn.COL_WORLDMAPMIN_POINTLONG.toString() + " REAL NOT NULL," +
            TablesColumn.COL_WORLDMAPMIN_OBJECTREF.toString() + " VARCHAR(255) NOT NULL," +
            TablesColumn.COL_WORLDMAPMIN_IDREF.toString() + " INTEGER,"+
            TablesColumn.COL_WORLDMAPMIN_IDUSER.toString() + " INTEGER,"+
            TablesColumn.COL_WORLDMAPMIN_ELEMENTKEY.toString()+ " VARCHAR(255))",5),
    CREATE_STRUCTURE("CREATE TABLE if not exists " + TablesName.TABLE_STRUCTURE.toString() + "(" +
            TablesColumn.COL_STRUCTURE_IDSTRUCTURE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TablesColumn.COL_STRUCTURE_PATHJSON + " VARCHAR(255) NOT NULL," +
            TablesColumn.COL_STRUCTURE_IDUSER.toString() + " INTEGER references " + TablesName.TABLE_USERINFO.toString() + "(" + TablesColumn.COL_USERINFO_IDUSER.toString() + ") ON UPDATE CASCADE ON DELETE CASCADE," +
            TablesColumn.COL_STRUCTURE_IDREL.toString()+" INTEGER NOT NULL)",6),
    CREATE_BOSS("CREATE TABLE if not exists " + TablesName.TABLE_STRUCTURE.toString() + "(" +
            TablesColumn.COL_STRUCTURE_IDSTRUCTURE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TablesColumn.COL_STRUCTURE_PATHJSON + " VARCHAR(255) NOT NULL," +
            TablesColumn.COL_STRUCTURE_IDREL.toString()+" INTEGER NOT NULL)",6);
    private final String sValue;
    private final int iValue;

    PrebuildCreationRequest(String sValue, int iIndex) {
        this.sValue = sValue;
        this.iValue = iIndex;
    }

    @Override
    public String toString() {
        return sValue;
    }
}
