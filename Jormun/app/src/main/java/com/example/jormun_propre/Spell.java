package com.example.jormun_propre;

public class Spell
{
    String nom;
    int duree;

    int degats;
    int type;
    boolean magique;

    SpellBonus effet_Bonus;
    SpellMalus effet_Malus;

    public Spell(String Nom, int Duree, int Degats, int Type, boolean Magique, SpellBonus BonusDuSort, SpellMalus MalusDuSort)
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
    }
}
