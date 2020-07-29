package structures;

import java.time.Instant;

public class TemporalPoint {
    private final Instant t;
    private Complex z;
    public TemporalPoint(Complex z, Instant t) {
        this.z = z;
        this.t = t;
    }
    public final Instant getT(){
        return t;
    }

    @Override
    public String toString(){
        return String.format("%s;%s",this.z, this.t.toString());
    }
    @Override
    public boolean equals(Object p){
        if (p == this) {
            return true;
        }
        if (!(p instanceof TemporalPoint)) {
            return false;
        }
        TemporalPoint temporalPoint = (TemporalPoint) p;
        return this.z.equals(temporalPoint.z);
    }
    public final Complex getZ(){
        return this.z;
    }
    public void setZ(Complex z){
        this.z = z;
    }
}

