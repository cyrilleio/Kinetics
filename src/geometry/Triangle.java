package geometry;

import javafx.scene.paint.Color;
import structures.Constant;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends PolygonBase implements Polygon {
    private final Point p1,p2,p3;
    private final Point center;
    private final double l, h;
    private final List<Point> points;

    public Triangle(Point p1, Point p2, Point p3){
        super();
        this.points = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        this.build(points);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.l = p1.distance(p2);
        this.h = this.l/(2*Math.tan(Math.PI/6));
        this.center = p3.add(0,this.h/2);
        this.setStrokeWidth(Constant.SHAPE_STROKE_WIDTH);
        this.setFill(Constant.SHAPE_FILL);
        this.setStroke(Constant.SHAPE_STROKE);
    }
    public Triangle(Point center, double l ){
        super();
        this.points = new ArrayList<>();
        double h = l/(2*Math.tan(Math.PI/6));
        this.h = h;
        this.p1 = center.add(-l/2, h/2);
        this.p2 = p1.add(l,0);
        this.p3 = center.add(0,-h/2 );
        this.points.add(p1);
        this.points.add(p2);
        this.points.add(p3);
        this.build(this.points);
        this.setStrokeWidth(Constant.SHAPE_STROKE_WIDTH);
        this.setFill(Constant.SHAPE_FILL);
        this.l = l ;
        this.center = center;
        this.setStroke(Constant.SHAPE_STROKE);
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
    public Triangle duplicate(double dh) {
        Point p1, p2,p3;
        double dx, dy;
        dx = dh*Math.cos(Math.PI/6);//(dh*this.l)/Math.sqrt(Math.pow(l,2) + Math.pow(this.h,2));
        dy = dh*Math.sin(Math.PI/6) ;//dh*this.h)/Math.sqrt(Math.pow(l,2) + Math.pow(this.h,2));
        p1 = this.p1.add(dx,-dy);
        p2 = this.p2.add(-dx,-dy);
        p3 = this.p3.add(0,dh);
        return new Triangle(p1,p2,p3);
    }
    public Point getCenter(){
        return this.center;
    }
    public Point getP1(){
        return this.p1;
    }
    public Point getP2(){
        return this.p2;
    }
    public Point getP3(){
        return this.p3;
    }
}
