import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.util.List;

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

    ImageIcon referrence = new ImageIcon("background.png");
    wallpaper = referrence.getImage();

    player = new Player();

    addKeyListener(new keyboardAdapter());

    timer = new Timer(5, this);
    timer.start();
  }

  public void paint(Graphics g) {
    Graphics2D graph = (Graphics2D) g;
    graph.drawImage(wallpaper, 0, 0, null);
    graph.drawImage(player.getImage(g), player.getX(), player.getY(), this);
    List<Shooting> shoots = player.getShoot();
    for (int i = 0; i < shoots.size(); i++) {
      Shooting fire = shoots.get(i);
      fire.load();
      graph.drawImage(fire.getImage(), fire.getX(), fire.getY(), this);
    }

    g.dispose();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    player.update();

    List<Shooting> shoots = player.getShoot();
    for (int i = 0; i < shoots.size(); i++) {
      Shooting fire = shoots.get(i);
      if (fire.ifVisible()) {
        fire.update();
      } else {
        shoots.remove(i);
      }
    }

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
