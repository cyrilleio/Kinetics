package simulation;

import experiment.ExperimentBase;
import experiment.ExperimentObjectRepresentation;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import structures.Constant;
import structures.DataCollection;
import structures.ListDataCollection;
import java.util.ArrayList;

public abstract class SimulationBase extends Parent {
    protected int animationPlayedCounter = 0;
    protected SimpleIntegerProperty currently;
    protected SimpleBooleanProperty recordingProperty;
    protected SimpleBooleanProperty simulationEndedProperty;
    protected SimpleBooleanProperty currentAnimationEndedProperty;
    public SimpleIntegerProperty circleTypeCounter,ellipseTypeCounter,triangleTypeCounter,
            rectangleTypeCounter,hexagonTypeCounter,octagonTypeCounter;
    protected int circleCounter=0,ellipseCounter=0,rectangleCounter=0,triangleCounter=0,hexagonCounter=0,octagonCounter=0;
    protected ListDataCollection data;
    public ObservableList<ExperimentBase> getExperiments() {
        return experiments;
    }

    public void setExperiments(ObservableList<ExperimentObjectRepresentation> experiments) {
        ArrayList<ExperimentBase> _experiments = new ArrayList<>();
        int count;
        for (count=0;count<experiments.size();++count){
            ExperimentBase _experiment = experiments.get(count).buildObject();
            _experiments.add(_experiment);
        }
        this.experiments.setAll(_experiments);
        this.getChildren().setAll(this.experiments.get(0).getShape());
    }
    public void addExperiment(ExperimentObjectRepresentation objectRepresentation){

        ExperimentBase _experiment = objectRepresentation.buildObject();
        int times = objectRepresentation.times;
        int nbr = experiments.size();
        if (times>1){
            _experiment.boundTo = getCountValue(_experiment.EXPERIMENT_TYPE);
            for(int i=0;i<times;++i){
               experiments.add(_experiment);
            }
        }
        else {
            experiments.add(_experiment);
        }
        if (nbr==0){
            this.getChildren().setAll(this.experiments.get(0).getShape());
        }
        updateCounter(_experiment.EXPERIMENT_TYPE,times);
        permuteSequence();
    }

    protected ObservableList<ExperimentBase> experiments;
    public final void render(){}
    public final void hide() {}
    public final void stop() {}
    public  void pause(){}
    public final void play(){}
    public final void delay(double d){}
    public final void trigger(){}
    public abstract void launch();
    public void continueSequence(int nowNbr){
        this.currently.set(nowNbr + 1 );
        try {
            Thread.sleep(Constant.BETWEEN_EXPERIMENT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.experiments.get(nowNbr+1).launch();
    }
    public int getTotalExperimentsNumber(){
        return this.experiments.size();
    }
    public void addToData(DataCollection data, DataCollection uData){
        this.data.add(data);
        this.data.add(uData);
    }
    public void printData(){
        System.out.println(String.format("%d experiments data saved", this.data.size()/2));
    }
    public void plotData(){
        this.data.plotData();
    }
    public void updateCounter(int experimentType,int times){
        switch (experimentType){
            case Constant.CIRCLE_TYPE:
                circleCounter += times;
                this.circleTypeCounter.set(circleCounter);
                break;
            case Constant.ELLIPSE_TYPE:
                ellipseCounter += times;
                this.ellipseTypeCounter.set(ellipseCounter);
                break;
            case Constant.TRIANGLE_TYPE:
                triangleCounter += times;
                this.triangleTypeCounter.set(triangleCounter);
                break;
            case Constant.RECTANGLE_TYPE:
                rectangleCounter += times;
                this.rectangleTypeCounter.set(rectangleCounter);
                break;
            case Constant.HEXAGON_TYPE:
                hexagonCounter += times;
                this.hexagonTypeCounter.set(hexagonCounter);
                break;
            case Constant.OCTAGON_TYPE:
                octagonCounter += times;
                this.octagonTypeCounter.set(octagonCounter);
                break;
            default:
                break;
        }
    }
    public int getCountValue(int experimentType){
        switch (experimentType){
            case Constant.CIRCLE_TYPE:
                return this.circleCounter;
            case Constant.ELLIPSE_TYPE:
                return this.ellipseCounter;
            case Constant.TRIANGLE_TYPE:
                return this.triangleCounter;
            case Constant.RECTANGLE_TYPE:
                return this.rectangleCounter;
            case Constant.HEXAGON_TYPE:
                return this.hexagonCounter;
            case Constant.OCTAGON_TYPE:
                return this.octagonCounter;
            default:
                return -1;
        }
    }
    public void permuteSequence(){
        System.out.println(this.experiments);
        java.util.Collections.shuffle(this.experiments);
        System.out.println(this.experiments);
    }
}