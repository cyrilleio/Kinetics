package experiment;

import animations.AnimationPolygon;
import geometry.Point;
import javafx.animation.Interpolator;
import javafx.util.Duration;
import shape.EllipseGroup;
import structures.Constant;
import structures.DataCollection;

public class ExperimentEllipse extends ExperimentBase {
    public ExperimentEllipse(Point pos, double radiusX, double radiusY, double space, Duration time, Interpolator interpolator,double orientation,int times){
        this.times = times;
        EXPERIMENT_TYPE = Constant.ELLIPSE_TYPE;
        this.animation = new AnimationPolygon(time, interpolator,Constant.CIRCLE_DIM);
        this.data = new DataCollection(Constant.ELLIPSE_TYPE);
        this.uData = new DataCollection(Constant.ELLIPSE_TYPE);
        this.shape = new EllipseGroup(pos, radiusX, radiusY,space);
        bindProperties();
        initShapeAndSensor(orientation);
    }
}