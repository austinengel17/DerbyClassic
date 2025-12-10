package entity;

import state.EntityState;
import state.HorseStationary;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Horse extends Entity {
    // Fields left public for direct access are to reduce overhead
    GamePanel gp;
    public EntityState entityState;
    private double finishTime;
    private final String IMAGE_PATH = "/%s/%s";
    private final String STATIONARY_IMAGE = "stationary_32_32.png";
    private final String RIGHT1_IMAGE = "right1_48_48.png";
    private final String RIGHT2_IMAGE = "right2_48_48.png";
    private final String RIGHT3_IMAGE = "right3_48_48.png";
    private final String RIGHT4_IMAGE = "right4_48_48.png";

    public Horse(GamePanel gp, String name) {
        this.gp = gp;
        this.name = name;
        entityState = new HorseStationary(this, gp);
        solidArea = new Rectangle(14, 20, 21, 10); // x14:y20 -> x14:y30 vertical = 10 height | x14:y20 -> x35:y20 -> 21 width
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        finishTime = 0.00;
        getHorseImage();
    }

    public void getHorseImage(){
        // Replace space with underline for folder name
        String folderName = name.replace(' ', '_');
        System.out.println(folderName);
        try {
            stationary = ImageIO.read(getClass().getResourceAsStream(String.format(IMAGE_PATH, folderName, STATIONARY_IMAGE)));
            right1  = ImageIO.read(getClass().getResourceAsStream(String.format(IMAGE_PATH, folderName, RIGHT1_IMAGE)));
            right2 = ImageIO.read(getClass().getResourceAsStream(String.format(IMAGE_PATH, folderName, RIGHT2_IMAGE)));
            right3 = ImageIO.read(getClass().getResourceAsStream(String.format(IMAGE_PATH, folderName, RIGHT3_IMAGE)));
            right4 = ImageIO.read(getClass().getResourceAsStream(String.format(IMAGE_PATH, folderName, RIGHT4_IMAGE)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        entityState.update();
    }

    public void registerPlacement(int index) {
        if(index != 999 && gp.finishCounter < gp.winnerPlacement.length) {

            // ADD FINISH TIME
            finishTime = gp.raceTimer.getTimeInSeconds();
            System.out.println("finish time is : " + finishTime);

            // ADD HORSES TO FINISH LIST
            gp.item[index].collision = false;
            System.out.println((gp.finishCounter + 1) + "st Place is: " + name + "!");
            gp.winnerPlacement[gp.finishCounter] = this;
            gp.finishCounter++;

        }
    }

    public void draw(Graphics2D g) {
        entityState.render(g);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getFinishTime() {
        return finishTime;
    }
}
