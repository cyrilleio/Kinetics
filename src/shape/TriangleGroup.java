package shape;
import geometry.Line;
import geometry.Point;
import geometry.Triangle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import structures.Complex;
import structures.Constant;
import java.util.ArrayList;

public class TriangleGroup extends ShapeGroup{
    private final Triangle t0, t1, t2;
    public TriangleGroup(Point center, double l, double space){
        this.space = space;
        this.center = center;
        this.t0 = new Triangle(center,l);
        this.t1 = t0.duplicate(space);
        this.t2 = t1.duplicate(space);
        this.outerObservable = this.t0.duplicate(-Constant.SHAPE_STROKE_WIDTH);
        this.observable = this.t1.duplicate(0);
        this.observable.setStrokeWidth(space);
        this.observable.setStroke(Constant.OBSERVABLE_SHAPE_STROKE);
        this.outerObservable.setFill(null);
        this.outerObservable.setStroke(Constant.OUTER_OBSERVABLE_FILL);
        this.outerObservable.setStrokeWidth(2*space);
        this.outerObservable.setStrokeType(StrokeType.OUTSIDE);
        this.children = new ArrayList<>();
        this.property = new SimpleDoubleProperty();
        this.lines = new ArrayList<>();
        bindCursor(this.t1.getP1());
        build();
    }

    public void build() {
        bindArc();
        this.children.add(this.t0);
        this.children.add(this.t1);
        this.children.add(this.t2);
        this.lines.add(new Line(this.t1.getP1(),this.t1.getP3()));
        this.lines.add( new Line(this.t1.getP3(),this.t1.getP2()));
        this.lines.add(new Line(this.t1.getP2(),this.t1.getP1()));
        this.children.addAll(this.lines);
        this.children.add(this.cursor);
        this.children.add(this.observable);
        this.children.add(this.outerObservable);
        this.getChildren().setAll(this.children);
    }

    @Override
    public double getCurrentX() {
        return 0;
    }

    @Override
    public double getCurrentY() {
        return 0;
    }

    public void bindArc(){
        property.addListener((observableValue, number, t1) -> {
            double t = (double) t1;
            int i =(int)Math.floor(t);
            lines.get(i).scale(t - i);
            bindings();
        });
    }

    public Complex getCurrentPosition(){
        return this.lines.get((int) Math.floor(this.property.get())).position().getAffix();
    }
    public void bindings(){
        this.cursor.setCenter(getCurrentPosition());
    }
}
