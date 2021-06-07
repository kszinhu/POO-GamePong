import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class World extends JPanel implements ActionListener {

  private Image wallpaper;
  private Player player;
  private Timer timer;

  public World() {
    setFocusable(true);
    setDoubleBuffered(true);

    ImageIcon referrence = new ImageIcon("res\\images\\background.png");
    wallpaper = referrence.getImage();

    player = new Player();
    player.load();

    addKeyListener(new keyboardAdapter());

    timer = new Timer(5, this);
    timer.start();
  }

  public void paint(Graphics g) {
    Graphics2D graph = (Graphics2D) g;
    graph.drawImage(wallpaper, 0, 0, null);
    graph.drawImage(player.getImage(), player.getX(), player.getY(), this);
    g.dispose();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    player.update();
    repaint();
  }

  private class keyboardAdapter extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
      player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
      player.keyRealeased(e);
    }

  }

}
