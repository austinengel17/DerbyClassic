package state;

import entity.Horse;
import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class HorseMobile extends EntityState {
    Random random;

    public HorseMobile(Horse horse, GamePanel gp) {
        super(gp, horse);
        random = new Random();
        entity.direction = "right";
        entity.speed = 3;
    }

    @Override
    public void update() {
        /*
        * TODO See if first updated horse always beats last rendered horse in a tie
        * TODO Try to replicate tie where first updated horse ties with last horse and finishes at x.xxx999 second. is this enough for it to beat the last horse which updates later?
        * TODO Would recording/comparing to one hundredth of a second (x.x1s) be accurate enough for close ties?
        */

        // Adjust horse speed by random amount
        if(frameCounter == gp.FPS || frameCounter == gp.FPS/2) {
            frameCounter = 0;
            int num = random.nextInt(3) + 1;
            switch(num) {
                case 2:
                    if(entity.speed < 5) // TODO: adjust according to gp.originalTileSize value
                        entity.speed++;
                    break;
                case 1:
                    if(entity.speed > 2)
                        entity.speed--;
                    break;
                // case 0 does nothing to speed
            }
        }

        // CHECK TILE COLLISION
        entity.collisionOn = false;
        gp.cc.checkTile(entity);
        ((Horse)entity).registerPlacement(gp.cc.checkObject(entity));

        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if(entity.collisionOn == false) {
            switch(entity.direction) {
                case "up":
                    break;
                case "down":
                    break;
                case "left":
                    break;
                case "right":
                    entity.x += entity.speed;
                    frameCounter++;
                    break;
                case "stationary":
                    break;
            }

        }
        entity.spriteCounter++;
        if(entity.spriteCounter > 10) {
            entity.spriteNum++;
            if(entity.spriteNum > 4) entity.spriteNum = 1;
            entity.spriteCounter = 0;
        }
    }

    @Override
    public void render(Graphics2D g2) {
        BufferedImage image = null;

        switch(entity.spriteNum) {
            case 1:
                image = entity.getRight1();
                break;
            case 2:
                image = entity.getRight2();
                break;
            case 3:
                image = entity.getRight3();
                break;
            case 4:
                image = entity.getRight4();
                break;
        }
        g2.drawImage(image, entity.x, entity.y, gp.tileSize, gp.tileSize, null);
    }

    @Override
    public void handleKeyEvent(int code) {

    }
}
