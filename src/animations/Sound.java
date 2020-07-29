package animations;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private static Thread AUDIO_STREAM;
    private Clip clip;
    public Sound(){
        loadResource();
    }

    public void play(){
        //clip.start();
    }
    public void stop(){
        clip.stop();
    }
    public synchronized void loadResource(){
        AUDIO_STREAM = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            this.getClass().getResourceAsStream("../resources/audio/beep.wav/"));
                    clip.open(inputStream);
                    clip.loop(10);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        });
        AUDIO_STREAM.start();
    }
}