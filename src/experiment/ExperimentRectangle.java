package experiment;

import animations.AnimationPolygon;
import geometry.Point;
import javafx.animation.Interpolator;
import javafx.util.Duration;
import shape.RectangleGroup;
import structures.Constant;
import structures.DataCollection;

public class ExperimentRectangle extends ExperimentBase{
    public ExperimentRectangle(Point pos, double width, double space, Duration time, Interpolator interpolator,double orientation,int times){
        this.times = times;
        EXPERIMENT_TYPE = Constant.RECTANGLE_TYPE;
        this.data = new DataCollection(Constant.RECTANGLE_TYPE);
        this.uData = new DataCollection(Constant.RECTANGLE_TYPE);
        this.animation = new AnimationPolygon(time, interpolator, Constant.RECTANGLE_DIM);
        this.shape = new RectangleGroup(pos, width,space);
        bindProperties();
        initShapeAndSensor(orientation);
    }
}