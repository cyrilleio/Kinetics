package geometry;

import javafx.scene.shape.Polygon;
import java.util.List;

public class PolygonBase extends Polygon implements geometry.Polygon {
    private List<Point> points;

    public PolygonBase(){
        super();
        this.setSmooth(true);
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
    public Object duplicate(double dh) {
        return null;
    }
    public void rotate(Point center, double theta){
        for (int i=0;i<this.points.size();i++){
            Point p = this.points.get(i);
            this.points.set(i, p.rotate(center,theta));
        }
        build(this.points);
    }
    public void build(List<Point> points){
        this.getPoints().setAll(new Double[]{});
        for (Point p: points){
            this.getPoints().addAll(new Double[]{p.getX(),p.getY()});
        }
        this.points = points;
    }
}