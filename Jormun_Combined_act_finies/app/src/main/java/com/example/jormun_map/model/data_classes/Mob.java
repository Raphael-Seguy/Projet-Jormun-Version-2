package com.example.jormun_map.model.data_classes;

import java.util.ArrayList;

public class Mob extends Personnage
{
    private int prefMag;
    private int prefDef;
    private int prefAttack;
    private int prefPar;
    private int prefEsq;

    private String race;
    private ArrayList<Recompense> Recompenses_possibles=new ArrayList<Recompense>();

    public Mob(int Sprite, int VmaxHP, int Vactuhp , int Vlvl, int Varmure, int VarmureMag, int Vdegat, int Vesquive, int armure_plus, double armure_mult, int armuremag_plus, double armuremag_mult, int esquive_plus, double esquive_mult, int degat_plus, double degat_mult, int degatmag_plus, double degatmag_mult, int VprefMag, int VprefDef, int VprefAttack, int VprefPar, int VprefEsq,String Vrace)
    {
        super(Sprite, VmaxHP, Vactuhp , Vlvl, Varmure, VarmureMag, Vdegat, Vesquive, armure_plus, armure_mult, armuremag_plus, armuremag_mult, esquive_plus, esquive_mult, degat_plus, degat_mult, degatmag_plus, degatmag_mult, Vrace);
        this.setPrefMag(VprefMag);
        this.setPrefDef(VprefDef);
        this.setPrefAttack(VprefAttack);
        this.setPrefPar(VprefPar);
        this.setPrefEsq(VprefEsq);
        this.setRace(Vrace);
    }
    public void addRecompense(Recompense nouvelleRecompense)
    {
        Recompenses_possibles.add(nouvelleRecompense);
    }

    public Mob() {

    }

    public int getPrefMag() {
        return prefMag;
    }

    public void setPrefMag(int prefMag) {
        this.prefMag = prefMag;
    }

    public int getPrefDef() {
        return prefDef;
    }

    public void setPrefDef(int prefDef) {
        this.prefDef = prefDef;
    }

    public int getPrefAttack() {
        return prefAttack;
    }

    public void setPrefAttack(int prefAttack) {
        this.prefAttack = prefAttack;
    }

    public int getPrefPar() {
        return prefPar;
    }

    public void setPrefPar(int prefPar) {
        this.prefPar = prefPar;
    }

    public int getPrefEsq() {
        return prefEsq;
    }

    public void setPrefEsq(int prefEsq) {
        this.prefEsq = prefEsq;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public ArrayList<Recompense> getRecompenses_possibles(){return Recompenses_possibles;}

    public void setRecompenses_possibles(ArrayList<Recompense> newRecompensesPossibles){Recompenses_possibles=newRecompensesPossibles;}
}
