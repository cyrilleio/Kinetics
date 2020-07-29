package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import structures.Complex;
import java.io.IOException;
import java.util.ArrayList;

public class ChartController extends Controller {

    @FXML private LineChart<Double, Double> lineGraph;
    @FXML private ScatterChart<Double,Double> scatterGraph;
    @FXML private StackPane plotStackPane;
    private final XYChart.Series<Double,Double> lineSeries;
    private final XYChart.Series<Double,Double> scatterSeries;
    private boolean onScatter = false;
    public ChartController(){
        this.stage = new Stage();
        this.lineSeries = new XYChart.Series<Double, Double>();
        this.scatterSeries = new XYChart.Series<Double, Double>();
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
        lineGraph.setTitle("Stock Monitoring, 2010");
        lineSeries.setName("My portfolio");
        //populating the series with data
        double range;
        //lineGraph2.getData().add(series);
    }
    public void renderData(Complex z){
        renderData(z.getReal(),z.getImag());
    }
    public void renderData(double x, double y){
        if(onScatter){
            this.scatterSeries.getData().add(new XYChart.Data<Double,Double>(x,y));
        }
        else {

            this.lineSeries.getData().add(new XYChart.Data<Double,Double>(x,y));
        }

    }

    public void renderData(ArrayList<Complex> complexArrayList){
        complexArrayList.forEach(this::renderData);
    }
    public void renderData(ArrayList<Complex> listComplex, double maxModule){
        listComplex.forEach(z->{z.scaleAndModify(1/maxModule);});
        renderData(listComplex);
    }
    public void renderData(ArrayList<Complex> complexArrayList,double maxKey,Double maxValue){
        System.out.println(complexArrayList);
        for (Complex z: complexArrayList){
            renderData(z.getReal()/maxKey,z.getImag()/maxValue);
        }
        render();
    }
    public void decorateChart(){}
    public void scatterMode(){
        this.onScatter = true;
    }
    public void lineMode(){
        this.onScatter=false;
    }
    private void render(){
        this.lineGraph.getData().add(this.lineSeries);
        this.scatterGraph.getData().add(this.scatterSeries);
    }
}
