package Math;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.exception.NoBracketingException;

public class U implements UnivariateFunction {
    protected final double a,b,m;
    protected final double c;
    protected final E T;
    protected final double length;
    protected final BrentSolver brent;
    public U(double a, double b, double c){
        double e = Math.sqrt(1 - Math.pow(b/a,2));
        this.a = a;
        this.b = b;
        this. m = Math.pow(e,2)/(Math.pow(e,2) -1);
        T = new E(this.m);
        this.c = c;
        this.brent = new BrentSolver(1e-15, 1e-13);
        length = 4*a*(new E(e*e).value(Math.PI/2));

    }
    @Override
    public double value(double t) throws NoBracketingException {
        UnivariateFunction g = new UnivariateFunction() {
            @Override
            public double value(double v) {
                return T.value(v) - c*t/b;
            }
        };
        return brent.solve(1000,g,0.d,2*Math.PI);
    }
    public final double getTime(){
        return this.length/this.c;
    }
}