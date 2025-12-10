package main;

import javax.swing.*;

public class Main {
    static final String TITLE = "Derby Classic";

    public static void main(String args[]) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(TITLE);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}

/*
* TODO List:
*   -Add camera movement to lengthen race?
*   -Resize game window + components to be larger
*   -Home screen
*       -"Call to Post" song plays in home screen on open (or maybe when play is clicked)
*   -Add JUnit tests where applicable
*       -FinishState placements (How to test this?)
*
* TODO ROADMAP:
*   -Spruce up title and finish screens
* */