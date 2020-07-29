package controllers;

import experiment.ExperimentObjectRepresentation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import simulation.Sequential;
import structures.Constant;
import java.io.IOException;
import java.util.regex.Pattern;

public class CreateShapeController extends Controller{
    @FXML
    private TextField speedTextField, accelerationTextField,radiusTextField,
            orientationTextField,spaceTextField,trackRadiusTextField,timesTextField;
    double speedValue, spaceValue, accelerationValue,radiusValue,
            orientationValue,trackRadiusValue;
    int timesValue;
    ExperimentObjectRepresentation objectRepresentation;
    @FXML private Button addButton;
    @FXML private Label paramLabel;
    private final int type;
    protected final Sequential simulation;
    private String title;
    public CreateShapeController(Sequential simulation, int type,String title, String paramName, String rep){
        this.type = type;
        this.title = title;
        this.simulation = simulation;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rep));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            stage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            stage.setTitle("Add " + title);
            paramLabel.setText(paramName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize() {
        Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]*");
        Pattern orientationPattern = Pattern.compile("-?[0-9]*\\.?[0-9]*");
        Pattern numberPattern = Pattern.compile("[0-9]*");
        setRegex(pattern,speedTextField);
        setRegex(pattern,radiusTextField);
        setRegex(pattern,accelerationTextField);
        setRegex(orientationPattern,orientationTextField);
        setRegex(pattern,spaceTextField);
        //setRegex(pattern,trackRadiusTextField);
        setRegex(numberPattern,timesTextField);
        addButton.setOnAction(event -> {handleApplyButtonAction(); stage.hide();});
    }

    protected void setRegex(Pattern pattern, TextField textField){
        TextFormatter<?> formatter = new TextFormatter<>(change ->{
            if (pattern.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                // todo: add error message/markup
                return null; // prevent change
            }
        });
        textField.setTextFormatter(formatter);

    }
    @FXML
    protected void handleApplyButtonAction(){
        retrieveDataFromTextFields();
        objectRepresentation = new ExperimentObjectRepresentation(type,speedValue,accelerationValue,radiusValue,radiusValue,spaceValue,orientationValue,timesValue);
        System.out.println(objectRepresentation);
        simulation.addExperiment(objectRepresentation);
    }
    protected double parseDouble(String string){
        return string.isEmpty()?0.d:Double.parseDouble(string);
        // mettre un marquage pour la derniere fig et demarrage retarte sur chaque figure
        // taille curseur dernoier param
    }
    protected int parseInt(String string){
        return string.isEmpty()?0:Integer.parseInt(string);
    }
    public void retrieveDataFromTextFields(){
        speedValue = parseDouble(speedTextField.getText());
        spaceValue = parseDouble(spaceTextField.getText()) * Constant.PIXEL_PER_CM;
        accelerationValue = parseDouble(accelerationTextField.getText())*Constant.PIXEL_PER_CM;
        radiusValue = parseDouble(radiusTextField.getText())*Constant.PIXEL_PER_CM;
        orientationValue = parseDouble(orientationTextField.getText());
        //trackRadiusValue = parseDouble(trackRadiusTextField.getText());
        timesValue = parseInt(timesTextField.getText());
    }
}
