package structures;

import animations.interpolator.Interpolation;

public abstract class ExperimentObjectConfiguration {
    protected static Interpolation interpolate = Interpolation.LINEAR;
    protected static double l = 10;
    protected static Complex center = new Complex(0,0);
    protected static double space =0;
    protected static double a=0;
    protected static double v=0;
    public ExperimentObjectConfiguration(){
    }
}