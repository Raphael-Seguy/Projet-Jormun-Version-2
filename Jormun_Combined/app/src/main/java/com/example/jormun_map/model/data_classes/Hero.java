package com.example.jormun_map.model.data_classes;

import java.util.ArrayList;

public class Hero extends Personnage
{

    private String Classe0;
    private int lvlClasse0;
    private String Classe1;
    private int lvlClasse1;

    private String name;
    private String description;

    private Utilitaire utilitaire0;
    private Utilitaire utilitaire1;
    private Utilitaire utilitaire2;
    private Utilitaire utilitaire3;

    private ArrayList <Recompense>Inventaire = new ArrayList();
    private ArrayList <Equipement>Equipe = new ArrayList();
    private ArrayList <Spell>ListdeSpells = new ArrayList();

    private boolean Travel;


    public Hero(int Sprite, int VmaxHP, int Vactuhp , int Vlvl, int Varmure, int VarmureMag, int Vdegat, int Vesquive, int armure_plus, double armure_mult, int armuremag_plus, double armuremag_mult, int esquive_plus, double esquive_mult, int degat_plus, double degat_mult, int degatmag_plus, double degatmag_mult, String Classe0, int lvlClasse0, String Classe1, int lvlClasse1, String Nom)
    {
        super(Sprite, VmaxHP, Vactuhp , Vlvl, Varmure, VarmureMag, Vdegat, Vesquive, armure_plus, armure_mult, armuremag_plus, armuremag_mult, esquive_plus, esquive_mult, degat_plus, degat_mult, degatmag_plus, degatmag_mult, Nom);

        this.setClasse0(Classe0);
        this.setLvlClasse0(lvlClasse0);
        this.setClasse1(Classe1);
        this.setLvlClasse1(lvlClasse1);
        this.setName(Nom);
        this.setTravel(false);
        this.setDescription("");
    }

    public Hero() {
        super();
    }


    public void addinInventaire(Recompense newObjet)
    {
        boolean found=false;

        for(int iCompteur = 0; iCompteur< this.getInventaire().size() && found==false; iCompteur++)
        {
            if(this.getInventaire().get(iCompteur).getNom() == newObjet.getNom())
            {
                this.getInventaire().get(iCompteur).setQuantite(this.getInventaire().get(iCompteur).getQuantite() + newObjet.getQuantite());
                found=true;
            }
        }

        if(found==false)
        {
            this.getInventaire().add(newObjet);
        }
    }

    ////GESTION EQUIPEMENT
    public void CreateEquipement() //seulement quand hero est nouveau
    {
        ////anneau droit
        this.getEquipe().add(null);
        ///anneau gauche
        this.getEquipe().add(null);
        ///arme droite
        this.getEquipe().add(null);
        //arme gauche
        this.getEquipe().add(null);
        //jambe
        this.getEquipe().add(null);
        ///torse
        this.getEquipe().add(null);
        //tête
        this.getEquipe().add(null);
    }
    public void ChangeEquipement(int categorie, Equipement equipement_choisis)
    {
        if(this.getEquipe().get(categorie)==null)
        {
            this.getEquipe().set(categorie,equipement_choisis);
            this.getInventaire().remove(equipement_choisis);
        }
        else
        {
            ChangeStat(this.getEquipe().get(categorie),true);
            this.getInventaire().add(this.getEquipe().get(categorie));

            this.getEquipe().set(categorie,equipement_choisis);
            ChangeStat(this.getEquipe().get(categorie),false);
            this.getInventaire().remove(this.getEquipe().get(categorie));
        }
    }

    public void ChangeStat (Equipement equipement_enleve, Boolean enleveStat)
    {
        if(enleveStat==true)
        {
            equipement_enleve = inverseStat(equipement_enleve);
        }

        //verif les bonus de l'equipement
        if(equipement_enleve.effet_Bonus.getArmure_plus() !=0)
        {
            this.setArmure_plus(this.getArmure_plus() + equipement_enleve.effet_Bonus.getArmure_plus());
        }
        if(equipement_enleve.effet_Bonus.getArmure_mult() !=0)
        {
            this.setArmure_mult(this.getArmure_mult() + equipement_enleve.effet_Bonus.getArmure_mult());
        }
        if(equipement_enleve.effet_Bonus.getArmureMag_plus() !=0)
        {
            this.setArmuremag_plus(this.getArmuremag_plus() + equipement_enleve.effet_Bonus.getArmureMag_plus());
        }
        if(equipement_enleve.effet_Bonus.getArmureMag_mult() !=0)
        {
            this.setArmuremag_mult(this.getArmuremag_mult() + equipement_enleve.effet_Bonus.getArmureMag_mult());
        }
        if(equipement_enleve.effet_Bonus.getDegat_plus() !=0)
        {
            this.setDegat_plus(this.getDegat_plus() + equipement_enleve.effet_Bonus.getDegat_plus());
        }
        if(equipement_enleve.effet_Bonus.getDegat_mult() !=0)
        {
            this.setDegat_mult(this.getDegat_mult() + equipement_enleve.effet_Bonus.getDegat_mult());
        }
        if(equipement_enleve.effet_Bonus.getDegatMag_plus() !=0)
        {
            this.setDegatmag_plus(this.getDegatmag_plus() + equipement_enleve.effet_Bonus.getDegatMag_plus());
        }
        if(equipement_enleve.effet_Bonus.getDegatMag_mult() !=0)
        {
            this.setDegatmag_mult(this.getDegatmag_mult() + equipement_enleve.effet_Bonus.getDegatMag_mult());
        }
        if(equipement_enleve.effet_Bonus.getEsquive_plus() !=0)
        {
            this.setEsquive_plus(this.getEsquive_plus() + equipement_enleve.effet_Bonus.getEsquive_plus());
        }
        if(equipement_enleve.effet_Bonus.getEsquive_mult() !=0)
        {
            this.setEsquive_mult(this.getEsquive_mult() + equipement_enleve.effet_Bonus.getEsquive_mult());
        }

        //verif les malus de l'equipement

        if(equipement_enleve.effet_Malus.getArmure_moins() !=0)
        {
            this.setArmure_plus(this.getArmure_plus() + equipement_enleve.effet_Malus.getArmure_moins());
        }
        if(equipement_enleve.effet_Malus.getArmure_div() !=0)
        {
            this.setArmure_mult(this.getArmure_mult() + equipement_enleve.effet_Malus.getArmure_div());
        }
        if(equipement_enleve.effet_Malus.getArmureMag_moins() !=0)
        {
            this.setArmuremag_plus(this.getArmuremag_plus() + equipement_enleve.effet_Malus.getArmureMag_moins());
        }
        if(equipement_enleve.effet_Malus.getArmureMag_div() !=0)
        {
            this.setArmuremag_mult(this.getArmuremag_mult() + equipement_enleve.effet_Malus.getArmureMag_div());
        }
        if(equipement_enleve.effet_Malus.getEsquive_moins() !=0)
        {
            this.setEsquive_plus(this.getEsquive_plus() + equipement_enleve.effet_Malus.getEsquive_moins());
        }
        if(equipement_enleve.effet_Malus.getEsquive_div() !=0)
        {
            this.setEsquive_mult(this.getEsquive_mult() + equipement_enleve.effet_Malus.getEsquive_div());
        }
        if(equipement_enleve.effet_Malus.getDegat_moins() !=0)
        {
            this.setDegat_plus(this.getDegat_plus() + equipement_enleve.effet_Malus.getDegat_moins());
        }
        if(equipement_enleve.effet_Malus.getDegat_div() !=0)
        {
            this.setDegat_mult(this.getDegat_mult() + equipement_enleve.effet_Malus.getDegat_div());
        }
        if(equipement_enleve.effet_Malus.getDegatMag_moins() !=0)
        {
            this.setDegatmag_plus(this.getDegatmag_plus() + equipement_enleve.effet_Malus.getDegatMag_moins());
        }
        if(equipement_enleve.effet_Malus.getDegatMag_div() !=0)
        {
            this.setDegatmag_mult(this.getDegatmag_mult() + equipement_enleve.effet_Malus.getDegatMag_div());
        }
    }
    public Equipement inverseStat(Equipement equipement_ajoute) //SOLUTION MEUILLEUR BIENVENUE
    {
        Equipement equipement_a_inverse = equipement_ajoute;
        if(equipement_a_inverse.effet_Bonus.getArmure_plus() !=0)
        {
            equipement_a_inverse.effet_Bonus.setArmure_plus(-equipement_a_inverse.effet_Bonus.getArmure_plus());
        }
        if(equipement_a_inverse.effet_Bonus.getArmure_mult() !=0)
        {
            equipement_a_inverse.effet_Bonus.setArmure_mult(-equipement_a_inverse.effet_Bonus.getArmure_mult());
        }
        if(equipement_a_inverse.effet_Bonus.getArmureMag_plus() !=0)
        {
            equipement_a_inverse.effet_Bonus.setArmureMag_plus(-equipement_a_inverse.effet_Bonus.getArmureMag_plus());
        }
        if(equipement_a_inverse.effet_Bonus.getArmureMag_mult() !=0)
        {
            equipement_a_inverse.effet_Bonus.setArmureMag_mult(-equipement_a_inverse.effet_Bonus.getArmureMag_mult());
        }
        if(equipement_a_inverse.effet_Bonus.getDegat_plus() !=0)
        {
            equipement_a_inverse.effet_Bonus.setDegat_plus(-equipement_a_inverse.effet_Bonus.getDegat_plus());
        }
        if(equipement_a_inverse.effet_Bonus.getDegat_mult() !=0)
        {
            equipement_a_inverse.effet_Bonus.setDegat_mult(-equipement_a_inverse.effet_Bonus.getDegat_mult());
        }
        if(equipement_a_inverse.effet_Bonus.getDegatMag_plus() !=0)
        {
            equipement_a_inverse.effet_Bonus.setDegatMag_plus(-equipement_a_inverse.effet_Bonus.getDegatMag_plus());
        }
        if(equipement_a_inverse.effet_Bonus.getDegatMag_mult() !=0)
        {
            equipement_a_inverse.effet_Bonus.setDegatMag_mult(-equipement_a_inverse.effet_Bonus.getDegatMag_mult());
        }
        if(equipement_a_inverse.effet_Bonus.getEsquive_plus() !=0)
        {
            equipement_a_inverse.effet_Bonus.setEsquive_plus(-equipement_a_inverse.effet_Bonus.getEsquive_plus());
        }
        if(equipement_a_inverse.effet_Bonus.getEsquive_mult() !=0)
        {
            equipement_a_inverse.effet_Bonus.setEsquive_mult(-equipement_a_inverse.effet_Bonus.getEsquive_mult());
        }

        if(equipement_a_inverse.effet_Malus.getArmure_moins() !=0)
        {
            equipement_a_inverse.effet_Malus.setArmure_moins(-equipement_a_inverse.effet_Malus.getArmure_moins());
        }
        if(equipement_a_inverse.effet_Malus.getArmure_div() !=0)
        {
            equipement_a_inverse.effet_Malus.setArmure_div(-equipement_a_inverse.effet_Malus.getArmure_div());
        }
        if(equipement_a_inverse.effet_Malus.getArmureMag_moins() !=0)
        {
            equipement_a_inverse.effet_Malus.setArmureMag_moins(-equipement_a_inverse.effet_Malus.getArmureMag_moins());
        }
        if(equipement_a_inverse.effet_Malus.getArmureMag_div() !=0)
        {
            equipement_a_inverse.effet_Malus.setArmureMag_div(-equipement_a_inverse.effet_Malus.getArmureMag_div());
        }
        if(equipement_a_inverse.effet_Malus.getEsquive_moins() !=0)
        {
            equipement_a_inverse.effet_Malus.setEsquive_moins(-equipement_a_inverse.effet_Malus.getEsquive_moins());
        }
        if(equipement_a_inverse.effet_Malus.getEsquive_div() !=0)
        {
            equipement_a_inverse.effet_Malus.setEsquive_div(-equipement_a_inverse.effet_Malus.getEsquive_div());
        }
        if(equipement_a_inverse.effet_Malus.getDegat_moins() !=0)
        {
            equipement_a_inverse.effet_Malus.setDegat_moins(-equipement_a_inverse.effet_Malus.getDegat_moins());
        }
        if(equipement_a_inverse.effet_Malus.getDegat_div() !=0)
        {
            equipement_a_inverse.effet_Malus.setDegat_div(-equipement_a_inverse.effet_Malus.getDegat_div());
        }
        if(equipement_a_inverse.effet_Malus.getDegatMag_moins() !=0)
        {
            equipement_a_inverse.effet_Malus.setDegatMag_moins(-equipement_a_inverse.effet_Malus.getDegatMag_moins());
        }
        if(equipement_a_inverse.effet_Malus.getDegatMag_div() !=0)
        {
            equipement_a_inverse.effet_Malus.setDegatMag_div(-equipement_a_inverse.effet_Malus.getDegatMag_div());
        }

        return equipement_a_inverse;
    }


    public void addinListSpell(Spell newSpell)
    {
        this.getListdeSpells().add(newSpell);
    }

    public void changeSpellChoisis(int positionSelected ,int emplacement)
    {
        if(positionSelected ==0)
        {
            this.setSpel0(this.getListdeSpells().get(emplacement));
        }
        else if(positionSelected==1)
        {
            this.setSpel1(this.getListdeSpells().get(emplacement));
        }
        else if(positionSelected==2)
        {
            this.setSpel2(this.getListdeSpells().get(emplacement));
        }
        else
        {
            this.setSpel3(this.getListdeSpells().get(emplacement));
        }
    }

    public void addUtilitaire(Utilitaire newUtilitaire)
    {
        if(getUtilitaire0() == null)
        {
            this.setUtilitaire0(newUtilitaire);
        }
        else if(getUtilitaire1() == null)
        {
            this.setUtilitaire1(newUtilitaire);
        }
        else if(getUtilitaire2() == null)
        {
            this.setUtilitaire2(newUtilitaire);
        }
        else if(getUtilitaire3() == null)
        {
            this.setUtilitaire3(newUtilitaire);
        }
    }

    public void changeUtilitaireChoisis(int positionSelected ,Utilitaire utilitaireSelectionne)
    {
        this.getInventaire().remove(utilitaireSelectionne);

        if(positionSelected ==0)
        {
            this.getInventaire().add(this.getUtilitaire0());
            this.setUtilitaire0(utilitaireSelectionne);
        }
        else if(positionSelected==1)
        {
            this.getInventaire().add(this.getUtilitaire1());
            this.setUtilitaire1(utilitaireSelectionne);
        }
        else if(positionSelected==2)
        {
            this.getInventaire().add(this.getUtilitaire2());
            this.setUtilitaire2(utilitaireSelectionne);
        }
        else
        {
            this.getInventaire().add(this.getUtilitaire3());
            this.setUtilitaire3(utilitaireSelectionne);
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

    public String getClasse0() {
        return Classe0;
    }

    public void setClasse0(String classe0) {
        Classe0 = classe0;
    }

    public int getLvlClasse0() {
        return lvlClasse0;
    }

    public void setLvlClasse0(int lvlClasse0) {
        this.lvlClasse0 = lvlClasse0;
    }

    public String getClasse1() {
        return Classe1;
    }

    public void setClasse1(String classe1) {
        Classe1 = classe1;
    }

    public int getLvlClasse1() {
        return lvlClasse1;
    }

    public void setLvlClasse1(int lvlClasse1) {
        this.lvlClasse1 = lvlClasse1;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Utilitaire getUtilitaire0() {
        return utilitaire0;
    }

    public void setUtilitaire0(Utilitaire utilitaire0) {
        this.utilitaire0 = utilitaire0;
    }

    public Utilitaire getUtilitaire1() {
        return utilitaire1;
    }

    public void setUtilitaire1(Utilitaire utilitaire1) {
        this.utilitaire1 = utilitaire1;
    }

    public Utilitaire getUtilitaire2() {
        return utilitaire2;
    }

    public void setUtilitaire2(Utilitaire utilitaire2) {
        this.utilitaire2 = utilitaire2;
    }

    public Utilitaire getUtilitaire3() {
        return utilitaire3;
    }

    public void setUtilitaire3(Utilitaire utilitaire3) {
        this.utilitaire3 = utilitaire3;
    }

    public ArrayList<Recompense> getInventaire() {
        return Inventaire;
    }

    public void setInventaire(ArrayList<Recompense> inventaire) {
        Inventaire = inventaire;
    }

    public ArrayList<Equipement> getEquipe() {
        return Equipe;
    }

    public void setEquipe(ArrayList<Equipement> equipe) {
        Equipe = equipe;
    }

    public ArrayList<Spell> getListdeSpells() {
        return ListdeSpells;
    }

    public void setListdeSpells(ArrayList<Spell> listdeSpells) {
        ListdeSpells = listdeSpells;
    }

    public boolean isTravel() {
        return Travel;
    }
}
