package com.example.jormun_map.model.data_classes;

import com.example.jormun_map.Config.CornerId;

public class Bound {
    Point pntCornerLeftFar;
    Point pntCornerRightFar;
    Point pntCornerLeftNear;
    Point pntCornerRightNear;

    public Bound(){
        setPntCornerLeftFar(new Point());
        setPntCornerRightFar(new Point());
        setPntCornerLeftNear(new Point());
        setPntCornerRightNear(new Point());
    }

    public void setCornerPoint(CornerId crnidCurrent,double dValueLat,double dValueLong){
        switch (crnidCurrent){
            case FL:
                setPntCornerLeftFar(new Point(dValueLat,dValueLong));
                break;
            case FR:
                setPntCornerRightFar(new Point(dValueLat,dValueLong));
                break;
            case NL:
                setPntCornerLeftNear(new Point(dValueLat,dValueLong));
                break;
            case NR:
                setPntCornerRightNear(new Point(dValueLat,dValueLong));
                break;
            default:
                break;
        }
    }

    public Point getPntCornerLeftFar() {
        return pntCornerLeftFar;
    }

    public Point getPntCornerRightFar() {
        return pntCornerRightFar;
    }

    public Point getPntCornerLeftNear() {
        return pntCornerLeftNear;
    }

    public Point getPntCornerRightNear() {
        return pntCornerRightNear;
    }

    public void setPntCornerLeftFar(Point pntCornerLeftFar) {
        this.pntCornerLeftFar = pntCornerLeftFar;
    }

    public void setPntCornerRightFar(Point pntCornerRightFar) {
        this.pntCornerRightFar = pntCornerRightFar;
    }

    public void setPntCornerLeftNear(Point pntCornerLeftNear) {
        this.pntCornerLeftNear = pntCornerLeftNear;
    }

    public void setPntCornerRightNear(Point pntCornerRightNear) {
        this.pntCornerRightNear = pntCornerRightNear;
    }
}
