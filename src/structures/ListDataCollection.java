package structures;


import controllers.ChartController;

import java.io.*;
import java.util.ArrayList;

public class ListDataCollection extends ArrayList<DataCollection> {
    private Writer fileWriter;
    public ListDataCollection(){
        fileWriter = null;
    }
    public void plotData(){
        for (int i=0;2*i <this.size();++i){
            ChartController  chartController = new ChartController();
            chartController.scatterMode();
            chartController.renderData(this.get(2*i).getXYCoordinates(),Constant.PIXEL_PER_CM,Constant.PIXEL_PER_CM);
            chartController.showStage();
        }
    }
    public void plotUData(){

    }
    public void addData(DataCollection data, DataCollection uData){
        this.add(data);
        this.add(uData);
    }
    public ArrayList<DataCollection>  retrieveDataForType(int dataType){
        ArrayList<DataCollection> typeArrayList = new ArrayList<>();
        for (int i=0;2*i<this.size();++i){
            if (this.get(i).DATA_TYPE == dataType){
                typeArrayList.add(this.get(2*i));
                typeArrayList.add(this.get(2*i +1));
            }
        }
        return typeArrayList;
    }
    public void exportToCSV(String dir) throws IOException {
        try {
            File file = new File("data.csv");
            fileWriter = new BufferedWriter(new FileWriter(file));
            for (DataCollection dataCollection:this) {
                String text =  dataCollection + "\n";
                fileWriter.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            fileWriter.flush();
            fileWriter.close();
        }
    }
    public String metadata(){
        return "metadata";
    }
}