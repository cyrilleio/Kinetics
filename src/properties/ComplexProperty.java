package properties;

import structures.Complex;

public interface ComplexProperty {
    public void bind(MeasureProperty measureProperty);
    public void unbind();
    public Complex get();
    public void set(Complex z);
}
