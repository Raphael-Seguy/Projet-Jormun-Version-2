package com.example.jormun_propre;

public class Quete
{
    String name;
    String Cible;       //string nom mob;
    int Compteur;       //nombre de cible tué
    int But;            //nombre de cible à tué
    int Coordx;
    int Coordy;
    Recompense gain;

    public Quete(String Vname, String VCible, int VCompteur, int VBut, int VCoordx, int VCoordy, Recompense Vgain)
    {
        this.name = Vname;
        this.Cible = VCible;
        this.Compteur = VCompteur;
        this.But = VBut;
        this.Coordx = VCoordx;
        this.Coordy = VCoordy;
        this.gain = Vgain;
    }
}
