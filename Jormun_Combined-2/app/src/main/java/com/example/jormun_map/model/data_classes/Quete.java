package com.example.jormun_map.model.data_classes;

public class Quete
{
    private String name;
    private String Cible;       //string nom mob;
    private int Compteur;       //nombre de cible tué
    private int But;            //nombre de cible à tué
    private int Coordx;
    private int Coordy;
    private Recompense gain;

    public Quete(String Vname, String VCible, int VCompteur, int VBut, int VCoordx, int VCoordy, Recompense Vgain)
    {
        this.setName(Vname);
        this.setCible(VCible);
        this.setCompteur(VCompteur);
        this.setBut(VBut);
        this.setCoordx(VCoordx);
        this.setCoordy(VCoordy);
        this.setGain(Vgain);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCible() {
        return Cible;
    }

    public void setCible(String cible) {
        Cible = cible;
    }

    public int getCompteur() {
        return Compteur;
    }

    public void setCompteur(int compteur) {
        Compteur = compteur;
    }

    public int getBut() {
        return But;
    }

    public void setBut(int but) {
        But = but;
    }

    public int getCoordx() {
        return Coordx;
    }

    public void setCoordx(int coordx) {
        Coordx = coordx;
    }

    public int getCoordy() {
        return Coordy;
    }

    public void setCoordy(int coordy) {
        Coordy = coordy;
    }

    public Recompense getGain() {
        return gain;
    }

    public void setGain(Recompense gain) {
        this.gain = gain;
    }
}
