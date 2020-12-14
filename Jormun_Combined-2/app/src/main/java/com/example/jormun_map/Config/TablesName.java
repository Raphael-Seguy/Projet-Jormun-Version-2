package com.example.jormun_map.Config;

public enum TablesName {
    TABLE_USERINFO("Userinfo", 0),
    TABLE_WORLDMAPMIN("Worldmap", 1),
    TABLE_CURRENTTEAM("CurrentTeam", 2),
    TABLE_HERO("StorageHero", 3),
    TABLE_INVENTORY("Inventory", 4),
    TABLE_ITEM("Item", 5),
    TABLE_MONSTER("Monster", 6),
    TABLE_VILLAGE("StorageVillage", 7),
    TABLE_STRUCTURE("Structure", 8),
    TABLE_BUILDING("Building", 9),
    TABLE_QUEST("Quest", 10),
    TABLE_BOSS("Boss",11),
    TABLE_RESSOURCEZONE("RessourceZone",12);


    private final String sValue;
    private final int iValue;

    TablesName(String sValue, int iIndex) {
        this.sValue = sValue;
        this.iValue = iIndex;
    }

    @Override
    public String toString() {
        return sValue;
    }
}
