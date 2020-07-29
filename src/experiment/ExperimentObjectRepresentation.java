package experiment;

import animations.interpolator.Interpolation;
import geometry.Circle;
import javafx.util.Duration;
import structures.Constant;

public class ExperimentObjectRepresentation {
    public final int id,times;
    public final double speed,acc,lcar1,lcar2,space,orientation;
    public String name;
    public ExperimentObjectRepresentation(int id, double speed,double acc,double lcar1,double lcar2,double space,double orientation,int times){
        this.id = id;
        this.times = times;
        this.acc = acc;
        this.lcar1 = lcar1;
        this.lcar2 = lcar2;
        this.speed = speed;
        this.space = space;
        this.orientation = orientation;
        this.name = Constant.EXPERIMENT_BY_ID[id];
    }
    @Override
    public String toString(){
        return String.format("%s of %.2f cm @ %.2f speed",this.name,this.lcar1,this.speed);
    }
    public ExperimentBase buildObject(){
        Interpolation interpolation=Interpolation.LINEAR;
        Duration time = Duration.seconds(0);
        if (Double.compare(this.speed,0.d)==1){
            time = Duration.seconds(interpolation.time(this.speed));
        }
        if(Double.compare(this.acc,0.d)==1){
            interpolation = Interpolation.QUADRATIC;
            time = Duration.seconds(interpolation.time(this.acc));
        }
        switch (this.id){
            case 0:
                return new ExperimentCircle(Constant.CENTER,this.lcar1,this.space,time,interpolation,this.times);
            case 1:
                return new ExperimentTriangle(Constant.CENTER,this.lcar1,this.space,time,interpolation,orientation,times);
            case 2:
                return new ExperimentEllipse(Constant.CENTER,this.lcar1,this.lcar2,this.space,time,interpolation,orientation,times);
            case 3:
                return new ExperimentRectangle(Constant.CENTER,this.lcar1,this.space,time,interpolation,orientation,times);
            case 4:
                return new ExperimentHexagon(Constant.CENTER,this.lcar1,this.space,time,interpolation,orientation,times);
            case 5:
                return new ExperimentOctagon(Constant.CENTER,this.lcar1,this.space,time,interpolation,orientation,times);
            default:
                return null;
        }
    };
}
