package com.example.jormun_propre;

import java.util.ArrayList;

public class Batiment
{
    int type;
    int lvl;
    int image;
    String nom;
    ArrayList <Ressources> ProdSem;

    public Batiment (int Type, int LVL)
    {
        this.type= Type;
        this.lvl = LVL;
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
                this.nom="taverne";
                this.image=R.drawable.taverne;
                this.ProdSem.add(batRess);
            }
            else if (lvl>=7)
            {
                this.nom="guilde des aventuriers";
                this.image=R.drawable.guilde_aventurier;
                this.ProdSem.add(batRess);
            }
            else
            {
                this.nom="auberge";
                this.image=R.drawable.auberge;
                this.ProdSem.add(batRess);
            }
        }
        else if(type==2)
        {
            if(lvl<4)
            {
                this.nom="réparateur";
                this.image=R.drawable.reprateur;
                this.ProdSem.add(batRess);
            }
            else if (lvl>=7)
            {
                this.nom="guilde des artisans";
                this.image=R.drawable.guilde_artisan;
                this.ProdSem.add(batRess);
            }
            else
            {
                this.nom="Forge";
                this.image=R.drawable.forgeron;
                this.ProdSem.add(batRess);
            }
        }
        else if(type==3)
        {
            if(lvl<4)
            {
                this.nom="cabane de bucheron";
                this.image=R.drawable.cabane_bucheron;
                this.ProdSem.add(batRess);
            }
            else
            {
                this.nom="Scierie";
                this.image=R.drawable.scierie;
                this.ProdSem.add(batRess);
            }
        }
        else if (type==4)
        {
            if(lvl<4)
            {
                this.nom="baricade";
                this.image=R.drawable.baricade;
                this.ProdSem.add(batRess);
            }
            else if (lvl>=7)
            {
                this.nom="Enceinte";
                this.image=R.drawable.enceinte;
                this.ProdSem.add(batRess);
            }
            else
            {
                this.nom="Muraille";
                this.image=R.drawable.muraille;
                this.ProdSem.add(batRess);
            }
        }
        else if (type==5)
        {
            if(lvl<4)
            {
                this.nom="échoppe";
                this.image=R.drawable.echoppe;
                this.ProdSem.add(batRess);
            }
            else if (lvl>=7)
            {
                this.nom="Marché";
                this.image=R.drawable.marche;
                this.ProdSem.add(batRess);
            }
            else
            {
                this.nom="Magasin";
                this.image=R.drawable.magasin;
                this.ProdSem.add(batRess);
            }
        }
        else if(type==6)
        {
            if(lvl<4)
            {
                this.nom="Mine";
                this.image=R.drawable.mine;
                this.ProdSem.add(batRess);
            }
            else
            {
                this.nom="Carrière";
                this.image=R.drawable.cariere;
                this.ProdSem.add(batRess);
            }
        }
        else
        {
            this.nom="enchanteur";
            this.image=R.drawable.enchanteur;
            this.ProdSem.add(batRess);
        }

    }

}
