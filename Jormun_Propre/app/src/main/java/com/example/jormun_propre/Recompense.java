package com.example.jormun_propre;

public class Recompense
{
    public String nom;
    public String description;
    public int quantite;
    public int categorie;
    public int Sprite;

    public Recompense(String Vnom, String Vdescription, int Vquantite, int Categorie)
    {
        this.nom = Vnom;
        this.description = Vdescription;
        this.quantite = Vquantite;
        this.categorie = Vquantite;
        this.Sprite = R.drawable.default_icon;
    }
    public void changeSprite(int newSprite)
    {
        this.Sprite = newSprite;
    }
}
