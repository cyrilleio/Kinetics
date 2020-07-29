package experiment;

import animations.AnimationPolygon;
import geometry.Point;
import javafx.animation.Interpolator;
import javafx.util.Duration;
import shape.TriangleGroup;
import structures.Constant;
import structures.DataCollection;

public class ExperimentTriangle extends ExperimentBase {
    public ExperimentTriangle(Point pos, double l, double space, Duration time, Interpolator interpolator,double orientation,int times){
        this.times = times;
        EXPERIMENT_TYPE = Constant.TRIANGLE_TYPE;
        this.animation = new AnimationPolygon(time, interpolator, Constant.TRIANGLE_DIM);
        this.data = new DataCollection(Constant.TRIANGLE_TYPE);
        this.uData = new DataCollection(Constant.TRIANGLE_TYPE);
        this.shape = new TriangleGroup(pos, l,space);
        bindProperties();
        initShapeAndSensor(orientation);
    }
}
