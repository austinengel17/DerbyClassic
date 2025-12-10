package state;

import entity.Horse;
import main.GamePanel;
import object.Item;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayState extends GameState {
    int finishFrameCounter;
    int startFrameCounter;
    boolean startRace;

    public PlayState(GamePanel gp) {
        super(gp);
        finishFrameCounter = 0;
        startFrameCounter = 0;
        startRace = false;
        setupHorses();
    }

    @Override
    public void update() {

        // DELAY BEFORE RACE START
        if(startFrameCounter == gp.FPS*2 && startRace == false){
            startRace = true;

            // Remove fence to start race
            gp.tm.removeRetainingBars();

            // Start Horse Sprint
            for(Horse horse : gp.lineup) {
                horse.entityState = new HorseMobile(horse, gp);
            }

            //Start race timer
            gp.raceTimer.start();

        } else if (startRace == false){
            startFrameCounter++;
        }

        // UPDATE HORSES
        for(Horse horse : gp.lineup) {
            horse.update();
        }

        // Check if all horses finished
        if(gp.finishCounter == gp.lineup.length) {
            finishFrameCounter++;
            if(finishFrameCounter == gp.FPS * 2)
                gp.gameState = new FinishState(gp);
        }
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
        int iconHeight = gp.tileSize/3;
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
            if(i >= gp.lineup.length / 2) { // 2nd Half lineup
                g2.drawImage(gp.tm.tile[tileIndex].image, rightColumnX, iconY, iconWidth, iconHeight, null);
                g2.drawString(gp.lineup[i].name, rightColumnX + messageIconSpacing, iconY + iconHeight - 2);
                iconY = iconY + gp.tileSize/2 + 8;
            } else { // 1st Half lineup
                g2.drawImage(gp.tm.tile[tileIndex].image, leftColumnX, iconY, iconWidth, iconHeight, null);
                g2.drawString(gp.lineup[i].name, leftColumnX + messageIconSpacing, iconY + iconHeight - 2);
                iconY = iconY + gp.tileSize/2 + 8;
            }
        }
    }

    @Override
    public void handleKeyEvent(int code) {
        switch(code) {
            case KeyEvent.VK_P:
                gp.gameState = new PauseState(gp,this);
                gp.raceTimer.stop();
                break;
        }
    }

    private void setupHorses() {
        for(int i = 0; i < gp.lineup.length; i++){
            gp.lineup[i].setPosition(gp.tileSize, gp.tileSize * (3 + i));
        }
    }
}
