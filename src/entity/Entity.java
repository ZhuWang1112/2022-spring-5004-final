package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Entity {

    public int x, y;
    public int speed;
    // describe an Image
    public BufferedImage image, image2;
    public String direction;

    //animation
    public int spriteCounter;
    public int spriteNum = 1;
    Random r;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collision = false;

    GamePanel gp;
    KeyHandler keyH;

    public Entity (GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        r = new Random();
        setDefaultValue();
        getImage();
    }
    public abstract void setDefaultValue();
    public abstract void getImage();
    public abstract void update();
    public abstract void draw(Graphics2D g2);


}