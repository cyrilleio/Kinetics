package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.regex.Pattern;

public class ConfigController extends Controller{
    @FXML
    private Label paramLabel;

    @FXML
    private TextField trackerRadius;

    @FXML
    private TextField sequenceDelay;

    @FXML
    private RadioButton showCountdown;

    @FXML
    private RadioButton Mute;

    @FXML
    private TextField spaceTextField;
    public ConfigController(){
        this.stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/view/Untitled2.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            stage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]*");
        Pattern numberPattern = Pattern.compile("[0-9]*");
        setRegex(pattern,trackerRadius);
        setRegex(numberPattern,sequenceDelay);
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

}