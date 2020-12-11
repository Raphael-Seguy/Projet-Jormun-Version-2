package com.example.jormun_map.model.data_classes;

import java.util.ArrayList;

public class Village
{
    private String nom;
    private int lvl;
    private int pv;
    private ArrayList <Batiment> listBat;
    private int CoordX;
    private int CoordY;

    public Village(String Vnom, int Vlvl, int Vpv,int VCoordX, int VCoordY)
    {
        this.setNom(Vnom);
        this.setLvl(Vlvl);
        this.setPv(Vpv);
        this.setCoordX(VCoordX);
        this.setCoordY(VCoordY);
    }
    public void addBatiment (int Vtype, int Vlvl)
    {
        Batiment newBatiment = new Batiment(Vtype,Vlvl);
        this.getListBat().add(newBatiment);
    }
    public void addBatimentCible (Batiment newBatiment)
    {
        this.getListBat().add(newBatiment);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public ArrayList<Batiment> getListBat() {
        return listBat;
    }

    public void setListBat(ArrayList<Batiment> listBat) {
        this.listBat = listBat;
    }

    public int getCoordX() {
        return CoordX;
    }

    public void setCoordX(int coordX) {
        CoordX = coordX;
    }

    public int getCoordY() {
        return CoordY;
    }

    public void setCoordY(int coordY) {
        CoordY = coordY;
    }
}
