package object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class FinishLine extends Item {
    private static final String IMAGE_PATH = "/objects/finish_line.png";

    public FinishLine() {
        name = "Finish Line";
        try {
            image = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        solidArea = new Rectangle(32, 0, 4, 48); // Hit box is only the line
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision = true;
    }
}
