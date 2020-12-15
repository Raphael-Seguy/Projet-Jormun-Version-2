package com.example.jormun_map.model.data_classes;

public class Point {
    public static final int iDEFAULT = -3995;

    double dLat;
    double dLong;

    public Point(){
        setdLat(iDEFAULT);
        setdLong(iDEFAULT);
    }

    public Point(double dLat, double dLong){
        setdLat(dLat);
        setdLong(dLong);
    }

    public double getdLat() {
        return dLat;
    }

    public double getdLong() {
        return dLong;
    }

    public void setdLat(double dLat) {
        this.dLat = dLat;
    }

    public void setdLong(double dLong) {
        this.dLong = dLong;
    }
}
