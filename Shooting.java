import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Shooting {

  private Image image;
  private int x, y;
  private int height, width;
  private boolean ifVisible;

  private static final int WIDTH = 1024;
  private static int speed = 2;

  public Shooting(int x, int y) {

    this.x = x;
    this.y = y;
    ifVisible = true;
  }

  public void load() {
    try {
      image = ImageIO.read(new File("players\\shootGirl.png"));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(new JFrame(), "The image cannot be loaded!\n" + e, "Error",
          JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }

  }

  public void update() {
    this.x += speed;
    if (this.x > WIDTH)
      ifVisible = false;
  }

  public boolean ifVisible() {
    return ifVisible;
  }

  public void setVisible(boolean ifVisible) {
    this.ifVisible = ifVisible;
  }

  public static int getSpeed() {
    return speed;
  }

  public static void setSpeed(int newSpeed) {
    speed = newSpeed;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Image getImage() {
    return image;
  }

}