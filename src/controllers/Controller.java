package controllers;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class Controller {
    protected Stage stage;
    protected String title = "Graph";
    public final void showStage(){
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }
}
