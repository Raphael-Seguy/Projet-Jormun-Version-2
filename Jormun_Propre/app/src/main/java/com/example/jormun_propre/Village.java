package com.example.jormun_propre;

import java.util.ArrayList;

public class Village
{
    String nom;
    int lvl;
    int pv;
    ArrayList <Batiment> listBat;
    int CoordX;
    int CoordY;

    public Village(String Vnom, int Vlvl, int Vpv,int VCoordX, int VCoordY)
    {
        this.nom = Vnom;
        this.lvl = Vlvl;
        this.pv=Vpv;
        this.CoordX = VCoordX;
        this.CoordY = VCoordY;
    }
    public void addBatiment (int Vtype, int Vlvl)
    {
        Batiment newBatiment = new Batiment(Vtype,Vlvl);
        this.listBat.add(newBatiment);
    }
    public void addBatimentCible (Batiment newBatiment)
    {
        this.listBat.add(newBatiment);
    }

}
