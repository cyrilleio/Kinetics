package animations;

import javafx.animation.Interpolator;
import javafx.util.Duration;
import structures.Constant;

public class AnimationPolygon extends AnimationBase {
    public AnimationPolygon(Duration time, Interpolator interpolator, double dim){
        super();
        this.interpolator = interpolator;
        this.endValue = dim - Constant.DELTA_DIM_ANIMATION;
        this.time = time;
        setTimeline();
    }

}

