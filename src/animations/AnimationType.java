package animations;

import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public interface AnimationType {
    public void play();
    public void pause();
    public void setTimeline();
    public void playFrom(Duration time);
    public void playFromStart();
    public void stop();
    public void onFinished (EventHandler<ActionEvent> event);
}
