package entity;

import main.GamePanel;
import main.KeyHandler;
import rotate.RotateImageUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Barrel extends Entity {
//    GamePanel gp;
//    KeyHandler keyH;
    int number;
    double angle = 0;
    double power = 15;

    public Barrel(GamePanel gp, KeyHandler keyH) {
        super(gp,keyH);

//        this.gp = gp;
//        this.keyH = keyH;
//        setDefaultValue();
//        getImage();
    }

    public void setDefaultValue() {

        x = gp.tank.x;
        y = gp.tank.y;
    }

    public void getImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tank/barrel3.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.highPressed) {
            power += 0.5;
        }
        if (keyH.lowPressed) {
            power -= 0.5;
        }
        if (keyH.upPressed) {
            angle += 1;
        }
        if (keyH.downPressed) {
            angle -= 1;
        }
        if (keyH.spacePressed && gp.fireCount == 0 && gp.BulletNumber < gp.total) {
            gp.bullets[gp.BulletNumber] = new Bullet(gp,keyH,gp.BulletNumber);
            gp.fireCount = 15;
            gp.BulletNumber++;

        }

        x = gp.tank.x;
        y = gp.tank.y;
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);d
//        // draw a rectangle as player
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        if (gp.BulletNumber == gp.total) {
            g2.drawString("You're out of bullets! ", gp.screenWidth / 2, gp.screenHeight / 2);
        }
        g2.drawString("Angle : " + angle, gp.tank.x + 20, gp.tank.y - 20);
        g2.drawString("Power : " + power, gp.tank.x + 20, gp.tank.y - 5);
        g2.drawString("number : " + gp.BulletNumber, gp.tank.x + 20, gp.tank.y - 35);

        g2.drawImage(RotateImageUtil.rotateImage(image,(int)-angle), x, y, (int)(gp.tileSize * (Math.sin(Math.toRadians(angle)) + Math.cos(Math.toRadians(angle)))), (int)(gp.tileSize * (Math.sin(Math.toRadians(angle)) + Math.cos(Math.toRadians(angle)))), null);
        System.out.println(x);
    }

        public BufferedImage rotateImage (BufferedImage image, int angle)
    {
        BufferedImage bufferedimage = (BufferedImage) image;
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(angle), w / 2, h / 2 + (w - h) / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();

        return img;
    }

}