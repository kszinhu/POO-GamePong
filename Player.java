import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {

  private int x, y;
  private int dx, dy;
  private Image image;
  private int height, width;

  public Player() {
    this.x = 100;
    this.y = 100;
  }

  public void load() {
    ImageIcon referrence = new ImageIcon("res\\images\\player.png");
    image = referrence.getImage();

    height = image.getHeight(null);
    width = image.getWidth(null);
  }

  public void update() {
    x += dx;
    y += dy;
  }

  public void keyPressed(KeyEvent key) {
    int code = key.getKeyCode();

    if (code == KeyEvent.VK_UP) {
      dy = -3;
    }

    if (code == KeyEvent.VK_DOWN) {
      dy = 3;
    }

    if (code == KeyEvent.VK_LEFT) {
      dx = -3;
    }

    if (code == KeyEvent.VK_RIGHT) {
      dx = 3;
    }

  }

  public void keyRealeased(KeyEvent key) {
    int code = key.getKeyCode();

    if (code == KeyEvent.VK_UP) {
      dy = 0;
    }

    if (code == KeyEvent.VK_DOWN) {
      dy = 0;
    }

    if (code == KeyEvent.VK_LEFT) {
      dx = 0;
    }

    if (code == KeyEvent.VK_RIGHT) {
      dx = 0;
    }

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
