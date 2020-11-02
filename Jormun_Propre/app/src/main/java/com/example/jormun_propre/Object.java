package com.example.jormun_propre;

public class Object extends Spell {

    public int quantite;
    public Object(int Vquantite,String Nom, int Duree, int Degats, int Type, boolean Magique, SpellBonus BonusDeObject, SpellMalus MalusDeObject)
    {
        super(Nom, Duree, Degats, Type, Magique, BonusDeObject, MalusDeObject);
        this.quantite = Vquantite;
    }


}
