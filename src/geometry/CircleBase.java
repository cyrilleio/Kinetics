package geometry;

import javafx.scene.Node;
import structures.Complex;
import structures.Constant;
import java.util.List;

public class CircleBase extends javafx.scene.shape.Ellipse implements Polygon {
    protected final double radiusX, radiusY;
    protected Point center;
    protected final double centerX, centerY;
    public CircleBase(double centerX, double centerY, double radius){
        super(centerX,centerY,radius,radius);
        this.radiusX = radius;
        this.radiusY = radius;
        this.centerX = centerX;
        this.centerY = centerY;
        this.center = new Point(centerX,centerY);
        this.setSmooth(true);
        buildCircle();
    }
    public CircleBase(double centerX, double centerY, double radiusX, double radiusY){
        super(centerX,centerY,radiusX,radiusY);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.centerY = centerY;
        this.centerX = centerX;
        buildCircle();
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
    public CircleBase duplicate (double dh){
        CircleBase ellipse = new CircleBase(this.getCenterX(), this.getCenterY(), this.getRadiusX() -dh, this.getRadiusY()-dh);
        ellipse.setCenterX(this.getCenterX());
        ellipse.setCenterY(this.getCenterY());
        return ellipse;
    }
    private void buildCircle(){
        this.setStrokeWidth(Constant.SHAPE_STROKE_WIDTH);
        this.setFill(Constant.SHAPE_FILL);
        this.setStroke(Constant.SHAPE_STROKE);
        this.center = new Point(this.centerX, this.centerY);
    }
    public Point getCenter(){
        return this.center;
    }
    public final void setCenter(Complex z){
        this.setCenterX(z.getReal());
        this.setCenterY(z.getImag());
    }
    public final void setCenter(Point pos){
        this.setCenterX(pos.getX());
        this.setCenterY(pos.getY());
    }
}
