package geometry;

import structures.Complex;

public class Vector {
    private final Point p1;
    private final Point p2;
    private Complex affix;
    private double norm;
    public Vector(Point p1,Point p2){
        this.p1 = p1;
        this.p2 = p2;
        this.affix = p2.getAffix().minus(p1.getAffix());
        this.norm = this.affix.abs();
    }
    public double getNorm(){
        return this.affix.abs();
    }
    public Point getOrigin(){
        return this.p1;
    }
    public Vector opposite(){
        return new Vector(this.p2,this.p1);
    }
    public void scale(double alpha){
        this.affix = this.affix.scale(alpha);
    }

    public Complex getAffix() {
        return affix;
    }
    public void normalise(){
        this.affix = this.affix.scale(1./this.affix.abs());
    }
}
