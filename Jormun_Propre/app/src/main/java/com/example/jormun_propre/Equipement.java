package com.example.jormun_propre;

public class Equipement extends Recompense
{
    public BonusEffect effet_Bonus;
    public MalusEffect effet_Malus;

    public Equipement(int Vcategorie,int Vquantite, String Vnom, BonusEffect BonusDeObject, MalusEffect MalusDeObject, String Vdescription)
    {
        super(Vnom,Vdescription, Vquantite, Vcategorie);
        this.effet_Bonus = BonusDeObject;
        this.effet_Malus = MalusDeObject;
    }
}
