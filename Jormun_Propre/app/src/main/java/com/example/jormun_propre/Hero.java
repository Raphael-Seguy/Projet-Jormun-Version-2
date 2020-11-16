package com.example.jormun_propre;

import java.util.ArrayList;

public class Hero extends Personnage
{

    String Classe0;
    int lvlClasse0;
    String Classe1;
    int lvlClasse1;

    String name;
    String description;

    Utilitaire utilitaire0;
    Utilitaire utilitaire1;
    Utilitaire utilitaire2;
    Utilitaire utilitaire3;

    ArrayList <Recompense>Inventaire = new ArrayList();
    ArrayList <Equipement>Equipe = new ArrayList();
    ArrayList <Spell>ListdeSpells = new ArrayList();

    boolean Travel;


    public Hero(int Sprite, int VmaxHP, int Vactuhp , int Vlvl, int Varmure, int VarmureMag, int Vdegat, int Vesquive, int armure_plus, double armure_mult, int armuremag_plus, double armuremag_mult, int esquive_plus, double esquive_mult, int degat_plus, double degat_mult, int degatmag_plus, double degatmag_mult, String Classe0, int lvlClasse0, String Classe1, int lvlClasse1, String Nom)
    {
        super(Sprite, VmaxHP, Vactuhp , Vlvl, Varmure, VarmureMag, Vdegat, Vesquive, armure_plus, armure_mult, armuremag_plus, armuremag_mult, esquive_plus, esquive_mult, degat_plus, degat_mult, degatmag_plus, degatmag_mult, Nom);

        this.Classe0= Classe0;
        this.lvlClasse0= lvlClasse0;
        this.Classe1= Classe1;
        this.lvlClasse1= lvlClasse1;
        this.name = Nom;
        this.Travel =false;
        this.description = "";
    }

    public Hero() {
        super();
    }


    public void addinInventaire(Recompense newObjet)
    {
        boolean found=false;

        for(int iCompteur=0; iCompteur<this.Inventaire.size() && found==false;iCompteur++)
        {
            if(this.Inventaire.get(iCompteur).nom == newObjet.nom)
            {
                this.Inventaire.get(iCompteur).quantite += newObjet.quantite;
                found=true;
            }
        }

        if(found==false)
        {
            this.Inventaire.add(newObjet);
        }
    }

    ////GESTION EQUIPEMENT
    public void CreateEquipement() //seulement quand hero est nouveau
    {
        ////anneau droit
        this.Equipe.add(null);
        ///anneau gauche
        this.Equipe.add(null);
        ///arme droite
        this.Equipe.add(null);
        //arme gauche
        this.Equipe.add(null);
        //jambe
        this.Equipe.add(null);
        ///torse
        this.Equipe.add(null);
        //tête
        this.Equipe.add(null);
    }
    public void ChangeEquipement(int categorie, Equipement equipement_choisis)
    {
        if(this.Equipe.get(categorie)==null)
        {
            this.Equipe.set(categorie,equipement_choisis);
            this.Inventaire.remove(equipement_choisis);
        }
        else
        {
            ChangeStat(this.Equipe.get(categorie),true);
            this.Inventaire.add(this.Equipe.get(categorie));

            this.Equipe.set(categorie,equipement_choisis);
            ChangeStat(this.Equipe.get(categorie),false);
            this.Inventaire.remove(this.Equipe.get(categorie));
        }
    }

    public void ChangeStat (Equipement equipement_enleve, Boolean enleveStat)
    {
        if(enleveStat==true)
        {
            equipement_enleve = inverseStat(equipement_enleve);
        }

        //verif les bonus de l'equipement
        if(equipement_enleve.effet_Bonus.armure_plus !=0)
        {
            this.armure_plus += equipement_enleve.effet_Bonus.armure_plus;
        }
        if(equipement_enleve.effet_Bonus.armure_mult !=0)
        {
            this.armure_mult += equipement_enleve.effet_Bonus.armure_mult;
        }
        if(equipement_enleve.effet_Bonus.armureMag_plus !=0)
        {
            this.armuremag_plus += equipement_enleve.effet_Bonus.armureMag_plus;
        }
        if(equipement_enleve.effet_Bonus.armureMag_mult !=0)
        {
            this.armuremag_mult += equipement_enleve.effet_Bonus.armureMag_mult;
        }
        if(equipement_enleve.effet_Bonus.degat_plus !=0)
        {
            this.degat_plus += equipement_enleve.effet_Bonus.degat_plus;
        }
        if(equipement_enleve.effet_Bonus.degat_mult !=0)
        {
            this.degat_mult += equipement_enleve.effet_Bonus.degat_mult;
        }
        if(equipement_enleve.effet_Bonus.degatMag_plus !=0)
        {
            this.degatmag_plus += equipement_enleve.effet_Bonus.degatMag_plus;
        }
        if(equipement_enleve.effet_Bonus.degatMag_mult !=0)
        {
            this.degatmag_mult += equipement_enleve.effet_Bonus.degatMag_mult;
        }
        if(equipement_enleve.effet_Bonus.esquive_plus !=0)
        {
            this.esquive_plus += equipement_enleve.effet_Bonus.esquive_plus;
        }
        if(equipement_enleve.effet_Bonus.esquive_mult !=0)
        {
            this.esquive_mult += equipement_enleve.effet_Bonus.esquive_mult;
        }

        //verif les malus de l'equipement

        if(equipement_enleve.effet_Malus.armure_moins!=0)
        {
            this.armure_plus += equipement_enleve.effet_Malus.armure_moins;
        }
        if(equipement_enleve.effet_Malus.armure_div!=0)
        {
            this.armure_mult += equipement_enleve.effet_Malus.armure_div;
        }
        if(equipement_enleve.effet_Malus.armureMag_moins!=0)
        {
            this.armuremag_plus += equipement_enleve.effet_Malus.armureMag_moins;
        }
        if(equipement_enleve.effet_Malus.armureMag_div !=0)
        {
            this.armuremag_mult += equipement_enleve.effet_Malus.armureMag_div;
        }
        if(equipement_enleve.effet_Malus.esquive_moins!=0)
        {
            this.esquive_plus += equipement_enleve.effet_Malus.esquive_moins;
        }
        if(equipement_enleve.effet_Malus.esquive_div!=0)
        {
            this.esquive_mult += equipement_enleve.effet_Malus.esquive_div;
        }
        if(equipement_enleve.effet_Malus.degat_moins!=0)
        {
            this.degat_plus += equipement_enleve.effet_Malus.degat_moins;
        }
        if(equipement_enleve.effet_Malus.degat_div!=0)
        {
            this.degat_mult += equipement_enleve.effet_Malus.degat_div;
        }
        if(equipement_enleve.effet_Malus.degatMag_moins!=0)
        {
            this.degatmag_plus += equipement_enleve.effet_Malus.degatMag_moins;
        }
        if(equipement_enleve.effet_Malus.degatMag_div!=0)
        {
            this.degatmag_mult += equipement_enleve.effet_Malus.degatMag_div;
        }
    }
    public Equipement inverseStat(Equipement equipement_ajoute) //SOLUTION MEUILLEUR BIENVENUE
    {
        Equipement equipement_a_inverse = equipement_ajoute;
        if(equipement_a_inverse.effet_Bonus.armure_plus !=0)
        {
            equipement_a_inverse.effet_Bonus.armure_plus = -equipement_a_inverse.effet_Bonus.armure_plus;
        }
        if(equipement_a_inverse.effet_Bonus.armure_mult !=0)
        {
            equipement_a_inverse.effet_Bonus.armure_mult = -equipement_a_inverse.effet_Bonus.armure_mult;
        }
        if(equipement_a_inverse.effet_Bonus.armureMag_plus !=0)
        {
            equipement_a_inverse.effet_Bonus.armureMag_plus = -equipement_a_inverse.effet_Bonus.armureMag_plus;
        }
        if(equipement_a_inverse.effet_Bonus.armureMag_mult !=0)
        {
            equipement_a_inverse.effet_Bonus.armureMag_mult = -equipement_a_inverse.effet_Bonus.armureMag_mult;
        }
        if(equipement_a_inverse.effet_Bonus.degat_plus !=0)
        {
            equipement_a_inverse.effet_Bonus.degat_plus = -equipement_a_inverse.effet_Bonus.degat_plus;
        }
        if(equipement_a_inverse.effet_Bonus.degat_mult !=0)
        {
            equipement_a_inverse.effet_Bonus.degat_mult = -equipement_a_inverse.effet_Bonus.degat_mult;
        }
        if(equipement_a_inverse.effet_Bonus.degatMag_plus !=0)
        {
            equipement_a_inverse.effet_Bonus.degatMag_plus = -equipement_a_inverse.effet_Bonus.degatMag_plus;
        }
        if(equipement_a_inverse.effet_Bonus.degatMag_mult !=0)
        {
            equipement_a_inverse.effet_Bonus.degatMag_mult = -equipement_a_inverse.effet_Bonus.degatMag_mult;
        }
        if(equipement_a_inverse.effet_Bonus.esquive_plus !=0)
        {
            equipement_a_inverse.effet_Bonus.esquive_plus = -equipement_a_inverse.effet_Bonus.esquive_plus;
        }
        if(equipement_a_inverse.effet_Bonus.esquive_mult !=0)
        {
            equipement_a_inverse.effet_Bonus.esquive_mult = -equipement_a_inverse.effet_Bonus.esquive_mult;
        }

        if(equipement_a_inverse.effet_Malus.armure_moins!=0)
        {
            equipement_a_inverse.effet_Malus.armure_moins = -equipement_a_inverse.effet_Malus.armure_moins;
        }
        if(equipement_a_inverse.effet_Malus.armure_div!=0)
        {
            equipement_a_inverse.effet_Malus.armure_div = -equipement_a_inverse.effet_Malus.armure_div;
        }
        if(equipement_a_inverse.effet_Malus.armureMag_moins!=0)
        {
            equipement_a_inverse.effet_Malus.armureMag_moins = -equipement_a_inverse.effet_Malus.armureMag_moins;
        }
        if(equipement_a_inverse.effet_Malus.armureMag_div !=0)
        {
            equipement_a_inverse.effet_Malus.armureMag_div = -equipement_a_inverse.effet_Malus.armureMag_div;
        }
        if(equipement_a_inverse.effet_Malus.esquive_moins!=0)
        {
            equipement_a_inverse.effet_Malus.esquive_moins = -equipement_a_inverse.effet_Malus.esquive_moins;
        }
        if(equipement_a_inverse.effet_Malus.esquive_div!=0)
        {
            equipement_a_inverse.effet_Malus.esquive_div = -equipement_a_inverse.effet_Malus.esquive_div;
        }
        if(equipement_a_inverse.effet_Malus.degat_moins!=0)
        {
            equipement_a_inverse.effet_Malus.degat_moins = -equipement_a_inverse.effet_Malus.degat_moins;
        }
        if(equipement_a_inverse.effet_Malus.degat_div!=0)
        {
            equipement_a_inverse.effet_Malus.degat_div = -equipement_a_inverse.effet_Malus.degat_div;
        }
        if(equipement_a_inverse.effet_Malus.degatMag_moins!=0)
        {
            equipement_a_inverse.effet_Malus.degatMag_moins = -equipement_a_inverse.effet_Malus.degatMag_moins;
        }
        if(equipement_a_inverse.effet_Malus.degatMag_div!=0)
        {
            equipement_a_inverse.effet_Malus.degatMag_div = -equipement_a_inverse.effet_Malus.degatMag_div;
        }

        return equipement_a_inverse;
    }


    public void addinListSpell(Spell newSpell)
    {
        this.ListdeSpells.add(newSpell);
    }

    public void changeSpellChoisis(int positionSelected ,int emplacement)
    {
        if(positionSelected ==0)
        {
            this.spel0= this.ListdeSpells.get(emplacement);
        }
        else if(positionSelected==1)
        {
            this.spel1= this.ListdeSpells.get(emplacement);
        }
        else if(positionSelected==2)
        {
            this.spel2= this.ListdeSpells.get(emplacement);
        }
        else
        {
            this.spel3= this.ListdeSpells.get(emplacement);
        }
    }

    public void addUtilitaire(Utilitaire newUtilitaire)
    {
        if(utilitaire0 == null)
        {
            this.utilitaire0 = newUtilitaire;
        }
        else if(utilitaire1 == null)
        {
            this.utilitaire1 = newUtilitaire;
        }
        else if(utilitaire2 == null)
        {
            this.utilitaire2 = newUtilitaire;
        }
        else if(utilitaire3 == null)
        {
            this.utilitaire3 = newUtilitaire;
        }
    }

    public void changeUtilitaireChoisis(int positionSelected ,Utilitaire utilitaireSelectionne)
    {
        this.Inventaire.remove(utilitaireSelectionne);

        if(positionSelected ==0)
        {
            this.Inventaire.add(this.utilitaire0);
            this.utilitaire0= utilitaireSelectionne;
        }
        else if(positionSelected==1)
        {
            this.Inventaire.add(this.utilitaire1);
            this.utilitaire1= utilitaireSelectionne;
        }
        else if(positionSelected==2)
        {
            this.Inventaire.add(this.utilitaire2);
            this.utilitaire2= utilitaireSelectionne;
        }
        else
        {
            this.Inventaire.add(this.utilitaire3);
            this.utilitaire3= utilitaireSelectionne;
        }
    }

    public void setTravel(boolean newState)
    {
        this.Travel = newState;
    }

    public void setDescription(String newDescription)
    {
        this.description = newDescription;
    }
}
