package com.example.jormun_map.model.data_classes;

import com.example.jormun_map.R;

public class Personnage // les personnages peuvent être des héros ou des mob
{
    private int maxHP;
    private int actuHp;
    private int lvl;
    private int armure;
    private int armureMag;
    private int degat;
    private int esquive;
    private String name;

    private int Image;

    private int armure_plus;
    private double armure_mult;
    private int armuremag_plus;
    private double armuremag_mult;
    private int esquive_plus;
    private double esquive_mult;
    private int degat_plus;
    private double degat_mult;
    private int degatmag_plus;
    private double degatmag_mult;

    private Spell spel0;
    private Spell spel1;
    private Spell spel2;
    private Spell spel3;

    //Sprite défini
    public Personnage(int SPrite,int VmaxHP, int Vactuhp , int Vlvl, int Varmure, int VarmureMag, int Vdegat, int Vesquive, int armure_plus, double armure_mult, int armuremag_plus, double armuremag_mult, int esquive_plus, double esquive_mult, int degat_plus, double degat_mult, int degatmag_plus, double degatmag_mult, String Nom)
    {
        this.setImage(SPrite);
        this.setMaxHP(VmaxHP);
        this.setActuHp(Vactuhp);
        this.setLvl(Vlvl);
        this.setArmure(Varmure);
        this.setArmureMag(VarmureMag);
        this.setDegat(Vdegat);
        this.setEsquive(Vesquive);

        this.setArmure_plus(armure_plus);
        this.setArmure_mult(armure_mult);
        this.setArmuremag_plus(armuremag_plus);
        this.setArmuremag_mult(armuremag_mult);
        this.setEsquive_plus(esquive_plus);
        this.setEsquive_mult(esquive_mult);
        this.setDegat_plus(degat_plus);
        this.setDegat_mult(degat_mult);
        this.setDegatmag_plus(degatmag_plus);
        this.setDegatmag_mult(degatmag_mult);

        this.setName(Nom);
    }
    //Sprite de Base
    public Personnage(int VmaxHP, int Vactuhp , int Vlvl, int Varmure, int VarmureMag, int Vdegat, int Vesquive, int armure_plus, double armure_mult, int armuremag_plus, double armuremag_mult, int esquive_plus, double esquive_mult, int degat_plus, double degat_mult, int degatmag_plus, double degatmag_mult, String Nom)
    {
        this.setImage(R.drawable.default_icon);
        this.setMaxHP(VmaxHP);
        this.setActuHp(Vactuhp);
        this.setLvl(Vlvl);
        this.setArmure(Varmure);
        this.setArmureMag(VarmureMag);
        this.setDegat(Vdegat);
        this.setEsquive(Vesquive);

        this.setArmure_plus(armure_plus);
        this.setArmure_mult(armure_mult);
        this.setArmuremag_plus(armuremag_plus);
        this.setArmuremag_mult(armuremag_mult);
        this.setEsquive_plus(esquive_plus);
        this.setEsquive_mult(esquive_mult);
        this.setDegat_plus(degat_plus);
        this.setDegat_mult(degat_mult);
        this.setDegatmag_plus(degatmag_plus);
        this.setDegatmag_mult(degatmag_mult);

        this.setName(Nom);
    }

    public Personnage() {

    }

    public void addSpell(Spell newSpell)
    {
        if(getSpel0() == null)
        {
            this.setSpel0(newSpell);
        }
        else if(getSpel1() == null)
        {
            this.setSpel1(newSpell);
        }
        else if(getSpel2() == null)
        {
            this.setSpel2(newSpell);
        }
        else if(getSpel3() == null)
        {
            this.setSpel3(newSpell);
        }
    }
    public void changeSpell(int numero, Spell newSpell)
    {
        if(numero==0)
        {
            this.setSpel0(newSpell);
        }
        else if(numero==1)
        {
            this.setSpel1(newSpell);
        }
        else if(numero==2)
        {
            this.setSpel2(newSpell);
        }
        else if(numero==3)
        {
            this.setSpel3(newSpell);
        }
    }

    public void changeSprite(int newSprite)
    {
        this.setImage(newSprite);
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getActuHp() {
        return actuHp;
    }

    public void setActuHp(int actuHp) {
        this.actuHp = actuHp;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getArmure() {
        return armure;
    }

    public void setArmure(int armure) {
        this.armure = armure;
    }

    public int getArmureMag() {
        return armureMag;
    }

    public void setArmureMag(int armureMag) {
        this.armureMag = armureMag;
    }

    public int getDegat() {
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    public int getEsquive() {
        return esquive;
    }

    public void setEsquive(int esquive) {
        this.esquive = esquive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public int getArmure_plus() {
        return armure_plus;
    }

    public void setArmure_plus(int armure_plus) {
        this.armure_plus = armure_plus;
    }

    public double getArmure_mult() {
        return armure_mult;
    }

    public void setArmure_mult(double armure_mult) {
        this.armure_mult = armure_mult;
    }

    public int getArmuremag_plus() {
        return armuremag_plus;
    }

    public void setArmuremag_plus(int armuremag_plus) {
        this.armuremag_plus = armuremag_plus;
    }

    public double getArmuremag_mult() {
        return armuremag_mult;
    }

    public void setArmuremag_mult(double armuremag_mult) {
        this.armuremag_mult = armuremag_mult;
    }

    public int getEsquive_plus() {
        return esquive_plus;
    }

    public void setEsquive_plus(int esquive_plus) {
        this.esquive_plus = esquive_plus;
    }

    public double getEsquive_mult() {
        return esquive_mult;
    }

    public void setEsquive_mult(double esquive_mult) {
        this.esquive_mult = esquive_mult;
    }

    public int getDegat_plus() {
        return degat_plus;
    }

    public void setDegat_plus(int degat_plus) {
        this.degat_plus = degat_plus;
    }

    public double getDegat_mult() {
        return degat_mult;
    }

    public void setDegat_mult(double degat_mult) {
        this.degat_mult = degat_mult;
    }

    public int getDegatmag_plus() {
        return degatmag_plus;
    }

    public void setDegatmag_plus(int degatmag_plus) {
        this.degatmag_plus = degatmag_plus;
    }

    public double getDegatmag_mult() {
        return degatmag_mult;
    }

    public void setDegatmag_mult(double degatmag_mult) {
        this.degatmag_mult = degatmag_mult;
    }

    public Spell getSpel0() {
        return spel0;
    }

    public void setSpel0(Spell spel0) {
        this.spel0 = spel0;
    }

    public Spell getSpel1() {
        return spel1;
    }

    public void setSpel1(Spell spel1) {
        this.spel1 = spel1;
    }

    public Spell getSpel2() {
        return spel2;
    }

    public void setSpel2(Spell spel2) {
        this.spel2 = spel2;
    }

    public Spell getSpel3() {
        return spel3;
    }

    public void setSpel3(Spell spel3) {
        this.spel3 = spel3;
    }
}
