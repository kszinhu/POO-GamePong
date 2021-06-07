import javax.swing.JFrame;

public class Game extends JFrame {
  JFrame frame;
  boolean isRunning;

  public static final int SCALE = 2;

  public Game() {
    add(new World());
    setTitle("SMB");
    setSize(1024, 768);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    this.setResizable(false);
    setVisible(true);
  }

  public static void main(String[] args) {
    new Game();
  }

}