package animations.interpolator;

import javafx.animation.Interpolator;
import Math.U;
import Math.U2;
public abstract class Interpolation extends Interpolator{
    public double a;
    public double time;
    public Interpolation(){
        super();
    }
    @Override
    protected abstract double curve(double v);
    public abstract double time(double a);
    public static final Interpolation QUADRATIC = new Interpolation() {
        @Override
        protected double curve(double v) {
            return v*v;
        }

        @Override
        public double time(double a) {
            return Math.sqrt(2*Math.PI / a);
        }
    };
    public static final Interpolation LINEAR = new Interpolation() {
        @Override
        public double curve(double v) {
            return v;
        }

        @Override
        public double time(double a) {
            return 2*Math.PI/a;
        }
    };
    public static final class CONSTANT extends Interpolation{
        protected final U u_f ;
        public CONSTANT(double a, double b, double c){
            this.u_f = new U(a,b,c);
            this.time = u_f.getTime() -1e-4;
        }
        @Override
        protected double curve(double v) {
            return u_f.value(v*time) / (2*Math.PI);
        }

        @Override
        public double time(double a) {
            return this.time;
        }
    };
    public static final class ACCELERATION extends Interpolation{
        protected final U2 u_f ;
        public ACCELERATION(double a, double b, double c){
            this.u_f = new U2(a,b,c);
            this.time = u_f.getTime() -1e-4;
        }
        @Override
        protected double curve(double v) {
            return u_f.value(v*time) / (2*Math.PI);
        }

        @Override
        public double time(double a) {
            return this.time;
        }
    };

    public static final Interpolation ACCELERATION = new Interpolation() {
        protected final U2 u_f = new U2(100,50,50);
        protected final double time = u_f.getTime() -1e-4;
        @Override
        protected double curve(double v) {
            return u_f.value(v*time) / (2*Math.PI);
        }

        @Override
        public double time(double a) {
            return this.time;
        }
    };
}
