package com.example.jormun_map.model.data_classes;

public class MalusEffect
{
    private int armure_moins;
    private double armure_div;

    private int armureMag_moins;
    private double armureMag_div;

    private int esquive_moins;
    private double esquive_div;

    private int degat_moins;
    private double degat_div;

    private int degatMag_moins;
    private double degatMag_div;

    public MalusEffect(int Varmure_moins, double Varmure_div, int VarmureMag_moins, double VarmureMag_div, int Vesquive_moins, double Vesquive_div, int Vdegat_moins, double Vdegat_div, int VdegatMag_moins, double VdegatMag_div)
    {
        this.setArmure_moins(Varmure_moins);
        this.setArmure_div(Varmure_div);
        this.setArmureMag_moins(VarmureMag_moins);
        this.setArmureMag_div(VarmureMag_div);
        this.setEsquive_moins(Vesquive_moins);
        this.setEsquive_div(Vesquive_div);
        this.setDegat_moins(Vdegat_moins);
        this.setDegat_div(Vdegat_div);
        this.setDegatMag_moins(VdegatMag_moins);
        this.setDegatMag_div(VdegatMag_div);
    }
    public MalusEffect()
    {
        this.setArmure_moins(0);
        this.setArmure_div(0);
        this.setArmureMag_moins(0);
        this.setArmureMag_div(0);
        this.setEsquive_moins(0);
        this.setEsquive_div(0);
        this.setDegat_moins(0);
        this.setDegat_div(0);
        this.setDegatMag_moins(0);
        this.setDegatMag_div(0);
    }

    public int getArmure_moins() {
        return armure_moins;
    }

    public void setArmure_moins(int armure_moins) {
        this.armure_moins = armure_moins;
    }

    public double getArmure_div() {
        return armure_div;
    }

    public void setArmure_div(double armure_div) {
        this.armure_div = armure_div;
    }

    public int getArmureMag_moins() {
        return armureMag_moins;
    }

    public void setArmureMag_moins(int armureMag_moins) {
        this.armureMag_moins = armureMag_moins;
    }

    public double getArmureMag_div() {
        return armureMag_div;
    }

    public void setArmureMag_div(double armureMag_div) {
        this.armureMag_div = armureMag_div;
    }

    public int getEsquive_moins() {
        return esquive_moins;
    }

    public void setEsquive_moins(int esquive_moins) {
        this.esquive_moins = esquive_moins;
    }

    public double getEsquive_div() {
        return esquive_div;
    }

    public void setEsquive_div(double esquive_div) {
        this.esquive_div = esquive_div;
    }

    public int getDegat_moins() {
        return degat_moins;
    }

    public void setDegat_moins(int degat_moins) {
        this.degat_moins = degat_moins;
    }

    public double getDegat_div() {
        return degat_div;
    }

    public void setDegat_div(double degat_div) {
        this.degat_div = degat_div;
    }

    public int getDegatMag_moins() {
        return degatMag_moins;
    }

    public void setDegatMag_moins(int degatMag_moins) {
        this.degatMag_moins = degatMag_moins;
    }

    public double getDegatMag_div() {
        return degatMag_div;
    }

    public void setDegatMag_div(double degatMag_div) {
        this.degatMag_div = degatMag_div;
    }
}
