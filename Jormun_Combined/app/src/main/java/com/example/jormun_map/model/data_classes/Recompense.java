package com.example.jormun_map.model.data_classes;

import com.example.jormun_map.R;

public class Recompense //objet pouvant être des utilitaires, des ressources, des equipements
{
    private String nom;
    private String description;
    private int quantite;
    private int categorie;
    private int Sprite;

    public Recompense(String Vnom, String Vdescription, int Vquantite, int Categorie)
    {
        this.setNom(Vnom);
        this.setDescription(Vdescription);
        this.setQuantite(Vquantite);
        this.setCategorie(Vquantite);
        this.setSprite(R.drawable.default_icon);
    }
    public void changeSprite(int newSprite)
    {
        this.setSprite(newSprite);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getSprite() {
        return Sprite;
    }

    public void setSprite(int sprite) {
        Sprite = sprite;
    }
}
