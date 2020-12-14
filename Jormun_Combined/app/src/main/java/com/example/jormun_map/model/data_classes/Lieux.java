package com.example.jormun_map.model.data_classes;

public class Lieux
{
    private int PosX;
    private int PosY;
    private int Type;
    private String Nom;
    public Lieux(int VPosX, int VPosY, int VType, String VNom)
    {
        this.setPosX(VPosX);
        this.setPosY(VPosY);
        this.setType(VType);
        this.setNom(VNom);
    }

    public int getPosX() {
        return PosX;
    }

    public void setPosX(int posX) {
        PosX = posX;
    }

    public int getPosY() {
        return PosY;
    }

    public void setPosY(int posY) {
        PosY = posY;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }
}
