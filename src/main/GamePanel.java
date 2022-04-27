package main;

import entity.Barrel;
import entity.Bullet;
import entity.Tank;
import entity.Target;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // screen setting
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    // horizontally
    public final int maxScreenCol = 16;
    // vertically
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels


    public KeyHandler keyH = new KeyHandler();

    //FPS
    int FPS = 60;

    // keep game running until stop
    Thread gameThread;

    public Tank tank = new Tank(this, keyH);

    // number of bullets
    public int total = 10;

    public Bullet[] bullets = new Bullet[total];

    // Number of bullets used
    public int BulletNumber = 0;

    // fire interval
    public int fireCount = 0;

    public Barrel barrel = new Barrel(this, keyH);

    public Target target = new Target(this,keyH);

    // constructor
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        // help drawing?
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        // focused on receive key input
        this.setFocusable(true);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {                 // thread will automatically call run

        double drawInterval = 1000000000.0 / FPS; // ≈ 0.0167 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        //int drawCount = 0;


        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;


            if (delta >= 1) {
                update();
                repaint();
                delta--;
                //drawCount++;

                if (fireCount > 0) {
                    fireCount--;
                }
            }
            if (timer >= 1000000000) {
                //drawCount = 0;
                timer = 0;
            }
//            System.out.println("The game loop is running! ");

            // 1 update the information such as  character position
            // update();
            // 2 redraw the screen with the updated information
            // repaint();              // call the paintComponent
        }
    }

    public void update() {


        tank.update();

        for (int i = 0; i < bullets.length; i++ ) {
            if (bullets[i] != null) {
                bullets[i].update();
            }
        }
        barrel.update();
        target.update();
    }

    // build-in method in java
    // from JComponet - > JPanel
    // ??
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        // Graphics2D extends the Graphics
        Graphics2D g2 = (Graphics2D) g;


        for (int i = 0; i < bullets.length; i++ ) {
            if (bullets[i] != null) {
                bullets[i].draw(g2);
            }
        }

        tank.draw(g2);
        barrel.draw(g2);
        target.draw(g2);
        g2.dispose();

    }
}
