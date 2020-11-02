package com.example.jormun_propre;

public class SpellMalus
{
    int armure_moins;
    double armure_div;

    int armureMag_moins;
    double armureMag_div;

    int esquive_moins;
    double esquive_div;

    int degat_moins;
    double degat_div;

    int degatMag_moins;
    double degatMag_div;

    public SpellMalus (int Varmure_moins, double Varmure_div, int VarmureMag_moins, double VarmureMag_div, int Vesquive_moins, double Vesquive_div, int Vdegat_moins, double Vdegat_div, int VdegatMag_moins, double VdegatMag_div)
    {
        this.armure_moins = Varmure_moins;
        this.armure_div = Varmure_div;
        this.armureMag_moins = VarmureMag_moins;
        this.armureMag_div = VarmureMag_div;
        this.esquive_moins = Vesquive_moins;
        this.esquive_div = Vesquive_div;
        this.degat_moins = Vdegat_moins;
        this.degat_div = Vdegat_div;
        this.degatMag_moins = VdegatMag_moins;
        this.degatMag_div = VdegatMag_div;
    }
}
