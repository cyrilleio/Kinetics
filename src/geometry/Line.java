package geometry;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import structures.Complex;
import structures.Constant;


public class Line extends javafx.scene.shape.Line implements Polygon {
    private final Point p1,p2;
    private final Vector vect;
    private final double norm;
    private Point p;
    private final SimpleBooleanProperty visibility;
    public Line(Point p1, Point p2){
        super(p1.getX(),p1.getY(),p1.getX(),p1.getY());
        this.p1 = p1;
        this.p2 = p2;
        this.p = p1;
        this.vect = new Vector(p1,p2);
        this.norm = vect.getNorm();
        this.visibility = new SimpleBooleanProperty();
        this.setStroke(Constant.ARC_STROKE);
        this.setStrokeWidth(Constant.ARC_STROKE_WIDTH);
        this.visibleProperty().bind(visibility);
        this.setSmooth(true);
    }
    @Override
    public int dim() {
        return 0;
    }
    @Override
    public float[] points() {
        return new float[0];
    }
    @Override
    public void translate(int dh) {

    }
    @Override
    public Object duplicate(double dh) {
        return null;
    }
    public double getLength(){
        return this.p1.distance(this.p2);
    }
    public void scale(double alpha){
        if (Double.compare(alpha,0.d)==0){
            this.visibility.setValue(false);
            this.p = this.p1;
        }

        else {
            this.visibility.setValue(true);
            Complex z;
            z = this.p1.getAffix().plus(this.vect.getAffix().scale(alpha));
            Point p = new Point(z);
            this.setEndX(p.getX());
            this.setEndY(p.getY());
            this.p = p;
        }
    }
    public Point position(){
        return this.p;
    }
}

