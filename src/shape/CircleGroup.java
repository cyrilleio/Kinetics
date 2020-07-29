package shape;

import geometry.Circle;
import geometry.Point;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeType;
import structures.Complex;
import structures.Constant;
import java.util.ArrayList;

public class CircleGroup extends ShapeGroup {
    private final Circle c0,c1,c2;
    private final static double startAngle = 0.d;
    private Arc arc;
    public CircleGroup(Point center, double radius, double space){
        super();
        this.property = new SimpleDoubleProperty();
        this.arc = new Arc();
        this.space = space;
        this.center = center;
        this.c0 = new Circle(center.getX(),center.getY(),radius);
        this.c1 = c0.duplicate(space);
        this.c2 = c1.duplicate(space);
        this.outerObservable = c0.duplicate(-Constant.SHAPE_STROKE_WIDTH);
        this.observable = this.c1.duplicate(0.d);
        this.observable.setFill(Constant.OBSERVABLE_SHAPE_FILL);
        this.observable.setStrokeWidth(2*space);
        this.observable.setStroke(Constant.OBSERVABLE_SHAPE_STROKE);
        this.outerObservable.setFill(null);
        this.outerObservable.setStroke(Constant.OUTER_OBSERVABLE_FILL);
        this.outerObservable.setStrokeWidth(2*space);
        this.outerObservable.setStrokeType(StrokeType.OUTSIDE);
        this.children = new ArrayList<>();
        Point pos = center.add(this.c1.getRadius(),0);
        bindCursor(pos);
        bindArc(this.c1);
        build();
        bindings();
    }
    @Override
    public void build(){
        this.children.add(this.c0);
        this.children.add(this.c1);
        this.children.add(this.c2);
        this.children.add(this.arc);
        this.children.add(this.cursor);
        this.children.add(this.observable);
        this.children.add(this.outerObservable);
        this.getChildren().setAll(this.children);
    }

    private void bindArc(Circle circle){
        Arc arc = new Arc();
        arc.setCenterX(circle.getCenterX());
        arc.setCenterY(circle.getCenterY());
        arc.setRadiusX(circle.getRadius());
        arc.setRadiusY(circle.getRadius());
        arc.setStartAngle(startAngle);
        arc.setType(ArcType.OPEN);
        arc.setFill(Constant.ARC_FILL);
        arc.setStroke(Constant.ARC_STROKE);
        arc.setStrokeWidth(Constant.ARC_STROKE_WIDTH);
        this.arc = arc;
    }

    private void bindings(){
        this.arc.lengthProperty().bind(this.property);
        this.property.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
               cursor.setCenterX(c1.getCenterX() + c1.getRadius()*Math.cos(Math.PI*(double)t1/180));
               cursor.setCenterY(c1.getCenterY() - c1.getRadius()*Math.sin(Math.PI*(double)t1/180));
            }
        });
    }

    @Override
    public double getCurrentX(){
        return this.c1.getRadius()*Math.cos(Math.PI*this.property.get()/180);
    }
    @Override
    public double getCurrentY(){
        return this.c1.getRadius()*Math.sin(Math.PI* Math.abs(this.property.get())/180);
    }
    @Override
    public Complex getCurrentPosition(){
        return new Complex(getCurrentX(),getCurrentY());
    }
    @Override public final void unbindArc(){
        this.arc.lengthProperty().unbind();
        this.arc.setLength(0.d);
        this.arc.lengthProperty().bind(this.property);
    }
    @Override
    public final void toggleArcVisibility(){
        this.arc.setVisible(!this.arc.isVisible());
    }

}