import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    public Sound() {}

    public void playSound(String sound_name) {
        String file_name = "src/Sounds/" + sound_name + ".wav"; // Ensure the file is in the project directory
        try {
            // Load the audio file
            File audioFile = new File(file_name);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get a Clip object
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Add a listener to detect when the clip is done
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            // Play the sound
            clip.start();



        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
