package geometry;
import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import structures.Constant;
import java.util.ArrayList;
import java.util.List;

public class Rectangle extends javafx.scene.shape.Polygon implements Polygon {
    private final Point p1,p2,p3,p4;
    private final double height,width;
    private final Point center;
    public Rectangle(Point p1, Point p2, Point p3, Point p4){
        super();
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.height = p3.getY() - p2.getY();
        this.width = p2.getX() - p1.getX();
        this.center = p1.add(width / 2, height/2);
        buildRectangle();
    }
    public Rectangle(Point center, double width,double height){
        super();
        this.center = center;
        this.p1 = center.add(-width/2, -height/2);
        this.p2 = p1.add(width, 0);
        this.p3 = p2.add(0,height);
        this.p4 = p3.add(-width,0);
        this.width = width;
        this.height = height;
        buildRectangle();
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
    public Rectangle duplicate(double dh) {
        return new Rectangle(this.center, this.width -dh ,this.height -dh);
    }
    private void buildRectangle(){
        Double[] points = {p1.getX(),p1.getY(),p2.getX(),p2.getY(),p3.getX(),p3.getY(),p4.getX(),p4.getY()};
        this.getPoints().addAll(points);
        this.setStrokeWidth(Constant.SHAPE_STROKE_WIDTH);
        this.setFill(Constant.SHAPE_FILL);
        this.setStroke(Constant.SHAPE_STROKE);
    }

    public List<Point> getCorners(){
        List<Point> points = new ArrayList<>();
        points.add(this.p1);
        points.add(this.p2);
        points.add(this.p3);
        points.add(this.p4);
        return points;
    }
    public Point getCenter(){
        return this.center;
    }

    public double getWidth() {
        return width;
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
    public Point getP4(){
        return this.p4;
    }
}
