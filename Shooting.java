import java.awt.Image;
import java.io.File;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Shooting {

  private Image image;
  private int x, y;
  private int height, width;
  private boolean isVisible;

  private static final int WIDTH = 1024;
  private static int speed = 10;

  public Shooting(int x, int y) {

    this.x = x;
    this.y = y;
    isVisible = true;

  }

  public void load() {
    try {
      image = ImageIO.read(new File("players\\shootGirl.png"));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(new JFrame(), "The image cannot be loaded!\n" + e, "Error",
          JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
    height = image.getHeight(null);
    width = image.getWidth(null);

  }

  public void update() {
    this.x += speed;
    if (this.x > WIDTH)
      isVisible = false;
  }

  public Rectangle getBounds() {
    return new Rectangle(x, y, width, height);
  }

  public boolean isVisible() {
    return isVisible;
  }

  public void setVisible(boolean ifVisible) {
    this.isVisible = ifVisible;
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