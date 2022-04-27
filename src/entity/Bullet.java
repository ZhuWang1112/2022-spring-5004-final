package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullet extends Entity {
//    GamePanel gp;
//    KeyHandler keyH;
    double x;
    double y;
    double speedX;
    double speedY;
    int number;


    public Bullet(GamePanel gp, KeyHandler keyH, int number) {
        super(gp,keyH);
//        this.gp = gp;
//        this.keyH = keyH;
//        setDefaultValue();
//        getImage();
        this.number = number;
    }

    public void setDefaultValue() {

        x = gp.tank.x;
        y = gp.tank.y;
        speedX = Math.cos(Math.toRadians(gp.barrel.angle)) * gp.barrel.power;
        speedY = Math.sin(Math.toRadians(gp.barrel.angle)) * gp.barrel.power;
    }

    @Override
    public void getImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tank/ball.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        x += speedX;
        y -= speedY;
        speedY -= 0.5;
        if (y > gp.screenHeight) {
            gp.bullets[number] = null;
        }
    }
    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);d
//        // draw a rectangle as player
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        g2.drawImage(image, (int)x, (int)y, gp.tileSize / 3, gp.tileSize / 3, null);
        System.out.println(x);
    }
}
