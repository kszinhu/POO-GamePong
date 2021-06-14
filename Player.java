import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Player extends JPanel {

  private int x, y;
  private int dx, dy;
  private Image Girl[] = new Image[3];
  // private Image Boy[] = new Image[3];
  private List<Shooting> shoots;
  private int height, width;
  private boolean isVisible;

  int moveStatus = 0;

  public Player() {
    shoots = new ArrayList<Shooting>();
    try {
      Girl[0] = ImageIO.read(new File("players\\Girl-1.png"));
      Girl[1] = ImageIO.read(new File("players\\Girl-2.png"));
      Girl[2] = ImageIO.read(new File("players\\Girl-3.png"));
      height = Girl[0].getHeight(this);
      width = Girl[0].getWidth(this);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(new JFrame(), "The image cannot be loaded!\n" + e, "Error",
          JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
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

  public Image getImage(Graphics g) {
    return Girl[moveStatus];
  }

}
