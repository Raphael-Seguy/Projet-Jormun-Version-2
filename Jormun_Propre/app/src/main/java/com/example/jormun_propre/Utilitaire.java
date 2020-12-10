package com.example.jormun_propre;

public class Utilitaire extends  Recompense{ // les utilitaires sont des recompenses possédant un sort, genre popo de soin

    public Spell sortillege;
    public Utilitaire(int Vquantite, String Vnom, int Duree, int Degats, int Type, boolean Magique, BonusEffect BonusDeObject, MalusEffect MalusDeObject, String Vdescription)
    {
        super(Vnom,Vdescription,Vquantite,10);
        this.sortillege = new Spell(Vnom, Duree, Degats, Type, Magique, BonusDeObject, MalusDeObject);
    }


}
