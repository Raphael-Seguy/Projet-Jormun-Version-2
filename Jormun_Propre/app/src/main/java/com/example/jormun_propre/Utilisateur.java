package com.example.jormun_propre;

import java.util.ArrayList;

public class Utilisateur
{
    ArrayList <Hero> ListHeros;
    ArrayList <Ressources>Inventaire_ressources = new ArrayList();
    ArrayList <Lieux> Liste_lieux;
    ArrayList < Village> Liste_village;
    ArrayList < Quete> Liste_quetes;

    int PosX;
    int PosY;

    public Utilisateur()
    {
        this.ListHeros = new ArrayList<Hero>();
        this.Inventaire_ressources = new ArrayList<Ressources>();
        this.PosX=0;
        this.PosY=0;
    }

    public void addinListHeros(Hero newHero)
    {
        if(this.ListHeros.size()<6)
        {
            this.ListHeros.add(newHero);
        }
    }

    public void addinInventaire_utilitaire(Ressources newRessources)
    {
        boolean found=false;

        for(int iCompteur=0; iCompteur<this.Inventaire_ressources.size() && found==false;iCompteur++)
        {
            if(this.Inventaire_ressources.get(iCompteur).nom == newRessources.nom)
            {
                this.Inventaire_ressources.get(iCompteur).quantite += newRessources.quantite;
                found=true;
            }
        }

        if(found==false)
        {
            this.Inventaire_ressources.add(newRessources);
        }
    }

    public void addinList_lieux(Lieux nvLieux)
    {
        if(this.Liste_lieux.size()<8)
        {
            this.Liste_lieux.add(nvLieux);
        }
    }

    public void addinListe_village (Village nvVillage)
    {
        this.Liste_village.add(nvVillage);
    }

    public void addinListe_quetes (Quete newQuete)
    {
        this.Liste_quetes.add(newQuete);
    }

    public void setPosition(int CoordX, int CoordY)
    {
        this.PosX = CoordX;
        this.PosY = CoordY;
    }
}
