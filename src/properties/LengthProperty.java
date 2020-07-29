package properties;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import structures.Complex;

public class LengthProperty extends ObjectProperty<Complex> {

    @Override
    public void bind(ObservableValue<? extends Complex> observableValue) {

    }

    @Override
    public void unbind() {

    }

    @Override
    public boolean isBound() {
        return false;
    }

    @Override
    public Object getBean() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Complex get() {
        return this.getValue();
    }

    @Override
    public void set(Complex complex) {
        this.setValue(complex);
    }

    @Override
    public void addListener(ChangeListener<? super Complex> changeListener) {

    }

    @Override
    public void removeListener(ChangeListener<? super Complex> changeListener) {

    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }
}
