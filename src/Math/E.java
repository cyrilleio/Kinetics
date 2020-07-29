package Math;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.RombergIntegrator;

public class E implements UnivariateFunction {
    private final double m;
    private final F f;
    public E(double m){
        this.m =m;
        this.f = new F(m);
    }
    @Override
    public double value(double v) {
        RombergIntegrator integrator = new RombergIntegrator();
        if (Double.compare(v,0.d)==0){
            return 0.d;
        }
        return integrator.integrate(1000,f,0.d,v);
    }
    public final double getValue(){
        return this.m;
    }
}
