package state;

import entity.Horse;
import main.GamePanel;
import object.Item;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseState extends GameState{
    PlayState state;
    public PauseState(GamePanel gp, PlayState state) {
        super(gp);
        this.state = state;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2) {
        // TILES
        gp.tm.draw(g2);

        for(Item item : gp.item) {
            if(item != null)
                item.draw(g2, gp);
        }

        // HORSES
        for(Horse horse : gp.lineup) {
            horse.draw(g2);
        }

        // LINEUP MAPPING
        String leftLongestName, rightLongestName;
        leftLongestName = rightLongestName = "";
        for(int i = 0; i < gp.lineup.length; i++) {
            if(i < gp.lineup.length/2) leftLongestName = gp.lineup[i].name.length() > leftLongestName.length() ? gp.lineup[i].name : leftLongestName;
            else rightLongestName = gp.lineup[i].name.length() > rightLongestName.length() ? gp.lineup[i].name : rightLongestName;
        }
        int messageIconSpacing = gp.tileSize/2;
        int iconWidth = gp.tileSize/3;
        int columnSpacing = gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.TYPE1_FONT, 14F));
        int leftLeaderboardWidth = messageUtil.getMessageLength(leftLongestName, g2) + messageIconSpacing + iconWidth;
        int rightLeaderboardWidth = messageUtil.getMessageLength(rightLongestName, g2) + messageIconSpacing + iconWidth;
        int leaderboardWidth = leftLeaderboardWidth + columnSpacing + rightLeaderboardWidth;
        int centerX = gp.screenWidth/2;
        int leftColumnX = centerX - leaderboardWidth/2;
        int rightColumnX = leftColumnX + rightLeaderboardWidth + columnSpacing;
        int iconY = 10 * gp.tileSize - gp.tileSize/2;
        for(int i = 0, tileIndex = 3; i < gp.lineup.length && tileIndex < gp.tm.tile.length; i++, tileIndex++ ) {
            if(i == gp.lineup.length/2) iconY = 10 * gp.tileSize - gp.tileSize/2;
            if(i >= gp.lineup.length / 2) { // Half lineup
                g2.drawImage(gp.tm.tile[tileIndex].image, rightColumnX, iconY, iconWidth, gp.tileSize/3, null);
                g2.drawString(gp.lineup[i].name, rightColumnX + messageIconSpacing, iconY + 14);
                iconY = iconY + gp.tileSize/2 + 8;
            } else {
                g2.drawImage(gp.tm.tile[tileIndex].image, leftColumnX, iconY, iconWidth, gp.tileSize/3, null);
                g2.drawString(gp.lineup[i].name, leftColumnX + messageIconSpacing, iconY + 14);
                iconY = iconY + gp.tileSize/2 + 8;
            }
        }
//        state.render(g2);
    }

    @Override
    public void handleKeyEvent(int code) {
        switch(code) {
            case KeyEvent.VK_P:
                gp.gameState = state;
                gp.raceTimer.start();
                break;
        }
    }
}
