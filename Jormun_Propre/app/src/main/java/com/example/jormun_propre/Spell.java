package com.example.jormun_propre;

public class Spell
{
    String nom;
    int duree;

    int degats;
    int type;
    boolean magique;

    BonusEffect effet_Bonus;
    MalusEffect effet_Malus;

    int Sprite;

    public Spell(String Nom, int Duree, int Degats, int Type, boolean Magique, BonusEffect BonusDuSort, MalusEffect MalusDuSort)
    {
        this.nom = Nom;
        this.duree = Duree;
        this.degats = Degats;
        this.type = Type;
        this.magique = Magique;

        if(Type%3==0) // Si le sort possède des effets Bonus
        {
            this.effet_Bonus = BonusDuSort;
        }
        if(Type%7==0) // Si le sort possède des effets négatfis
        {
            this.effet_Malus = MalusDuSort;
        }

        this.Sprite = R.drawable.default_icon;
    }
}
