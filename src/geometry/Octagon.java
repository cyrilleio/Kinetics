package geometry;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import structures.Constant;

import java.util.ArrayList;
import java.util.List;

public class Octagon extends Polygon implements geometry.Polygon {
    private final Point o1,o2,o3,o4;
    private final Point s1,s2,s3,s4;
    private final Point c1, c11,c2, c22,c3,c33,c4,c44;
    private final Point center;
    private final double width;
    private double h =20;
    private double b = 30;
    public Octagon(Point center, double width,double b) {
        this.width = width;
        Rectangle rect = new Rectangle(center, width, width);
        List<Point> points = new ArrayList<>(rect.getCorners());
        this.b = b;
        this.h = (Math.sqrt(2)-1)*width/2;//0.25*this.b;
        this.o1 = points.get(0);
        this.o2 = points.get(1);
        this.o3 = points.get(2);
        this.o4 = points.get(3);
        double dh = (width - this.b) / 2;
        this.c1 = this.o1.add(dh, 0);
        this.c11 = this.o2.add(-dh, 0);
        this.c2 = this.o2.add(0, dh);
        this.c22 = this.o3.add(0, -dh);
        this.c3 = this.o3.add(-dh, 0);
        this.c33 = this.o4.add(dh, 0);
        this.c4 = this.o4.add(0, -dh);
        this.c44 = this.o1.add(0, dh);
        this.s1 = this.c1.middle(this.c11).translate(center, this.h);
        this.s2 = this.o2.middle(this.o3).translate(center, this.h);
        this.s3 = this.o3.middle(this.o4).translate(center, this.h);
        this.s4 = this.o4.middle(this.o1).translate(center, this.h);
        this.center = center;
        this.setFill(Constant.SHAPE_FILL);
        this.setStrokeWidth(Constant.SHAPE_STROKE_WIDTH);
        this.setStroke(Constant.SHAPE_STROKE);
        Double[] cords = {
                o1.getX(), o1.getY(),
                c1.getX(), c1.getY(),
                s1.getX(), s1.getY(),
                c11.getX(), c11.getY(),
                o2.getX(), o2.getY(),
                c2.getX(), c2.getY(),
                s2.getX(), s2.getY(),
                c22.getX(), c22.getY(),
                o3.getX(), o3.getY(),
                c3.getX(), c3.getY(),
                s3.getX(), s3.getY(),
                c33.getX(), c33.getY(),
                o4.getX(), o4.getY(),
                c4.getX(), c4.getY(),
                s4.getX(), s4.getY(),
                c44.getX(), c44.getY()};
        this.getPoints().addAll(cords);
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
    public Octagon duplicate(double dh) {

        return new Octagon(this.center,width-dh,(this.width-dh)*this.b/this.width);
    }

    public Point getO1() {
        return o1;
    }

    public Point getO2() {
        return o2;
    }

    public Point getO3() {
        return o3;
    }

    public Point getO4() {
        return o4;
    }

    public Point getS1() {
        return s1;
    }

    public Point getS2() {
        return s2;
    }

    public Point getS3() {
        return s3;
    }

    public Point getS4() {
        return s4;
    }

    public Point getC1() {
        return c1;
    }

    public Point getC11() {
        return c11;
    }

    public Point getC2() {
        return c2;
    }

    public Point getC22() {
        return c22;
    }

    public Point getC3() {
        return c3;
    }

    public Point getC33() {
        return c33;
    }

    public Point getC4() {
        return c4;
    }

    public Point getC44() {
        return c44;
    }
}
