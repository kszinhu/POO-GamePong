import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

  private AudioInputStream audioInputStream[] = new AudioInputStream[3];
  {
    try {
      audioInputStream[1] = AudioSystem.getAudioInputStream(this.getClass().getResource("res\\sounds\\coins.wav"));
      audioInputStream[2] = AudioSystem.getAudioInputStream(this.getClass().getResource("res\\sounds\\laser.wav"));
    } catch (UnsupportedAudioFileException e) {
      System.out.println("Arquivo não suportado");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("Erro na Entrada");
      e.printStackTrace();
    }
  }

  public synchronized void playSound(int path) {
    try {
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream[path]);
      clip.start();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
