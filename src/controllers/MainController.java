package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import simulation.Sequential;
import structures.Constant;

import java.io.IOException;

public class MainController extends Controller {
    @FXML private VBox addCircleExperiment, addEllipseExperiment,addTriangleExperiment;
    @FXML private VBox addRectangleExperiment,addHexagonExperiment,addOctagonExperiment;
    @FXML private ProgressIndicator simulationProgressIndicator;
    @FXML private Label ellipseCounter,circleCounter,triangleCounter,
            octagonCounter,hexagonCounter,rectangleCounter;
    private Group group;
    private Parent parent;
    private final Sequential simulation;

    int nn=0;
    @FXML private Button openConfigButton;
    public MainController(Sequential simulation){
        this.simulation = simulation;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/main.fxml"));

            // Set this class as the controller
            loader.setController(this);
            // Load the scene
            parent = loader.load();
            group = new Group(parent);
            group.getChildren().add(simulation);
            stage.setScene(new Scene(group));

            // Setup the window/stage
            stage.setTitle("Add");
            circleCounter.textProperty().bind(simulation.circleTypeCounter.asString());
            ellipseCounter.textProperty().bind(simulation.ellipseTypeCounter.asString());
            rectangleCounter.textProperty().bind(simulation.rectangleTypeCounter.asString());
            triangleCounter.textProperty().bind(simulation.triangleTypeCounter.asString());
            hexagonCounter.textProperty().bind(simulation.hexagonTypeCounter.asString());
            octagonCounter.textProperty().bind(simulation.octagonTypeCounter.asString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stage.setTitle(Constant.APP_NAME);
        this.stage.initStyle(StageStyle.DECORATED);
        simulationProgressIndicator.visibleProperty().bind(simulation.recordingProperty());
        group.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
int totalNbr = simulation.getTotalExperimentsNumber();
                if (event.getCode() == KeyCode.L) {
                    if (nn != 0) {
                        nn = nn - 1;
                        simulation.setCurrently(nn);
                    }
                }
                if (event.getCode()==KeyCode.R){
                    nn = (nn==totalNbr-1)?0:nn +1;
                    simulation.setCurrently(nn);
                }
                if(event.getCode()==KeyCode.P){
                    simulation.launch();
                }
            }
        });
    }
    @FXML
    public void initialize(){
        openConfigButton.setOnAction(event -> { new ConfigController().showStage();});
        addCircleExperiment.setOnMouseClicked(event -> {handleAddExperimentButton(Constant.CIRCLE_TYPE,"circle","Radius");});
        addRectangleExperiment.setOnMouseClicked(event -> {handleAddExperimentButton(Constant.RECTANGLE_TYPE,"rectangle","Length");});
        addTriangleExperiment.setOnMouseClicked(event -> {handleAddExperimentButton(Constant.TRIANGLE_TYPE,"triangle","Altitude");});
        addHexagonExperiment.setOnMouseClicked(event -> {handleAddExperimentButton(Constant.HEXAGON_TYPE,"hexagon","Radius");});
        addEllipseExperiment.setOnMouseClicked(event -> {handleAddEllipseExperiment();});
        addOctagonExperiment.setOnMouseClicked(event -> {handleAddExperimentButton(Constant.OCTAGON_TYPE,"octagon","Radius");});
    }

    public void handleAddExperimentButton(int type, String title,String paramName){
        System.out.println("clicked");
        new CreateShapeController(simulation,type,title,paramName,Constant.GENERIC_FORM_REP).showStage();
    }
    public void handleAddEllipseExperiment(){
        new AddEllipseController(simulation,Constant.ELLIPSE_TYPE,"ellipse").showStage();
    }
    public void destroy(){
    }
}