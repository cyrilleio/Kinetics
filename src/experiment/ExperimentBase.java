package experiment;

import animations.AnimationBase;
import interactions.Sensor;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Duration;
import shape.ShapeGroup;
import structures.DataCollection;
import structures.TemporalPoint;
import java.time.Instant;

public abstract class ExperimentBase {
    public int times;
    protected AnimationBase animation;
    protected ShapeGroup shape;
    protected Sensor sensor;
    public int EXPERIMENT_TYPE = -1;
    protected DataCollection data;
    protected DataCollection uData;
    public int boundTo = -1;
    public SimpleBooleanProperty recordingProperty;
    public ExperimentBase(){
        this.recordingProperty = new SimpleBooleanProperty();

    }
    public final void pause(){
        this.animation.pause();
    };
    public final void launch(){
        this.animation.play();
        record(10);
    };
    public final void playFromStart(){
        this.animation.playFromStart();
    }
    public final void playFrom(Duration time){
        this.animation.playFrom(time);
    }
    public final void stop(){
        this.animation.stop();
    }
    public final ShapeGroup getShape(){return this.shape;}
    public final void record(double frequency){
        this.sensor.record(frequency);
    }
    public final void stopRecording(){
        this.sensor.stopRecording();
    }
    public final void stopRecordingOnFinished(){
        this.animation.onFinished(actionEvent -> {
            times--;
            stopRecording();
            restart();
        });
    }
    public final void addToData(TemporalPoint temporalPoint){
        this.data.add(temporalPoint);
    }
    public final void addToUData(TemporalPoint temporalPoint){
        this.uData.add(temporalPoint);
    }
    public final DataCollection getData(){
        return this.data;
    }
    public final DataCollection getUData(){
        return this.uData;
    }
    public void restart(){
        this.animation.movementProperty().setValue(0.d);
        this.shape.unbindArc();
    }
    protected final void captureData(){
        this.animation.movementProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                TemporalPoint uTemporalPoint = new TemporalPoint(sensor.getValue(), Instant.now());
                TemporalPoint temporalPoint = new TemporalPoint(shape.getCurrentPosition(), Instant.now());
                addToData(temporalPoint);
                addToUData(uTemporalPoint);
            }
        });
    }
    protected final void bindProperties(){
        this.shape.getProperty().bind(this.animation.movementProperty());
        this.recordingProperty.bind(this.animation.playedProperty);
    }
    protected final void initShapeAndSensor(double orientation){
        this.shape.rotate(orientation*Math.PI/180);
        this.sensor = new Sensor();
        this.sensor.attachShape(this.shape);
        stopRecordingOnFinished();
        this.sensor.attach();
        captureData();
    }
    public void playAgain(){
        playFromStart();
    }
}
