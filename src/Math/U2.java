package Math;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NoBracketingException;

public class U2 extends U{
    public U2(double a, double b, double c) {
        super(a, b, c);
    }
    @Override
    public double value(double t) throws NoBracketingException {
        UnivariateFunction g = new UnivariateFunction() {
            @Override
            public double value(double v) {
                return T.value(v) - c*Math.pow(t,2)/(2*b);
            }
        };
        return brent.solve(1000,g,0.d,2*Math.PI);
    }
}
