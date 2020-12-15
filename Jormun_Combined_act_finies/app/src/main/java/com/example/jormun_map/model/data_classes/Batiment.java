package com.example.jormun_map.model.data_classes;

import com.example.jormun_map.R;

import java.util.ArrayList;

public class Batiment
{
    private int type;
    private int lvl;
    private int image;
    private String nom;
    private ArrayList <Ressources> ProdSem = new ArrayList<Ressources>();

    public Batiment (int Type, int LVL)
    {
        this.setType(Type);
        this.setLvl(LVL);
        SetNameBat(Type,LVL);
    }

    public void SetNameBat(int type, int lvl) // pour le moment tous les batiments produisent la même ressources
    {
        Ressources batRess;

        batRess = new Ressources( 10,  "or",  "ca brille");
        if(type==1)
        {
            if(lvl<4)
            {
                this.setNom("taverne");
                this.setImage(R.drawable.taverne);
                this.getProdSem().add(batRess);
            }
            else if (lvl>=7)
            {
                this.setNom("guilde des aventuriers");
                this.setImage(R.drawable.guilde_aventurier);
                this.getProdSem().add(batRess);
            }
            else
            {
                this.setNom("auberge");
                this.setImage(R.drawable.auberge);
                this.getProdSem().add(batRess);
            }
        }
        else if(type==2)
        {
            if(lvl<4)
            {
                this.setNom("réparateur");
                this.setImage(R.drawable.reprateur);
                this.getProdSem().add(batRess);
            }
            else if (lvl>=7)
            {
                this.setNom("guilde des artisans");
                this.setImage(R.drawable.guilde_artisan);
                this.getProdSem().add(batRess);
            }
            else
            {
                this.setNom("Forge");
                this.setImage(R.drawable.forgeron);
                this.getProdSem().add(batRess);
            }
        }
        else if(type==3)
        {
            if(lvl<4)
            {
                this.setNom("cabane de bucheron");
                this.setImage(R.drawable.cabane_bucheron);
                this.getProdSem().add(batRess);
            }
            else
            {
                this.setNom("Scierie");
                this.setImage(R.drawable.scierie);
                this.getProdSem().add(batRess);
            }
        }
        else if (type==4)
        {
            if(lvl<4)
            {
                this.setNom("baricade");
                this.setImage(R.drawable.baricade);
                this.getProdSem().add(batRess);
            }
            else if (lvl>=7)
            {
                this.setNom("Enceinte");
                this.setImage(R.drawable.enceinte);
                this.getProdSem().add(batRess);
            }
            else
            {
                this.setNom("Muraille");
                this.setImage(R.drawable.muraille);
                this.getProdSem().add(batRess);
            }
        }
        else if (type==5)
        {
            if(lvl<4)
            {
                this.setNom("échoppe");
                this.setImage(R.drawable.echoppe);
                this.getProdSem().add(batRess);
            }
            else if (lvl>=7)
            {
                this.setNom("Marché");
                this.setImage(R.drawable.marche);
                this.getProdSem().add(batRess);
            }
            else
            {
                this.setNom("Magasin");
                this.setImage(R.drawable.magasin);
                this.getProdSem().add(batRess);
            }
        }
        else if(type==6)
        {
            if(lvl<4)
            {
                this.setNom("Mine");
                this.setImage(R.drawable.mine);
                this.getProdSem().add(batRess);
            }
            else
            {
                this.setNom("Carrière");
                this.setImage(R.drawable.cariere);
                this.getProdSem().add(batRess);
            }
        }
        else
        {
            this.setNom("enchanteur");
            this.setImage(R.drawable.enchanteur);
            this.getProdSem().add(batRess);
        }

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Ressources> getProdSem() {
        return ProdSem;
    }

    public void setProdSem(ArrayList<Ressources> prodSem) {
        ProdSem = prodSem;
    }
}
