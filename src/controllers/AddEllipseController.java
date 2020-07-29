package controllers;

import experiment.ExperimentObjectRepresentation;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import simulation.Sequential;
import structures.Constant;

import java.util.regex.Pattern;

public class AddEllipseController extends CreateShapeController{
    @FXML private TextField secondRadiusTextField;
    private double secondRadiusValue;
    public AddEllipseController(Sequential simulation, int type, String title) {
        super(simulation, type, title, "Radius a", Constant.ELLIPSE_FORM_REP);
    }
    @Override
    public void initialize(){
        super.initialize();
        Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]*");
        Pattern numberPattern = Pattern.compile("[0-9]*");
        setRegex(pattern,secondRadiusTextField);
    }
    @Override
    public void retrieveDataFromTextFields(){
        super.retrieveDataFromTextFields();
        secondRadiusValue = parseDouble(secondRadiusTextField.getText())*Constant.PIXEL_PER_CM;
    }
    @Override
    protected void handleApplyButtonAction(){
        this.retrieveDataFromTextFields();
        objectRepresentation = new ExperimentObjectRepresentation(Constant.ELLIPSE_TYPE,speedValue,accelerationValue,radiusValue,secondRadiusValue,spaceValue,orientationValue,timesValue);
        System.out.println(objectRepresentation);
        this.simulation.addExperiment(objectRepresentation);
    }
}

