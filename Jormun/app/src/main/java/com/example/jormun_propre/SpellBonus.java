package com.example.jormun_propre;

public class SpellBonus
{
    int armure_plus;
    double armure_mult;

    int armureMag_plus;
    double armureMag_mult;

    int esquive_plus;
    double esquive_mult;

    int degat_plus;
    double degat_mult;

    int degatMag_plus;
    double degatMag_mult;


    public SpellBonus (int Varmure_plus, double Varmure_mult, int VarmureMag_plus, double VarmureMag_mult, int Vesquive_plus, double Vesquive_mult, int Vdegat_plus, double Vdegat_mult, int VdegatMag_plus, double VdegatMag_mult)
    {
        this.armure_plus = Varmure_plus;
        this.armure_mult = Varmure_mult;
        this.armureMag_plus = VarmureMag_plus;
        this.armureMag_mult = VarmureMag_mult;
        this.esquive_plus = Vesquive_plus;
        this.esquive_mult = Vesquive_mult;
        this.degat_plus = Vdegat_plus;
        this.degat_mult = Vdegat_mult;
        this.degatMag_plus = VdegatMag_plus;
        this.degatMag_mult = VdegatMag_mult;
    }

}
