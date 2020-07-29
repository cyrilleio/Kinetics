package shape;

import geometry.Ellipse;
import geometry.Point;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeType;
import structures.Constant;
import structures.TemporalPoint;

import java.util.ArrayList;
import java.util.List;

public class EllipseGroup extends ShapeGroup{
    private final Ellipse e0, e1, e2, e3;
    private Arc arc;
    private final static double startAngle = 0.0d;

    public EllipseGroup(Point center, double radiusX, double radiusY, double space) {
        this.space = space;
        this.center = center;
        this.property = new SimpleDoubleProperty();
        this.e0 = new Ellipse(center.getX(), center.getY(), radiusX, radiusY);
        this.e1 = e0.duplicate(space);
        this.e2 = e1.duplicate(space);
        this.e3 = this.e0.duplicate(-Constant.SHAPE_STROKE_WIDTH);
        this.observable = this.e1.duplicate(0);
        this.observable.setStrokeWidth(2*space -4);
        this.observable.setStroke(Constant.OBSERVABLE_SHAPE_STROKE);
        this.e3.setFill(null);
        this.e3.setStroke(Constant.OUTER_OBSERVABLE_FILL);
        this.e3.setStrokeWidth(2*space);
        this.e3.setStrokeType(StrokeType.OUTSIDE);
        this.children = new ArrayList<>();
        Point pos = center.add(this.e1.getRadiusX(),0);
        bindCursor(pos);
        bindArc(this.e1);
        build();
        bindings();
    }
@Override
    public void build() {
        this.children.add(this.e0);
        this.children.add(this.e1);
        this.children.add(this.e2);
        this.children.add(this.arc);
        this.children.add(this.cursor);
        this.children.add(this.observable);
        this.children.add(this.e3);
        this.getChildren().setAll(this.children);
    }



    private void bindArc(Ellipse ellipse) {
        Arc arc = new Arc();
        arc.setCenterX(ellipse.getCenterX());
        arc.setCenterY(ellipse.getCenterY());
        arc.setRadiusX(ellipse.getRadiusX());
        arc.setRadiusY(ellipse.getRadiusY());
        arc.setStartAngle(startAngle);
        arc.setType(ArcType.OPEN);
        arc.setFill(Constant.ARC_FILL);
        arc.setStroke(Constant.ARC_STROKE);
        arc.setStrokeWidth(Constant.ARC_STROKE_WIDTH);
        this.arc = arc;
    }

    private void bindings() {
        this.arc.lengthProperty().bind(this.property);
        this.property.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                cursor.setCenterX(e1.getCenterX() + e1.getRadiusX()*Math.cos(Math.PI*(double)t1/180));
                cursor.setCenterY(e1.getCenterY() - e1.getRadiusY()*Math.sin(Math.PI*(double)t1/180));
            }
        });
    }


@Override
    public double getCurrentX(){
        return this.e1.getRadiusX()*Math.cos(Math.PI*this.property.get()/180);
    }
    @Override
    public double getCurrentY(){
        return this.e1.getRadiusY()*Math.sin(Math.PI* Math.abs(this.property.get())/180);

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
