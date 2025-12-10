package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {
    // Fields left public for direct access are to reduce overhead
    BufferedImage image;
    String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea;
    int solidAreaDefaultX, solidAreaDefaultY;

    public void draw(Graphics2D g, GamePanel gp) {
        g.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }

    // GETTERS AND SETTERS
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

}
