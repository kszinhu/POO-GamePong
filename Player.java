import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Player extends JPanel {

  private int x, y;
  private int dx, dy;
  private Image Girl[] = new Image[3];
  {
    Girl[0] = new ImageIcon("players\\Girl-1.png").getImage();
    Girl[1] = new ImageIcon("players\\Girl-2.png").getImage();
    Girl[2] = new ImageIcon("players\\Girl-3.png").getImage();
    height = Girl[0].getHeight(this);
    width = Girl[0].getWidth(this);
  }
  // private Image Boy[] = new Image[3];
  private List<Shooting> shoots;
  private int height, width;
  private boolean isVisible;

  int moveStatus = 0;

  public Player() {
    shoots = new ArrayList<Shooting>();

    this.x = 5;
    this.y = 512 - height / 2;
    isVisible = true;
  }

  public void update() {
    x += dx;
    y += dy;
  }

  public void shoot() {
    this.shoots.add(new Shooting(x + (width / 2), y + (height / 2) - 3));
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

    if (code == KeyEvent.VK_Z) {
      moveStatus = 2;
      shoot();
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

    if (code == KeyEvent.VK_Z) {
      moveStatus = 0;
    }

  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public List<Shooting> getShoot() {
    return shoots;
  }

  public Image getImage() {
    return Girl[moveStatus];
  }

}
