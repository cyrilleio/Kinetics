package shape;

import structures.Complex;

public interface PolygonGroup {
    double getDuration();
    void setDuration(double time);
    Complex getCurrentPosition();
    void play();
    void pause();
    void build();
}
