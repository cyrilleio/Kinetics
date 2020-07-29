package experiment;

import animations.AnimationPolygon;
import geometry.Point;
import javafx.animation.Interpolator;
import javafx.util.Duration;
import shape.HexagonGroup;
import structures.Constant;
import structures.DataCollection;

public class ExperimentHexagon extends ExperimentBase{
    public ExperimentHexagon(Point pos, double radius, double space, Duration time, Interpolator interpolator,double orientation,int times){
        this.times = times;
        EXPERIMENT_TYPE = Constant.HEXAGON_TYPE;
        this.animation = new AnimationPolygon(time, interpolator, Constant.HEXAGON_DIM);
        this.data = new DataCollection(Constant.HEXAGON_TYPE);
        this.uData = new DataCollection(Constant.HEXAGON_TYPE);
        this.shape = new HexagonGroup(pos, radius,space);
        bindProperties();
        initShapeAndSensor(orientation);
    }
}
