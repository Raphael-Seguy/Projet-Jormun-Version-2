package com.example.jormun_map.model.data_classes;

import java.util.ArrayList;

public class Utilisateur
{
    private ArrayList <Hero> ListHeros;
    private ArrayList <Ressources>Inventaire_ressources = new ArrayList();
    private ArrayList <Lieux> Liste_lieux;
    private ArrayList < Village> Liste_village;
    private ArrayList < Quete> Liste_quetes;

    private int PosX;
    private int PosY;

    public Utilisateur()
    {
        this.setListHeros(new ArrayList<Hero>());
        this.setInventaire_ressources(new ArrayList<Ressources>());
        this.setPosX(0);
        this.setPosY(0);
    }

    public void addinListHeros(Hero newHero)
    {
        if(this.getListHeros().size()<6)
        {
            this.getListHeros().add(newHero);
        }
    }

    public void addinInventaire_utilitaire(Ressources newRessources)
    {
        boolean found=false;

        for(int iCompteur = 0; iCompteur< this.getInventaire_ressources().size() && found==false; iCompteur++)
        {
            if(this.getInventaire_ressources().get(iCompteur).getNom() == newRessources.getNom())
            {
                this.getInventaire_ressources().get(iCompteur).setQuantite(this.getInventaire_ressources().get(iCompteur).getQuantite() + newRessources.getQuantite());
                found=true;
            }
        }

        if(found==false)
        {
            this.getInventaire_ressources().add(newRessources);
        }
    }

    public void addinList_lieux(Lieux nvLieux)
    {
        if(this.getListe_lieux().size()<8)
        {
            this.getListe_lieux().add(nvLieux);
        }
    }

    public void addinListe_village (Village nvVillage)
    {
        this.getListe_village().add(nvVillage);
    }

    public void addinListe_quetes (Quete newQuete)
    {
        this.getListe_quetes().add(newQuete);
    }

    public void setPosition(int CoordX, int CoordY)
    {
        this.setPosX(CoordX);
        this.setPosY(CoordY);
    }

    public ArrayList<Hero> getListHeros() {
        return ListHeros;
    }

    public void setListHeros(ArrayList<Hero> listHeros) {
        ListHeros = listHeros;
    }

    public ArrayList<Ressources> getInventaire_ressources() {
        return Inventaire_ressources;
    }

    public void setInventaire_ressources(ArrayList<Ressources> inventaire_ressources) {
        Inventaire_ressources = inventaire_ressources;
    }

    public ArrayList<Lieux> getListe_lieux() {
        return Liste_lieux;
    }

    public void setListe_lieux(ArrayList<Lieux> liste_lieux) {
        Liste_lieux = liste_lieux;
    }

    public ArrayList<Village> getListe_village() {
        return Liste_village;
    }

    public void setListe_village(ArrayList<Village> liste_village) {
        Liste_village = liste_village;
    }

    public ArrayList<Quete> getListe_quetes() {
        return Liste_quetes;
    }

    public void setListe_quetes(ArrayList<Quete> liste_quetes) {
        Liste_quetes = liste_quetes;
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
}
