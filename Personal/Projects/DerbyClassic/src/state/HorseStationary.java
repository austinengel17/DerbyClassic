package state;

import entity.Horse;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HorseStationary extends EntityState {
    public HorseStationary(Horse horse, GamePanel gp) {
        super(gp, horse);
        entity.direction = "stationary";
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2) {
        BufferedImage image = entity.getStationary();
        int x = entity.x;
        int y = entity.y;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    @Override
    public void handleKeyEvent(int code) {

    }
}
