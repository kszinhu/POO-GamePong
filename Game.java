import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends JFrame {
  JFrame frame;
  boolean isRunning;

  public static final int SCALE = 2;

  public Game() {
    add(new World());
    setTitle("Space inPOO");
    setSize(1024, 1024);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    this.setResizable(false);
    setIconImage(new ImageIcon("icon.png").getImage());

    setVisible(true);
  }
  public static void main(String[] args) {
    new Game();
  }

}