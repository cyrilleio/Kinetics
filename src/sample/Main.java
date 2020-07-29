package sample;
import controllers.ChartController;
import controllers.CreateShapeController;
import controllers.MainController;
import experiment.ExperimentObjectRepresentation;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.stage.Stage;
import simulation.Sequential;

public class Main extends Application {
    private ObservableList<ExperimentObjectRepresentation> strings = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Sequential simulation = new Sequential();
        MainController mainController = new MainController(simulation);
        mainController.showStage();
        //ChartController chartController = new ChartController();
        //chartController.showStage();

    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void stop(){

    }
}
