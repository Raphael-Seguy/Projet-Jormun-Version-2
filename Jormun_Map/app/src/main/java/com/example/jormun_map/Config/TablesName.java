package com.example.jormun_map.Config;

public enum TablesName {
    TABLE_INFOUSER("infouser", 0),
    TABLE_WORLDMAPMIN("worldmapmin", 1),
    TABLE_CURRENTTEAM("currentteam", 2),
    TABLE_HERO("hero", 3),
    TABLE_INVENTORY("inventory", 4),
    TABLE_ITEM("item", 5),
    TABLE_MONSTER("monster", 6),
    TABLE_VILLAGE("village", 7),
    TABLE_STRUCTURE("structure", 8),
    TABLE_BUILDING("building", 9),
    TABLE_QUEST("quest", 10);


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
