package animations.timer;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

public class Timer extends java.util.Timer {
    public final Instant timeOrigin = Instant.now();
    public StringProperty stringProperty;
    private TimerTask timerTask;
    public Timer(Label label){
        super(true);
        this.stringProperty = new SimpleStringProperty();
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                Duration duration =Duration.between(timeOrigin,Instant.now());
                String string = String.format("%02d:%02d:%02d",duration.toHoursPart(),duration.toMinutesPart(),duration.toSecondsPart());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        label.setText(string);
                    }
                });

            }
        };
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        this.scheduleAtFixedRate(timerTask,0,1000);
    }
    public Timer(){
        super(true);
    }

    public long getSecondsElapsed(){
        return Duration.between(timeOrigin,Instant.now()).getSeconds();
    }
    public long getNanosElapsed(){
        return Duration.between(timeOrigin,Instant.now()).getNano();
    }
    public Duration getTimeElapsed(){
        return Duration.between(timeOrigin,Instant.now());
    }

}
