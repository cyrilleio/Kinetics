package simulation;
import experiment.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import structures.DataCollection;
import structures.ListDataCollection;
public class Sequential extends SimulationBase {
    public Sequential(){
        this.currentAnimationEndedProperty = new SimpleBooleanProperty(false);
        this.simulationEndedProperty = new SimpleBooleanProperty(false);
        initExperimentTypeCounter();
        this.data = new ListDataCollection();
        experiments = FXCollections.observableArrayList();
        currently = new SimpleIntegerProperty(-1);
        this.recordingProperty = new SimpleBooleanProperty(false);
        this.recordingProperty.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    playNext();
                }
            }
        });
        this.currently.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                ExperimentBase _experiment = experiments.get((int)t1);
                addToData(_experiment.getData(),_experiment.getUData());
                getChildren().setAll(_experiment.getShape());
                recordingProperty.unbind();
                recordingProperty.bind(_experiment.recordingProperty);
            }
        });
    }
    public ExperimentBase getCurrentExperiment(){
        return this.experiments.get(currently.get());
    }
    public void setCurrently(int nbr){
        this.currently.set(nbr);
    }
    @Override
    public void launch() {
        if (simulationEndedProperty.get()){
            System.out.println("animation ended, you should restart");
        }
        else {
            permuteSequence();
            setCurrently(0);
            this.experiments.get(0).launch();
            animationPlayedCounter =0;
        }

    }
    @Override
    public void pause(){
        getCurrentExperiment().pause();
    }
    public final void addToData(DataCollection data,DataCollection uData){
        this.data.add(data);
        this.data.add(uData);
    }
    public SimpleBooleanProperty recordingProperty(){
        return this.recordingProperty;
    }
    public void initExperimentTypeCounter(){
        this.circleTypeCounter = new SimpleIntegerProperty(0);
        this.ellipseTypeCounter = new SimpleIntegerProperty(0);
        this.rectangleTypeCounter = new SimpleIntegerProperty(0);
        this.octagonTypeCounter = new SimpleIntegerProperty(0);
        this.hexagonTypeCounter = new SimpleIntegerProperty(0);
        this.triangleTypeCounter = new SimpleIntegerProperty(0);
    }
    private void playNext(){
        animationPlayedCounter++;
        if (this.animationPlayedCounter != experiments.size()){
            currently.set(animationPlayedCounter);
            experiments.get(animationPlayedCounter).launch();
        }
        else{
            simulationEndedProperty.set(true);
            this.plotData();
        }
    }
}