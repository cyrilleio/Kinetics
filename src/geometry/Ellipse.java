package geometry;

import javafx.scene.paint.Color;
import java.text.DecimalFormat;

public class Ellipse extends CircleBase {

    private double perimeter;
    private final static int dim = 1 ;
    static boolean onEvent = false;
    private double strokeWidth, radius, centerX, centerY;
    DecimalFormat df = new DecimalFormat("###.###");
    public Ellipse(double centerX, double centerY, double radiusX, double radiusY){
        super(centerX,centerY,radiusX,radiusY);
    }

    @Override
    public int dim() {
        return 0;
    }

    @Override
    public float[] points() {
        return new float[0];
    }


    @Override
    public void translate(int dh) {

    }

    @Override
    public Ellipse duplicate(double dh) {
        Ellipse ellipse = new Ellipse(this.getCenterX(), this.getCenterY(), this.getRadiusX() -dh,this.getRadiusY() -dh);
        ellipse.setCenterX(this.getCenterX());
        ellipse.setCenterY(this.getCenterY());
        return ellipse;
    }
}

