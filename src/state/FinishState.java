package state;

import entity.Horse;
import main.GamePanel;
import java.awt.*;
import java.awt.event.KeyEvent;


public class FinishState extends GameState {

    private final String PLACE_MESSAGE_KEY = "%s.place";
    private final String PLACEMENT_MESSAGE_FORMAT = "%s - %s - %ss";

    public FinishState(GamePanel gp) {
        super(gp);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2) {
        int x, y;
        String text;
        Horse winner = gp.winnerPlacement[0];
        g2.setColor(new Color(31, 119, 30));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        // CHECK FOR DRAW
        if(winner.getFinishTime() == gp.winnerPlacement[1].getFinishTime()) {
            text = "Draw!";
        } else {
            text = winner.name + " Wins!";
        }
        g2.setFont(g2.getFont().deriveFont(Font.TYPE1_FONT, 32F));
        x = gp.getXforCenteredText(text, g2);
        y = gp.tileSize * 4;

        // TITLE SHADOW
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+2, y+2);

        // TITLE MAIN COLOR
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // DRAW PLACEMENTS
        int currentPlace = 1;
        int skipCounter = 0;
        ///TEST/// TODO add junit testing for this
        //Two ties
//        gp.winnerPlacement[2].setFinishTime(gp.winnerPlacement[1].getFinishTime());
//        gp.winnerPlacement[5].setFinishTime(gp.winnerPlacement[4].getFinishTime());
        // Tie with 1st, 2nd
//        gp.winnerPlacement[1].setFinishTime(gp.winnerPlacement[0].getFinishTime());
        // Tie with 1st, 2nd, 3rd
//        gp.winnerPlacement[1].setFinishTime(gp.winnerPlacement[0].getFinishTime());
//        gp.winnerPlacement[2].setFinishTime(gp.winnerPlacement[1].getFinishTime());
        // Three way tie at end
//        gp.winnerPlacement[4].setFinishTime(gp.winnerPlacement[3].getFinishTime());
//        gp.winnerPlacement[5].setFinishTime(gp.winnerPlacement[4].getFinishTime());
        // Three way tie in middle
//        gp.winnerPlacement[3].setFinishTime(gp.winnerPlacement[2].getFinishTime());
//        gp.winnerPlacement[4].setFinishTime(gp.winnerPlacement[3].getFinishTime());
        ///TEST///
        for(int i = 0; i < gp.winnerPlacement.length; i++) {
            Horse horse = gp.winnerPlacement[i];
            String name = horse.name;
            String finishTime = String.format("%.2f", horse.getFinishTime());
            String placeMessage = messageUtil.getMessage(String.format(PLACE_MESSAGE_KEY, currentPlace));
            text = String.format(PLACEMENT_MESSAGE_FORMAT, placeMessage, name, finishTime);
            // Determine next placement (Accounting for ties)
            if(i < gp.winnerPlacement.length-1 && gp.winnerPlacement[i].getFinishTime() == gp.winnerPlacement[i+1].getFinishTime()) {
                skipCounter++;
            } else {
                currentPlace = currentPlace + 1 + skipCounter;
                skipCounter = 0;
            }
            g2.setFont(g2.getFont().deriveFont(Font.TYPE1_FONT, 16F));
            x = gp.getXforCenteredText(text, g2);
            y = gp.tileSize * 5 + gp.tileSize/2 * i;
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
        }

        // DRAW MESSSAGE play.again
        text = messageUtil.getMessage("return.title.screen");
        g2.setFont(g2.getFont().deriveFont(Font.TYPE1_FONT, 20F));
        g2.setColor(Color.WHITE);
        x = gp.getXforCenteredText(text, g2);
        y = gp.tileSize * 7 + gp.tileSize/2 * gp.winnerPlacement.length;
        g2.drawString(text, x, y);


    }

    @Override
    public void handleKeyEvent(int code) {
        switch(code) {
            case KeyEvent.VK_SPACE:
                gp.setupGame();
                gp.winnerPlacement = new Horse[6];
                gp.gameState = new TitleState(gp);
                break;
        }
    }

}
