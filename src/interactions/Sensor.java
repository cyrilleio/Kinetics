package interactions;

import animations.timer.Timer;
import geometry.Point;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.shape.Shape;
import shape.ShapeGroup;
import structures.Complex;
import structures.Constant;

import java.util.TimerTask;

public class Sensor {
    private Shape observable;
    private Shape outerObservable;
    private boolean recording;
    public final Timer timer;
    private TimerTask task;
    private ShapeGroup shape;
    private EventHandler<TouchEvent> enteredEvent;
    private EventHandler<TouchEvent> exitedEvent;
    private EventHandler<TouchEvent> movedEvent;
    public final ObjectProperty<Complex> measure;
    private Complex m;
    private final SimpleBooleanProperty unauthorisedMovementProperty;
    public Sensor(){
        this.recording = false;
        this.timer = new Timer();
        this.measure = new SimpleObjectProperty<Complex>();
        this.unauthorisedMovementProperty = new SimpleBooleanProperty(false);
    }
    public Complex getValue(){
        return this.m;
    }
    public void bindEvents(){
        movedEvent = new EventHandler<TouchEvent>() {
            @Override
            public void handle(TouchEvent event) {
                Point point;
                Complex z = new Complex(event.getTouchPoint().getX(), event.getTouchPoint().getY());
                //point = new Point(z);
                z = z.minus(shape.getCenter().getAffix()).conjugate();
                m = z;
            }
        };

        enteredEvent = new EventHandler<TouchEvent>() {
            @Override
            public void handle(TouchEvent event) {
                System.out.println("mouse leaved the area");
            }
        };
        exitedEvent = new EventHandler<TouchEvent>() {
            @Override
            public void handle(TouchEvent event) {
                System.out.println("mouse leaved the area");
            }
        };
    }

    public void record(double frequency){
        task = new TimerTask() {
            @Override
            public void run() {
                measure.setValue(m);
                //measure.setValue(shape.getCurrentPosition());
            }
        };
        this.timer.scheduleAtFixedRate(task, Constant.DELAY_BEFORE_EXPERIMENT,(long) (1000/frequency));
        this.recording = true;
    }
    public void pause(){
        this.recording = false;
    }
    public boolean recording(){
        return this.recording;
    }
    public void sensitivity(double v){}
    public void unbindEvents(){
        this.outerObservable.removeEventHandler(TouchEvent.ANY,enteredEvent);
        this.outerObservable.removeEventHandler(TouchEvent.ANY,exitedEvent);
        this.observable.removeEventHandler(TouchEvent.ANY,movedEvent);
    }
    public void handleEvents(){}
    public void setObservable(Shape observable){
        this.observable = observable;
    }
    public void stopRecording(){
        this.task.cancel();
    }
    public void attach(){

        this.observable.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Point point;
                Complex z = new Complex(mouseEvent.getX(), mouseEvent.getY());
                //point = new Point(z);
                z = z.minus(shape.getCenter().getAffix()).conjugate();
                m = z;
            }
        });
        this.observable.setOnTouchMoved(movedEvent);
    }
    public void attachShape(ShapeGroup shape){
        this.shape = shape;
        this.observable = shape.getObservableShape();
        this.outerObservable = shape.getOuterObservableShape();
    }
    public void detachShape(){
        //this.observable.removeEventHandler(MouseEvent.ANY,);
    }
}