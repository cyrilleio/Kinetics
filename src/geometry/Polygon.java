package geometry;


public interface Polygon {
    int dim();
    float[] points();
    void translate(int dh);
    Object duplicate(double dh);
}