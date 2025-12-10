package state;

import main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TitleState extends GameState{

    public TitleState(GamePanel gp) {
        super(gp);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2) {
        String text = messageUtil.getMessage("title");
        int x;
        int y;
        int horseImageSize = gp.tileSize * 4;

        // BACKGROUND COLOR
        g2.setColor(new Color(31, 119, 30));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        // TITLE
        g2.setFont(g2.getFont().deriveFont(Font.TYPE1_FONT, 80F));
        x = gp.getXforCenteredText(text, g2);
        y = gp.tileSize * 3;

        // TITLE SHADOW
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);

        // TITLE MAIN COLOR
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // DERBY IMAGE
        x = gp.screenWidth / 2 - horseImageSize / 2;
        y = gp.tileSize * 4;
        g2.drawImage(gp.kryptonite.getRight1(), x, y, horseImageSize, horseImageSize, null);

        // MENU
        text = messageUtil.getMessage("press.space.start");
        g2.setFont(g2.getFont().deriveFont(Font.TYPE1_FONT, 24F));
        x = gp.getXforCenteredText(text, g2);
        y = gp.tileSize * 5 + horseImageSize;
        g2.drawString(text, x, y);
    }

    @Override
    public void handleKeyEvent(int code) {
        switch(code) {
            case KeyEvent.VK_SPACE:
                gp.gameState = new PlayState(gp);
                break;
        }
    }
}
