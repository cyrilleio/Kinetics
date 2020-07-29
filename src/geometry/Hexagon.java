package geometry;

import javafx.scene.shape.Polygon;
import structures.Constant;

public class Hexagon extends Polygon implements geometry.Polygon {
    private final Point h1,h2,h3,h4,h5,h6;
    private Point s1,s2,s3,s4,s5,s6;
    private final Point center;
    private final double radius;
    public Hexagon(Point center, double radius) {
        super();
        double theta = Math.PI / 3;
        this.radius = radius;
        this.h1 = center.add(radius, 0).rotate(center,3*Math.PI/2);
        this.h2 = h1.rotate(center, theta);
        this.h3 = h2.rotate(center, theta);
        this.h4 = h3.rotate(center, theta);
        this.h5 = h4.rotate(center, theta);
        this.h6 = h5.rotate(center, theta);
        this.center = center;
        starify();
        buildHexagon();
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
    public Hexagon duplicate(double dh) {
        return new Hexagon(this.center,this.radius - dh);
    }
    public Point getCenter(){
        return this.center;
    }
    private void starify(){
        double h= this.radius/(2*Math.tan(Math.PI/6));
        this.s1 = h1.middle(h2).translate(center,h);
        this.s2 = h2.middle(h3).translate(center,h);
        this.s3 = h3.middle(h4).translate(center,h);
        this.s4 = h4.middle(h5).translate(center,h);
        this.s5 = h5.middle(h6).translate(center,h);
        this.s6 = h6.middle(h1).translate(center,h);
    }
    private void buildHexagon(){
        Double[] cords = {
                h1.getX(),h1.getY(), s1.getX(),s1.getY(),
                h2.getX(),h2.getY(), s2.getX(),s2.getY(),
                h3.getX(),h3.getY(), s3.getX(), s3.getY(),
                h4.getX(),h4.getY(), s4.getX(), s4.getY(),
                h5.getX(),h5.getY(), s5.getX(),s5.getY(),
                h6.getX(),h6.getY(), s6.getX(),s6.getY()};
        this.getPoints().addAll(cords);
        this.setStrokeWidth(Constant.SHAPE_STROKE_WIDTH);
        this.setFill(Constant.SHAPE_FILL);
        this.setStroke(Constant.SHAPE_STROKE);
    }

    public Point getH1() {
        return h1;
    }

    public Point getH2() {
        return h2;
    }

    public Point getH3() {
        return h3;
    }

    public Point getH4() {
        return h4;
    }

    public Point getH5() {
        return h5;
    }

    public Point getH6() {
        return h6;
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

    public Point getS5() {
        return s5;
    }

    public Point getS6() {
        return s6;
    }
}



