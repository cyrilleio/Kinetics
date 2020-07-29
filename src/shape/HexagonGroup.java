package shape;

import geometry.Hexagon;
import geometry.Line;
import geometry.Point;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.StrokeType;
import structures.Complex;
import structures.Constant;
import java.util.ArrayList;

public class HexagonGroup extends ShapeGroup{
    private final Hexagon h0, h1, h2, h3;
    public HexagonGroup(Point center, double radius, double space){
        this.space = space;
        this.center = center;
        this.property = new SimpleDoubleProperty();
        this.lines = new ArrayList<>();
        this.h0 = new Hexagon(center,radius);
        this.h1 = h0.duplicate(space);
        this.h2 = h1.duplicate(space);
        this.h3 = this.h0.duplicate(Constant.SHAPE_STROKE_WIDTH);
        this.observable = this.h1.duplicate(0);
        this.observable.setStrokeWidth(space);
        this.observable.setStroke(Constant.OBSERVABLE_SHAPE_STROKE);
        this.observable.setFill(Constant.OBSERVABLE_SHAPE_FILL);
        this.h3.setFill(null);
        this.h3.setStroke(Constant.OUTER_OBSERVABLE_FILL);
        this.h3.setStrokeWidth(2*space);
        this.h3.setStrokeType(StrokeType.OUTSIDE);
        this.children = new ArrayList<>();
        bindCursor(this.h1.getH1());
        build();
    }
    public void build() {
        bindArc();
        this.children.add(this.h0);
        this.children.add(this.h1);
        this.children.add(this.h2);
        this.lines.add(new Line(this.h1.getH1(),this.h1.getS1()));
        this.lines.add(new Line(this.h1.getS1(),this.h1.getH2()));
        this.lines.add(new Line(this.h1.getH2(),this.h1.getS2()));
        this.lines.add(new Line(this.h1.getS2(),this.h1.getH3()));
        this.lines.add(new Line(this.h1.getH3(),this.h1.getS3()));
        this.lines.add(new Line(this.h1.getS3(),this.h1.getH4()));
        this.lines.add(new Line(this.h1.getH4(),this.h1.getS4()));
        this.lines.add(new Line(this.h1.getS4(),this.h1.getH5()));
        this.lines.add(new Line(this.h1.getH5(),this.h1.getS5()));
        this.lines.add(new Line(this.h1.getS5(),this.h1.getH6()));
        this.lines.add(new Line(this.h1.getH6(),this.h1.getS6()));
        this.lines.add(new Line(this.h1.getS6(),this.h1.getH1()));
        this.children.addAll(this.lines);
        this.children.add(this.cursor);
        this.children.add(this.observable);
        this.children.add(this.h3);
        this.getChildren().setAll(this.children);
    }
    public void bindArc(){
        this.property.addListener((observableValue, number, t1) -> {
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
