package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    // Fields left public for direct access are to reduce overhead
    public String name;
    public int x,y;
    public int speed;
    public String direction;
    BufferedImage stationary, right1, right2, right3, right4;
    int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle solidArea;
    public boolean collisionOn = false;

    public int spriteCounter = 0;
    public int spriteNum = 1;


    public BufferedImage getStationary() {
        return stationary;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public BufferedImage getRight3() {
        return right3;
    }

    public BufferedImage getRight4() {
        return right4;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

}
