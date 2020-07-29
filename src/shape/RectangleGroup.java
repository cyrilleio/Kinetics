package shape;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import structures.Complex;
import structures.Constant;
import java.util.ArrayList;



public class RectangleGroup extends ShapeGroup {
    private final Rectangle r0, r1, r2;
    public RectangleGroup(Point center, double width,  double space){
        this.space = space;
        this.center = center;
        this.property = new SimpleDoubleProperty();
        this.children = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.r0 = new Rectangle(center,width, width);
        this.r1 = r0.duplicate(space);
        this.r2 = r1.duplicate(space);
        this.outerObservable = this.r0.duplicate(-Constant.SHAPE_STROKE_WIDTH);
        this.observable = this.r1.duplicate(0);
        this.observable.setStroke(Constant.OBSERVABLE_SHAPE_STROKE);
        this.observable.setFill(Constant.OBSERVABLE_SHAPE_FILL);
        this.observable.setStrokeWidth(space);
        this.outerObservable.setFill(null);
        this.outerObservable.setStroke(Constant.OUTER_OBSERVABLE_FILL);
        this.outerObservable.setStrokeWidth(2*space);
        this.outerObservable.setStrokeType(StrokeType.OUTSIDE);
        bindCursor(this.r1.getP1());
        build();
    }

    public void build() {
        bindArc();
        this.children.add(this.r0);
        this.children.add(this.r1);
        this.children.add(this.r2);
        this.lines.add(new Line(this.r1.getP1(),this.r1.getP2()));
        this.lines.add(new Line(this.r1.getP2(),this.r1.getP3()));
        this.lines.add(new Line(this.r1.getP3(),this.r1.getP4()));
        this.lines.add(new Line(this.r1.getP4(),this.r1.getP1()));
        this.children.addAll(this.lines);
        this.children.add(this.cursor);
        this.children.add(this.observable);
        this.children.add(this.outerObservable);
        this.getChildren().setAll(this.children);
    }
    public void bindArc(){
        property.addListener((observableValue, number, t1) -> {
            double t = (double) t1;
            int i =(int)Math.floor(t);
            lines.get(i).scale(t - i);
            bindings();
    });
    }

    @Override
    public double getCurrentX() {
        return 0;
    }

    @Override
    public double getCurrentY() {
        return 0;
    }


    public Complex getCurrentPosition(){
        return this.lines.get((int) Math.floor(this.property.get())).position().getAffix();
    }
    public void bindings(){
        this.cursor.setCenter(getCurrentPosition());
    }

}