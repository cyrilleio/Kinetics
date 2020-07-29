package shape;

import geometry.Point;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;

public class CursorType extends Circle {
    private SimpleDoubleProperty theta;
    private SimpleDoubleProperty X;
    private SimpleDoubleProperty Y;
    private double radius;
    public CursorType(Point center, double radius){
        super(center.getX(),center.getY(),radius);
    }
    private void bindProperties(){

    }
}
