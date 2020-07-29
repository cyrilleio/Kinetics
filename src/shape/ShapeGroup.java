package shape;

import geometry.Circle;
import geometry.Line;
import geometry.Point;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import structures.Complex;
import structures.Constant;

import java.util.List;

public abstract class ShapeGroup extends Group implements ObservableShape {
    protected Circle cursor;
    protected DoubleProperty property;
    protected Point center;
    protected double space;
    protected Shape observable;
    protected Shape outerObservable;
    protected List<Line> lines;
    protected List<Node> children;
    public abstract void build();
    public abstract double getCurrentX();
    public abstract double getCurrentY();
    protected double rotatedTheta =0;
    public double cosTheta = 1;
    public double sinTheta = 0;
    public final void bindCursor(Point pos){
        Circle cursor = new Circle(pos, Constant.CURSOR_RADIUS);
        cursor.setStrokeWidth(Constant.CURSOR_STROKE_WIDTH);
        cursor.setStroke(Constant.CURSOR_STROKE);
        cursor.setFill(Constant.CURSOR_FILL);
        this.cursor = cursor;
    }
    public final void setProperty(DoubleProperty property){
        this.property = property;
    }
    public final DoubleProperty getProperty(){
        return this.property;
    }
    public final Shape getObservableShape() {
        return this.observable;
    }
    public final Shape getOuterObservableShape(){return this.outerObservable;}
    public final Point getCenter(){return this.center;}
    public final double getSpace(){
        return this.space;
    }
    public Complex getCurrentPosition(){
        double xTheta, yTheta;
        xTheta = cosTheta*getCurrentX() - sinTheta*getCurrentY();
        yTheta = sinTheta*getCurrentX() + cosTheta*getCurrentY();
        return new Complex(xTheta,yTheta).conjugate();
    }
    public  void unbindArc(){
        for(Line line:lines){
            line.scale(0.d);
        }
    }
    public void toggleArcVisibility() {

    }
    public final void rotate(double theta){
        this.getTransforms().add(new Rotate(180*theta/Math.PI,this.center.getX(),this.center.getY()));
        this.rotatedTheta = theta;
        this.cosTheta = Math.cos(theta);
        this.sinTheta = Math.sin(theta);
    }
    public final void translate(double dx,double dy){
        this.getTransforms().add(new Translate(dx,dy));
        this. center = this.center.add(dx,dy);
    }

}

