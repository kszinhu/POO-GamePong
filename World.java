import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class World extends JPanel implements ActionListener {

  private Image wallpaper;
  private Player player;
  private Rene rene;
  private Timer timer;
  private boolean inGame;

  public World() {
    setFocusable(true);
    setDoubleBuffered(true);

    player = new Player();
    rene = new Rene();
    inGame = true;

    addKeyListener(new keyboardAdapter());

    timer = new Timer(5, this);
    timer.start();
  }

  public void paint(Graphics g) {
    Graphics2D graph = (Graphics2D) g;
    if (inGame) {
      try {
        graph.drawImage(ImageIO.read(new File("background.png")), 0, 0, null);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(new JFrame(), "The image cannot be loaded!\n" + e, "Error",
            JOptionPane.ERROR_MESSAGE);
        System.exit(1);
      }
      graph.drawImage(player.getImage(g), player.getX(), player.getY(), this);
      graph.drawImage(rene.getImage(g), rene.getX(), rene.getY(), this);
      
      List<Shooting> shoots = player.getShoot();
      
      for (int i = 0; i < shoots.size(); i++) {
        Shooting fire = shoots.get(i);
        fire.load();
        graph.drawImage(fire.getImage(), fire.getX(), fire.getY(), this);
      }
    } else {
      try {
        graph.drawImage(ImageIO.read(new File("player.png")), 0, 0, null); // End game
        System.out.println("END GAME");
      } catch (IOException e) {
        JOptionPane.showMessageDialog(new JFrame(), "The image cannot be loaded!\n" + e, "Error",
            JOptionPane.ERROR_MESSAGE);
        System.exit(1);
      }
    }
    colisions();
    g.dispose();
  }

  public void colisions() {
    Rectangle playerBounds = player.getBounds();
    Rectangle reneBounds = rene.getBounds();
    Rectangle shootBounds;

    if (playerBounds.intersects(reneBounds)) {
      player.setVisible(false);
      inGame = false;
    }

    List<Shooting> shoots = player.getShoot();
    for (int i = 0; i < shoots.size(); i++) {
      Shooting fire = shoots.get(i);
      shootBounds = fire.getBounds();
      if (shootBounds.intersects(reneBounds)) {
        rene.setVisible(false);
        fire.setVisible(false);
      }
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    player.update();

    List<Shooting> shoots = player.getShoot();
    for (int i = 0; i < shoots.size(); i++) {
      Shooting fire = shoots.get(i);
      if (fire.isVisible()) {
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
