package structures;

public class Complex {
    private double real;   // the real part
    private double imag;   // the imaginary part

    // create a new object with the given real and imaginary parts
    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }
    public Complex(double theta){
        this.real = Math.cos(theta);
        this.imag = Math.sin(theta);
    }
    // return abs/modulus/magnitude
    public double abs() {
        return Math.hypot(this.real, this.imag);
    }

    // return angle/phase/argument, normalized to be between -pi and pi
    public double phase() {
        return Math.atan2(this.imag, this.real);
    }

    // return a new Complex object whose value is (this + b)
    public Complex plus(Complex b) {
        Complex a = this;             // invoking object
        double real = a.real + b.real;
        double imag = a.imag + b.imag;
        return new Complex(real, imag);
    }

    // return a new Complex object whose value is (this - b)
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.real - b.real;
        double imag = a.imag - b.imag;
        return new Complex(real, imag);
    }

    // return a new Complex object whose value is (this * b)
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.real * b.real - a.imag * b.imag;
        double imag = a.real * b.imag + a.imag * b.real;
        return new Complex(real, imag);
    }

    public Complex normalise(){
        return new Complex(this.real,this.imag).scale(1/abs());
    }
    // return a new object whose value is (this * alpha)
    public Complex scale(double alpha) {
        return new Complex(alpha * real, alpha * imag);
    }
    public void scaleAndModify(double alpha){
        this.imag *= alpha;
        this.real *= alpha;
    }

    // return a new Complex object whose value is the conjugate of this
    public Complex conjugate() {
        return new Complex(real, -imag);
    }

    public double getImag() {
        return imag;
    }

    public double getReal() {
        return real;
    }
    @Override
    public String toString(){
        return String.format("%05.2f ; %05.2f",this.real,this.imag);
    }
    @Override
    public boolean equals(Object z){
        if (z == this) {
            return true;
        }
        if (!(z instanceof Complex)) {
            return false;
        }
        Complex c = (Complex) z;
        return Double.compare(this.real, c.real) == 0
                && Double.compare(this.imag, c.imag) == 0;
    }
}


