import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Enemy {

  private Image image;
  private Image Rele[] = new Image[2];
  private int x, y;
  private int height, width;
  private boolean ifVisible;

  private static final int WIDTH = 1024;
  private static int speed = 2;

  public Enemy(int x, int y) {

    this.x = x;
    this.y = y;
    ifVisible = true;
  }

  public void load() {
    try {
      Rele[0] = ImageIO.read(new File("Head-Rene.png"));
      Rele[1] = ImageIO.read(new File("pMonth-Rene.png"));
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

  public Rectangle getBounds() {
    return new Rectangle(x, y, width, height);
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