package experiment;

import animations.AnimationPolygon;
import geometry.Point;
import javafx.animation.Interpolator;
import shape.CircleGroup;
import javafx.util.Duration;
import structures.Constant;
import structures.DataCollection;

public class ExperimentCircle extends ExperimentBase{
    public ExperimentCircle(Point pos, double radius, double space, Duration time, Interpolator interpolation,int times){
        this.times = times;
        EXPERIMENT_TYPE = Constant.CIRCLE_TYPE;
        this.data = new DataCollection(Constant.CIRCLE_TYPE);
        this.uData = new DataCollection(Constant.CIRCLE_TYPE);
        this.animation = new AnimationPolygon(time,interpolation,Constant.CIRCLE_DIM);
        this.shape = new CircleGroup(pos, radius,space);
        bindProperties();
        initShapeAndSensor(0.d);
    }
}