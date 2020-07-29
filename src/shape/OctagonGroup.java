package shape;

import geometry.Line;
import geometry.Octagon;
import geometry.Point;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import structures.Complex;
import structures.Constant;
import java.util.ArrayList;

public class OctagonGroup extends ShapeGroup {
    private final Octagon o0, o1, o2, o3;
    public OctagonGroup(Point center, double width, double space){
        this.space = space;
        this.center= center;
        this.o0 = new Octagon(center,width,width/3);
        this.o1 = o0.duplicate(space);
        this.o2 = o1.duplicate(space);
        this.o3 = this.o0.duplicate(-Constant.SHAPE_STROKE_WIDTH);
        this.observable = this.o1.duplicate(0);
        this.observable.setStrokeWidth(this.space);
        this.observable.setStroke(Constant.OBSERVABLE_SHAPE_STROKE);
        this.observable.setFill(Constant.OBSERVABLE_SHAPE_FILL);
        this.o3.setFill(null);
        this.o3.setStroke(Constant.OUTER_OBSERVABLE_FILL);
        this.o3.setStrokeWidth(2*space);
        this.o3.setStrokeType(StrokeType.OUTSIDE);
        this.children = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.property = new SimpleDoubleProperty();
        bindCursor(this.o1.getO1());
        build();
    }
    public void build() {
        bindArc();
        this.children.add(this.o0);
        this.children.add(this.o1);
        this.children.add(this.o2);


        this.lines.add(new Line(this.o1.getO1(),this.o1.getC1()));
        this.lines.add(new Line(this.o1.getC1(),this.o1.getS1()));
        this.lines.add(new Line(this.o1.getS1(),this.o1.getC11()));
        this.lines.add(new Line(this.o1.getC11(),this.o1.getO2()));


        this.lines.add(new Line(this.o1.getO2(),this.o1.getC2()));
        this.lines.add(new Line(this.o1.getC2(),this.o1.getS2()));
        this.lines.add(new Line(this.o1.getS2(),this.o1.getC22()));
        this.lines.add(new Line(this.o1.getC22(),this.o1.getO3()));

        this.lines.add(new Line(this.o1.getO3(),this.o1.getC3()));
        this.lines.add(new Line(this.o1.getC3(),this.o1.getS3()));
        this.lines.add(new Line(this.o1.getS3(),this.o1.getC33()));
        this.lines.add(new Line(this.o1.getC33(),this.o1.getO4()));

        this.lines.add(new Line(this.o1.getO4(),this.o1.getC4()));
        this.lines.add(new Line(this.o1.getC4(),this.o1.getS4()));
        this.lines.add(new Line(this.o1.getS4(),this.o1.getC44()));
        this.lines.add(new Line(this.o1.getC44(),this.o1.getO1()));
        this.children.addAll(this.lines);
        this.children.add(this.cursor);
        this.children.add(this.observable);
        this.children.add(this.o3);
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


