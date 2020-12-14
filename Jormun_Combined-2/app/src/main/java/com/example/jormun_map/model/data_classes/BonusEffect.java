package com.example.jormun_map.model.data_classes;

public class BonusEffect
{
    private int armure_plus;
    private double armure_mult;

    private int armureMag_plus;
    private double armureMag_mult;

    private int esquive_plus;
    private double esquive_mult;

    private int degat_plus;
    private double degat_mult;

    private int degatMag_plus;
    private double degatMag_mult;


    public BonusEffect(int Varmure_plus, double Varmure_mult, int VarmureMag_plus, double VarmureMag_mult, int Vesquive_plus, double Vesquive_mult, int Vdegat_plus, double Vdegat_mult, int VdegatMag_plus, double VdegatMag_mult)
    {
        this.setArmure_plus(Varmure_plus);
        this.setArmure_mult(Varmure_mult);
        this.setArmureMag_plus(VarmureMag_plus);
        this.setArmureMag_mult(VarmureMag_mult);
        this.setEsquive_plus(Vesquive_plus);
        this.setEsquive_mult(Vesquive_mult);
        this.setDegat_plus(Vdegat_plus);
        this.setDegat_mult(Vdegat_mult);
        this.setDegatMag_plus(VdegatMag_plus);
        this.setDegatMag_mult(VdegatMag_mult);
    }

    public int getArmure_plus() {
        return armure_plus;
    }

    public void setArmure_plus(int armure_plus) {
        this.armure_plus = armure_plus;
    }

    public double getArmure_mult() {
        return armure_mult;
    }

    public void setArmure_mult(double armure_mult) {
        this.armure_mult = armure_mult;
    }

    public int getArmureMag_plus() {
        return armureMag_plus;
    }

    public void setArmureMag_plus(int armureMag_plus) {
        this.armureMag_plus = armureMag_plus;
    }

    public double getArmureMag_mult() {
        return armureMag_mult;
    }

    public void setArmureMag_mult(double armureMag_mult) {
        this.armureMag_mult = armureMag_mult;
    }

    public int getEsquive_plus() {
        return esquive_plus;
    }

    public void setEsquive_plus(int esquive_plus) {
        this.esquive_plus = esquive_plus;
    }

    public double getEsquive_mult() {
        return esquive_mult;
    }

    public void setEsquive_mult(double esquive_mult) {
        this.esquive_mult = esquive_mult;
    }

    public int getDegat_plus() {
        return degat_plus;
    }

    public void setDegat_plus(int degat_plus) {
        this.degat_plus = degat_plus;
    }

    public double getDegat_mult() {
        return degat_mult;
    }

    public void setDegat_mult(double degat_mult) {
        this.degat_mult = degat_mult;
    }

    public int getDegatMag_plus() {
        return degatMag_plus;
    }

    public void setDegatMag_plus(int degatMag_plus) {
        this.degatMag_plus = degatMag_plus;
    }

    public double getDegatMag_mult() {
        return degatMag_mult;
    }

    public void setDegatMag_mult(double degatMag_mult) {
        this.degatMag_mult = degatMag_mult;
    }
}
