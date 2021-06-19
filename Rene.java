import java.awt.Rectangle;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Rene extends JPanel {

  private int x, y;
  private int height, width;
  private boolean isVisible;
  private Image Rene[] = new Image[2];
  {
    Rene[0] = new ImageIcon("enemies\\Rene-none.png").getImage();
    Rene[1] = new ImageIcon("enemies\\Rene-month.png").getImage();
    height = Rene[0].getHeight(null);
    width = Rene[0].getWidth(null);
  }

  private static int SPEED = 2;
  int moveStatus = 0;

  public Rene() {
    isVisible = true;
    this.x = 885;
    this.y = 512 - height / 2;
  }

  public void update() {
    this.y -= SPEED;
  }

  public boolean isVisible() {
    return isVisible;
  }

  public void setVisible(boolean ifVisible) {
    this.isVisible = ifVisible;
  }

  public Rectangle getBounds() {
    return new Rectangle(x, y, width, height);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Image getImage() {
    return Rene[moveStatus];
  }

  public static void setSpeed(int Speed) {
    SPEED = Speed;
  }

}
