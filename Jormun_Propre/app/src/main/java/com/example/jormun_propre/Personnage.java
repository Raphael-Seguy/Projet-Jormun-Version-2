package com.example.jormun_propre;

public class Personnage // les personnages peuvent être des héros ou des mob
{
    int maxHP;
    int actuHp;
    int lvl;
    int armure;
    int armureMag;
    int degat;
    int esquive;
    String name;

    int Image;

    int armure_plus;
    double armure_mult;
    int armuremag_plus;
    double armuremag_mult;
    int esquive_plus;
    double esquive_mult;
    int degat_plus;
    double degat_mult;
    int degatmag_plus;
    double degatmag_mult;

    Spell spel0;
    Spell spel1;
    Spell spel2;
    Spell spel3;

    //Sprite défini
    public Personnage(int SPrite,int VmaxHP, int Vactuhp , int Vlvl, int Varmure, int VarmureMag, int Vdegat, int Vesquive, int armure_plus, double armure_mult, int armuremag_plus, double armuremag_mult, int esquive_plus, double esquive_mult, int degat_plus, double degat_mult, int degatmag_plus, double degatmag_mult, String Nom)
    {
        this.Image = SPrite;
        this.maxHP = VmaxHP;
        this.actuHp = Vactuhp;
        this.lvl = Vlvl;
        this.armure = Varmure;
        this.armureMag = VarmureMag;
        this.degat = Vdegat;
        this.esquive = Vesquive;

        this.armure_plus = armure_plus;
        this.armure_mult = armure_mult;
        this.armuremag_plus= armuremag_plus;
        this.armuremag_mult= armuremag_mult;
        this.esquive_plus= esquive_plus;
        this.esquive_mult= esquive_mult;
        this.degat_plus = degat_plus;
        this.degat_mult= degat_mult;
        this.degatmag_plus= degatmag_plus;
        this.degatmag_mult= degatmag_mult;

        this.name = Nom;
    }
    //Sprite de Base
    public Personnage(int VmaxHP, int Vactuhp , int Vlvl, int Varmure, int VarmureMag, int Vdegat, int Vesquive, int armure_plus, double armure_mult, int armuremag_plus, double armuremag_mult, int esquive_plus, double esquive_mult, int degat_plus, double degat_mult, int degatmag_plus, double degatmag_mult, String Nom)
    {
        this.Image = R.drawable.default_icon;
        this.maxHP = VmaxHP;
        this.actuHp = Vactuhp;
        this.lvl = Vlvl;
        this.armure = Varmure;
        this.armureMag = VarmureMag;
        this.degat = Vdegat;
        this.esquive = Vesquive;

        this.armure_plus = armure_plus;
        this.armure_mult = armure_mult;
        this.armuremag_plus= armuremag_plus;
        this.armuremag_mult= armuremag_mult;
        this.esquive_plus= esquive_plus;
        this.esquive_mult= esquive_mult;
        this.degat_plus = degat_plus;
        this.degat_mult= degat_mult;
        this.degatmag_plus= degatmag_plus;
        this.degatmag_mult= degatmag_mult;

        this.name = Nom;
    }

    public Personnage() {

    }

    public void addSpell(Spell newSpell)
    {
        if(spel0 == null)
        {
            this.spel0 = newSpell;
        }
        else if(spel1 == null)
        {
            this.spel1 = newSpell;
        }
        else if(spel2 == null)
        {
            this.spel2 = newSpell;
        }
        else if(spel3 == null)
        {
            this.spel3 = newSpell;
        }
    }
    public void changeSpell(int numero, Spell newSpell)
    {
        if(numero==0)
        {
            this.spel0 = newSpell;
        }
        else if(numero==1)
        {
            this.spel1 = newSpell;
        }
        else if(numero==2)
        {
            this.spel2 = newSpell;
        }
        else if(numero==3)
        {
            this.spel3 = newSpell;
        }
    }

    public void changeSprite(int newSprite)
    {
        this.Image = newSprite;
    }

}
