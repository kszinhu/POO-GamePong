import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Rene extends JPanel {

  private int x, y;
  private int height, width;
  private boolean isVisible;
  private Image Rene[] = new Image[2];
  private Timer timer;

  private static int SPEED = 2;
  int moveStatus = 0;

  public Rene() {
    try {
      Rene[0] = ImageIO.read(new File("enemies\\Rene-none.png"));
      Rene[1] = ImageIO.read(new File("enemies\\Rene-month.png"));
      height = Rene[0].getHeight(this);
      width = Rene[0].getWidth(this);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(new JFrame(), "The image cannot be loaded!\n" + e, "Error",
          JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
    isVisible = true;
    this.x = 885;
    this.y = 512 - height / 2;
  }

  public void update() {
    this.y -= SPEED;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Image getImage(Graphics g) {
    return Rene[moveStatus];
  }

  public void setVisible(boolean isView) {
    this.isVisible = isView;
  }

  public static void setSpeed(int Speed) {
    SPEED = Speed;
  }

}
