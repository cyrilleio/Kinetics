package structures;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Chart extends Application {
    public static void main(String[] args) {
        Application.launch(Chart.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../resources/view/Untitled2.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("JavaFX Graph Example");
            stage.setAlwaysOnTop(true);
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.show();
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
