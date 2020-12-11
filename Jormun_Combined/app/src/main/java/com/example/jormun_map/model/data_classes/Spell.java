package com.example.jormun_map.model.data_classes;

import com.example.jormun_map.R;

public class Spell // les sorts possédent des sort positif et/ou négatif
{
    private String nom;
    private int duree;

    private int degats;
    private int type;
    private boolean magique;

    private BonusEffect effet_Bonus;
    private MalusEffect effet_Malus;

    private int Sprite;

    public Spell(String Nom, int Duree, int Degats, int Type, boolean Magique, BonusEffect BonusDuSort, MalusEffect MalusDuSort)
    {
        this.setNom(Nom);
        this.setDuree(Duree);
        this.setDegats(Degats);
        this.setType(Type);
        this.setMagique(Magique);

        if(Type%3==0) // Si le sort possède des effets Bonus
        {
            this.setEffet_Bonus(BonusDuSort);
        }
        if(Type%7==0) // Si le sort possède des effets négatfis
        {
            this.setEffet_Malus(MalusDuSort);
        }

        this.setSprite(R.drawable.default_icon);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isMagique() {
        return magique;
    }

    public void setMagique(boolean magique) {
        this.magique = magique;
    }

    public BonusEffect getEffet_Bonus() {
        return effet_Bonus;
    }

    public void setEffet_Bonus(BonusEffect effet_Bonus) {
        this.effet_Bonus = effet_Bonus;
    }

    public MalusEffect getEffet_Malus() {
        return effet_Malus;
    }

    public void setEffet_Malus(MalusEffect effet_Malus) {
        this.effet_Malus = effet_Malus;
    }

    public int getSprite() {
        return Sprite;
    }

    public void setSprite(int sprite) {
        Sprite = sprite;
    }
}
