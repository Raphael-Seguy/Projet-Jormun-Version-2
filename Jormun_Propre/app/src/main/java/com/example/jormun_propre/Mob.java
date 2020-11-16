package com.example.jormun_propre;

public class Mob extends Personnage
{
    int prefMag;
    int prefDef;
    int prefAttack;
    int prefPar;
    int prefEsq;

    String race;

    public Mob(int Sprite, int VmaxHP, int Vactuhp , int Vlvl, int Varmure, int VarmureMag, int Vdegat, int Vesquive, int armure_plus, double armure_mult, int armuremag_plus, double armuremag_mult, int esquive_plus, double esquive_mult, int degat_plus, double degat_mult, int degatmag_plus, double degatmag_mult, int VprefMag, int VprefDef, int VprefAttack, int VprefPar, int VprefEsq,String Vrace)
    {
        super(Sprite, VmaxHP, Vactuhp , Vlvl, Varmure, VarmureMag, Vdegat, Vesquive, armure_plus, armure_mult, armuremag_plus, armuremag_mult, esquive_plus, esquive_mult, degat_plus, degat_mult, degatmag_plus, degatmag_mult, Vrace);
        this.prefMag = VprefMag;
        this.prefDef = VprefDef;
        this.prefAttack = VprefAttack;
        this.prefPar = VprefPar;
        this.prefEsq = VprefEsq;
        this.race = Vrace;
    }

    public Mob() {

    }
}
