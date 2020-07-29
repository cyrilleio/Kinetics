package experiment;

import animations.AnimationPolygon;
import geometry.Point;
import javafx.animation.Interpolator;
import javafx.util.Duration;
import shape.OctagonGroup;
import structures.Constant;
import structures.DataCollection;

public class ExperimentOctagon extends ExperimentBase{
    public ExperimentOctagon(Point pos, double width, double space, Duration time, Interpolator interpolator,double orientation,int times){
        this.times = times;
        EXPERIMENT_TYPE = Constant.OCTAGON_TYPE;
        this.animation = new AnimationPolygon(time, interpolator, Constant.OCTAGON_DIM);
        this.data = new DataCollection(Constant.OCTAGON_TYPE);
        this.uData = new DataCollection(Constant.OCTAGON_TYPE);
        this.shape = new OctagonGroup(pos, width,space);
        bindProperties();
        initShapeAndSensor(orientation);
    }
}
