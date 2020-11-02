package com.example.jormun_propre;

public class Hero extends Personnage
{


    String Classe0;
    int lvlClasse0;
    String Classe1;
    int lvlClasse1;

    String name;

    Object object0;
    Object object1;
    Object object2;
    Object object3;

    public Hero(int VmaxHP, int Vactuhp , int Vlvl, int Varmure, int VarmureMag, int Vdegat, int Vesquive, int armure_plus, double armure_mult, int armuremag_plus, double armuremag_mult, int esquive_plus, double esquive_mult, int degat_plus, double degat_mult, int degatmag_plus, double degatmag_mult, String Classe0, int lvlClasse0, String Classe1, int lvlClasse1, String Nom)
    {
        super(VmaxHP, Vactuhp , Vlvl, Varmure, VarmureMag, Vdegat, Vesquive, armure_plus, armure_mult, armuremag_plus, armuremag_mult, esquive_plus, esquive_mult, degat_plus, degat_mult, degatmag_plus, degatmag_mult, Nom);


        this.Classe0= Classe0;
        this.lvlClasse0= lvlClasse0;
        this.Classe1= Classe1;
        this.lvlClasse1= lvlClasse1;
        this.name = Nom;

    }

    public Hero() {
        super();
    }

    public void addObject(Object newObject)
    {
        if(object0 == null)
        {
            this.object0 = newObject;
        }
        else if(object1 == null)
        {
            this.object1 = newObject;
        }
        else if(object2 == null)
        {
            this.object2 = newObject;
        }
        else if(object3 == null)
        {
            this.object3 = newObject;
        }
    }
    public void changeObject(int numero, Object newObject)
    {
        if(numero==0)
        {
            this.object0 = newObject;
        }
        else if(numero==1)
        {
            this.object1 = newObject;
        }
        else if(numero==2)
        {
            this.object2 = newObject;
        }
        else if(numero==3)
        {
            this.object3= newObject;
        }
    }

}
