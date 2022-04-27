package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tank extends Entity{

//    GamePanel gp;
//    KeyHandler keyH;

    public Tank(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);
//        this.gp = gp;
//        this.keyH = keyH;
//        setDefaultValue();
//        getImage();
    }
    public void setDefaultValue() {

        x = 0;
        y = gp.screenHeight - gp.tileSize - 100;
        speed = 4;
    }
    public  void getImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tank/tank1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/tank/tank2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.leftPressed | keyH.rightPressed) {
            if (keyH.leftPressed) {
                x -= speed;
            } else {
                x += speed;
            }

            // animation
            spriteCounter++;
            if (spriteCounter > 2) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }

    }
    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);d
//        // draw a rectangle as player
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage show;
        if (spriteCounter == 1) {
            show = image;
        } else {
            show = image2;
        }
        g2.drawImage(show, x, y, gp.tileSize, gp.tileSize, null);
        System.out.println(x);
    }

}

