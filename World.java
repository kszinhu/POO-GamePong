import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class World extends JPanel implements ActionListener {

  private Player player;
  private Rene rene;
  private List<Enemy> enemy;
  private Timer timer;
  private boolean inGame;

  private Image background = new ImageIcon("res\\images\\background.png").getImage();

  public World() {
    setFocusable(true);
    setDoubleBuffered(true);

    player = new Player();
    rene = new Rene();

    addKeyListener(new keyboardAdapter());

    timer = new Timer(5, this);
    timer.start();

    initEnemies();
    inGame = true;
  }

  public void initEnemies() {
    int coordinate[] = new int[80];
    enemy = new ArrayList<Enemy>();

    for (int i = 0; i < coordinate.length; i++) {
      int x = (int) (Math.random() * 8000 + 1024);
      int y = (int) (Math.random() * 976 + 48);
      enemy.add(new Enemy(x, y));
    }
  }

  public void paint(Graphics g) {
    Graphics2D graph = (Graphics2D) g;
    String points = Integer.toString(player.getScore());
    String text = "Pontuação";
    if (inGame) {
      graph.drawImage(background, 0, 0, null);
      graph.drawImage(player.getImage(), player.getX(), player.getY(), this);
      graph.drawImage(rene.getImage(), rene.getX(), rene.getY(), this);
      graph.setColor(new Color(255, 255, 255));
      graph.setFont(new Font(points, 0, 24));
      graph.setFont(new Font(text, 0, 32));
      graph.drawString(text, 5, 29);
      graph.drawString(points, 20, 66);

      List<Shooting> shoots = player.getShoot();

      for (int i = 0; i < shoots.size(); i++) {
        Shooting fire = shoots.get(i);
        fire.load();
        graph.drawImage(fire.getImage(player.getId()), fire.getX(), fire.getY(), this);
      }

      for (int i = 0; i < enemy.size(); i++) {
        Enemy duke = enemy.get(i);
        duke.load();
        graph.drawImage(duke.getImage(), duke.getX(), duke.getY(), this);
      }

    } else {
      // END GAME
      System.out.println("END GAME");
      System.exit(0);
    }
    g.dispose();
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

    for (int i = 0; i < enemy.size(); i++) {
      Enemy duke = enemy.get(i);
      if (duke.isVisible()) {
        duke.update();
      } else {
        enemy.remove(i);
        (new Sound()).playSound(1);
        player.incScore();
      }
    }
    colisions();
    repaint();
  }

  public void colisions() {
    Rectangle playerBounds = player.getBounds();
    Rectangle reneBounds = rene.getBounds();
    Rectangle shootBounds;
    Rectangle dukeBounds;

    if (playerBounds.intersects(reneBounds)) {
      player.setVisible(false);
      inGame = false;
    }

    for (int i = 0; i < enemy.size(); i++) {
      Enemy duke = enemy.get(i);
      dukeBounds = duke.getBounds();
      if (playerBounds.intersects(dukeBounds)) {
        player.setVisible(false);
        duke.setVisible(false);
        inGame = false;
      }
    }

    List<Shooting> shoots = player.getShoot();

    for (int i = 0; i < shoots.size(); i++) {
      Shooting fire = shoots.get(i);
      shootBounds = fire.getBounds();
      for (int j = 0; j < enemy.size(); j++) {
        Enemy duke = enemy.get(j);
        dukeBounds = duke.getBounds();
        if (shootBounds.intersects(dukeBounds)) {
          duke.setVisible(false);
          fire.setVisible(false);
        }
      }
    }
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
