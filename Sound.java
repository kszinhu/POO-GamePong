import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

  private AudioInputStream audioInputStream;

  public synchronized void playSound(String path) {
    try {
      audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(path));
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
