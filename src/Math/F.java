package Math;

import org.apache.commons.math3.analysis.UnivariateFunction;

public class F implements UnivariateFunction {
    private final double m;
    public F(double m){
        this.m =m;
    }
    @Override
    public double value(double phi) {
        return Math.sqrt( 1 - this.m* Math.pow(Math.sin(phi),2));
    }
    public final double getValue(){
        return this.m;
    }
}
