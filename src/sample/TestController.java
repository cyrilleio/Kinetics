package sample;

import experiment.ExperimentObjectRepresentation;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simulation.Sequential;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TestController {
    @FXML private ListView<ExperimentObjectRepresentation> simulation;
    @FXML private ListView<String> experiments;
    @FXML private TextField acc,speed,lCarr;
    @FXML private Button add,remove;
    @FXML private Label hint;
    private final Stage testStage;
    ObservableList<ExperimentObjectRepresentation> strings, string;
    ObservableList<String> strings1;
    SimpleIntegerProperty listSize = new SimpleIntegerProperty();
    public TestController(Sequential simulation){
        this.testStage = new Stage();
        this.strings = FXCollections.observableArrayList();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Temp.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            testStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            testStage.setTitle("Passing Controllers Example - Layout2");

        } catch (IOException e) {
            e.printStackTrace();
        }
        onClose(windowEvent -> {simulation.setExperiments(strings);});
    }

    @FXML
    public void initialize() {
        strings = FXCollections.observableArrayList();
        strings1 = FXCollections.observableArrayList("Cercle", "Triangle","Ellipse","Rectangle","Hexagone","Octagone");
        simulation.setItems(strings);
        experiments.setItems(strings1);
        experiments.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        simulation.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        handle();
        listSize.set(0);
        hint.visibleProperty().bind(listSize.isEqualTo(0));
    }
    public void handle(){
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleAdd();
                listSize.set(strings.size());
            }
        });
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleRemove();
                listSize.set(strings.size());
            }
        });
    }
    public void handleAdd(){
        int selected = experiments.getSelectionModel().getSelectedIndex();
        System.out.println(speed.getText() + acc.getText() + lCarr.getText() );
        System.out.println("item selected" + selected);
        if (selected!=-1){
            strings.add(new ExperimentObjectRepresentation(selected,0.5,0,130,80,30,0,0));
        }

        /*switch (selected){
            case 0:
                strings.add(new ExperimentObjectRepresentation());
                break;
            case 1:
                strings.add("Triangle");
                break;
            case 2:
                strings.add("Ellipse");
                break;
            case 3:
                strings.add("Rectangle");
                break;
            case 4:
                strings.add("Hexagone");
                break;
            case 5:
                strings.add("Octagone");
                break;
            default:
                break;
        }*/
    }
    public void handleRemove(){
        int selected = simulation.getSelectionModel().getSelectedIndex();
        if (selected!=-1){
            strings.remove(selected);
        }
    }
    public ObservableList<ExperimentObjectRepresentation> getObjectRepresentation(){
        return this.strings;
    }
    public void showStage(){
        this.testStage.setResizable(false);
        this.testStage.initModality(Modality.APPLICATION_MODAL);
        this.testStage.showAndWait();
        this.testStage.centerOnScreen();
    }
    @FXML
    public void stop(){
        System.out.println("application closing");
    }
    public void onClose(EventHandler<WindowEvent> event){
        this.testStage.setOnCloseRequest(event);
    }
}
