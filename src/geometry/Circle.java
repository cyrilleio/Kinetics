package geometry;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.text.DecimalFormat;

public class Circle extends CircleBase{
    private double perimeter;
    private final static int dim = 1 ;

    public Circle(double centerX, double centerY, double radius){
        super(centerX,centerY,radius);
    }
    public Circle(Point center, double radius){
        super(center.getX(),center.getY(),radius);
    }

    @Override
    public int dim() {
        return 0;
    }

    @Override
    public float[] points() {
        return new float[0];
    }

    public double getPerimeter (){
        return perimeter;
    }

    @Override
    public Circle duplicate (double dh){
        Circle circle = new Circle(this.getCenterX(), this.getCenterY(), this.getRadius() -dh);
        circle.setCenterX(this.getCenterX());
        circle.setCenterY(this.getCenterY());
        return circle;
    }
    public double getRadius(){
        return this.radiusX;
    }
}

