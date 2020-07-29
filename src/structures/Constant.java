package structures;

import geometry.Point;
import javafx.scene.paint.Color;
import java.awt.Toolkit;

public abstract class Constant {
    public static final int SHAPE_STROKE_WIDTH = 3;
    public static final Color SHAPE_FILL = null;
    public static final Color CURSOR_FILL = Color.web("af0000");
    public static final int CURSOR_STROKE_WIDTH = 4;
    public static final Color CURSOR_STROKE = Color.web("df0000");
    private static final double INCH_PER_CM = 0.393701;
    public static final double PIXEL_PER_CM = INCH_PER_CM * Toolkit.getDefaultToolkit().getScreenResolution();
    public static final int TRIANGLE_DIM = 3;
    public static final int HEXAGON_DIM = 12;
    public static final int OCTAGON_DIM = 16;
    public static final int RECTANGLE_DIM = 4;
    public static final int CURSOR_RADIUS = 8;
    public static final double FREQUENCY = 60;
    public static final int CIRCLE_DIM = 360;
    public static final Color ARC_STROKE = Color.WHITE;
    public static final int ARC_STROKE_WIDTH = 2*SHAPE_STROKE_WIDTH ;
    public static final Color ARC_FILL = null;
    public static final Color SHAPE_STROKE = Color.web("004369");
    public static final long DELAY_BEFORE_EXPERIMENT = 400;
    public static final Color OBSERVABLE_SHAPE_STROKE = Color.TRANSPARENT;
    public static final Color OBSERVABLE_SHAPE_FILL = null;
    public static final double DELTA_DIM_ANIMATION = 0.0001;
    public static final int TIMES_PER_FRAME = 10;
    public final static Point CENTER = new Point(590,300);
    public static final String[] EXPERIMENT_BY_ID = {"Cercle","Triangle","Ellipse","Rectangle","Hexagone","Octagone"};
    public static final int NOT_CHAINED_IN_SEQUENCE = -1;
    public static final int LAST_IN_SEQUENCE = -2;
    public static final long BETWEEN_EXPERIMENT_TIME = 500; //ms
    public static final int CIRCLE_TYPE =0;
    public static final int TRIANGLE_TYPE =1;
    public static final int ELLIPSE_TYPE =2;
    public static final int RECTANGLE_TYPE = 3;
    public static final int HEXAGON_TYPE = 4;
    public static final int OCTAGON_TYPE = 5;
    public static final String APP_NAME = "Bonnie";
    public static final String ELLIPSE_FORM_REP = "../resources/view/Untitled11.fxml";
    public static final String GENERIC_FORM_REP ="../resources/view/Untitled1.fxml";
    public static final Color OUTER_OBSERVABLE_FILL = Color.TRANSPARENT;
}
