package structures;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class DataCollection extends ArrayList<TemporalPoint> {
    public final int DATA_TYPE;
    public DataCollection(int dataType){
        this.DATA_TYPE = dataType;
    }
    public ArrayList<Complex> getXYCoordinates(){
        Complex z= new Complex(0,0);
        if (DATA_TYPE!=Constant.CIRCLE_TYPE && DATA_TYPE!=Constant.ELLIPSE_TYPE){
            // Then we need to translate to center
            z = new Complex(Constant.CENTER.getX(),Constant.CENTER.getY());
        }
        ArrayList<Complex> xyCoordinates = new ArrayList<>();
        for (TemporalPoint temporalPoint:this){
            xyCoordinates.add(temporalPoint.getZ().minus(z));
        }
        return xyCoordinates;
    }
    public ArrayList<Long> getMillisCoordinates(){
        if (this.size()<1){
            return null;
        }
        Instant temporalOrigin = this.get(0).getT();
        ArrayList<Long> millisCoordinates = new ArrayList<>();
        for(TemporalPoint temporalPoint:this){
            millisCoordinates.add(Duration.between(temporalOrigin,temporalPoint.getT()).toMillis());
        }
        return millisCoordinates;
    }
    public ArrayList<Complex> getTXCoordinates(){
        if (this.size()<1){
            return null;
        }
        ArrayList<Complex> txCoordinates = new ArrayList<>();
        ArrayList<Long> time = getMillisCoordinates();
        for (int i=0;i<this.size();++i){
            txCoordinates.add(new Complex(time.get(i),this.get(i).getZ().getReal()));
        }
        return txCoordinates;
    }
    public ArrayList<Complex> getTYCoordinates(){
        if (this.size()<1){
            return null;
        }
        ArrayList<Complex> txCoordinates = new ArrayList<>();
        ArrayList<Long> time = getMillisCoordinates();
        for (int i=0;i<this.size();++i){
            txCoordinates.add(new Complex(time.get(i),this.get(i).getZ().getImag()));
        }
        return txCoordinates;
    }
    @Override
    public String toString(){
        ArrayList<Long> millisCoordinates = getMillisCoordinates();
        StringBuilder formatted = new StringBuilder();
        for (int i=0;i<this.size();++i){
            formatted.append(String.format("%s;%d \n", this.get(i).getZ(), millisCoordinates.get(i)));
        }
        return formatted.toString();
    }
}
