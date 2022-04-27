package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Target extends Entity{

//    GamePanel gp;
//    KeyHandler keyH;
//    Random r = new Random();
    Boolean collision = false;
    int targetInterval;

    public Target(GamePanel gp, KeyHandler keyH) {
        super(gp,keyH);
//        this.gp = gp;
//        this.keyH = keyH;
//        setDefaultValue();
//        getImage();
    }
    public void setDefaultValue() {
        x = r.nextInt(gp.screenWidth / 2) + gp .screenWidth / 2 - gp.tileSize;
        y = gp.screenHeight - gp.tileSize - 100;
    }
    public  void getImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tank/tank1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        for (int i = 0; i < gp.bullets.length; i++ ) {
            if (gp.bullets[i] != null) {
                if (gp.bullets[i].x  < x + gp.tileSize && gp.bullets[i].x > x - gp.tileSize && gp.bullets[i].y  < y + gp.tileSize && gp.bullets[i].y > y - gp.tileSize) {
                    collision = true;
                    targetInterval = 60;
                    break;
                }
            }
        }
        // update new target
        if (targetInterval == 1) {
            x = r.nextInt(gp.screenWidth / 2) + gp .screenWidth / 2 - gp.tileSize;
        }
    }
    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);d
//        // draw a rectangle as player
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        if (!collision && targetInterval == 0) {
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        } else {
            g2.drawString("Boob!", x, y - 20);
            targetInterval--;
            collision = false;
        }
    }

}

