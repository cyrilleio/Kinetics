package geometry;

import javafx.geometry.Point2D;
import structures.Complex;


public class Point extends Point2D {
    private final Complex affix;
    public Point(double axisX, double axisY){
        super(axisX,axisY);
        this.affix = new Complex(axisX,axisY);
    }
    public Point(Complex affix){
        super(affix.getReal(),affix.getImag());
        this.affix = affix;
    }

    public Point add(Point point){

        return new Point(this.getX() + point.getX(),this.getY()+point.getY());
    }
    public Point add(double axisX, double axisY){
        return new Point(this.getX() + axisX, this.getY() + axisY);
    }
    public Complex getAffix(){
        return this.affix;
    }
    public Point rotate(Point center,double theta){
        double radius = this.distance(center);
        double h = center.getY() - this.getY();
        Complex z,ez;
        ez= new Complex(theta);
        z = center.affix.plus(ez.times(this.affix.minus(center.affix)));
        return new Point(z);
    }
    public Point middle(Point b){
        return new Point(this.affix.plus(b.affix).scale(0.5));
    }
    public Point translate(Point ref, double h){
        Complex z = this.affix.minus(ref.affix).normalise().scale(h).plus(this.affix);
        return new Point(z);
    }

    @Override
    public String toString(){
        return String.format("%05.2f;%05.2f",this.getX(), this.getY());
    }
    @Override
    public boolean equals(Object p){
        if (p == this) {
            return true;
        }
        if (!(p instanceof Point)) {
            return false;
        }
        Point c = (Point) p;
        return c.getAffix().equals(this.affix);
    }
}
