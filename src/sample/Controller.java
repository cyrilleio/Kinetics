package sample;

import animations.interpolator.Interpolation;
import animations.timer.Timer;
import experiment.*;
import geometry.Point;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import shape.ShapeGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML Circle recording;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final double width = 300;
        Point center = new Point(450,300);
        double a = 0.5 ; // rad/s/s
        double v = Math.PI/10; // rad /s
        Duration time = Duration.seconds(Interpolation.QUADRATIC.time(a));
        Interpolator interpolator = Interpolation.QUADRATIC;

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setDelay(Duration.millis(1000));
        timeline.setAutoReverse(true);
        //timeline.getKeyFrames().add(new KeyFrame(Duration.millis(600),
                //new KeyValue(recording.strokeWidthProperty(), 4.0, Interpolator.EASE_BOTH)));
        //timeline.play();

    }

}
