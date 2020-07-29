package animations;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import structures.Constant;

public abstract class AnimationBase implements AnimationType{
    protected Timeline timeline;
    protected Duration time;
    protected Interpolator interpolator;
    protected double endValue;
    public SimpleBooleanProperty playedProperty;
    protected DoubleProperty property;
    public AnimationBase(){
        playedProperty = new SimpleBooleanProperty(false);
    }

    public void setTime(Duration time) {
        this.time = time;
    }


    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    public void setEndValue(double endValue) {
        this.endValue = endValue;
    }


    public Duration getTime() {
        return time;
    }

    public Interpolator getInterpolator() {
        return interpolator;
    }

    public DoubleProperty movementProperty() {
        return property;
    }

    @Override
    public void play() {
        this.timeline.play();
        this.playedProperty.setValue(true);
    }

    @Override
    public void pause() {
        this.timeline.pause();
        this.playedProperty.setValue(false);
    }

    @Override
    public void setTimeline() {
        this.property = new SimpleDoubleProperty();
        this.timeline = new Timeline();
        this.timeline.setDelay(Duration.millis(Constant.DELAY_BEFORE_EXPERIMENT));
        this.timeline.setCycleCount(1);
        this.timeline.setAutoReverse(false);
        this.timeline.getKeyFrames().add(new KeyFrame(this.time,
                new KeyValue(this.property, this.endValue, this.interpolator)));
    }

    @Override
    public void playFrom(Duration time) {
        this.timeline.playFrom(time);
        this.playedProperty.setValue(true);
    }

    @Override
    public void playFromStart() {
        this.timeline.playFromStart();
        this.playedProperty.setValue(true);
    }

    @Override
    public void stop() {
        this.playedProperty.setValue(false);
        this.timeline.stop();
    }

    @Override
    public void onFinished(EventHandler<ActionEvent> event) {
        this.timeline.setOnFinished(event1 -> {
            event.handle(event1);
            this.playedProperty.set(false);
        });
    }

}

