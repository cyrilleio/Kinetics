package properties;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import structures.Complex;
import structures.TemporalPoint;

import java.time.Instant;

public class MeasureProperty implements ComplexProperty, javafx.event.EventHandler {
    private final SimpleDoubleProperty x, y;
    public MeasureProperty(){
        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();
    }
    @Override
    public void bind(MeasureProperty measureProperty) {
        this.x.bind(measureProperty.x);
        this.y.bind(measureProperty.y);
    }



    @Override
    public void unbind() {
        this.x.unbind();
        this.y.unbind();
    }

    public boolean isBound() {
        return this.x.isBound() || this.y.isBound();
    }


    public Complex get() {
        return new Complex(this.x.get(),this.y.get());
    }

    @Override
    public void set(Complex z) {
        this.x.set(z.getReal());
        this.y.set(z.getImag());
    }

    @Override
    public void handle(Event event) {

    }
}
