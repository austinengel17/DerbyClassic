package main;

import state.State;
import state.TitleState;
import entity.Horse;
import object.Item;
import tile.TileManager;
import util.ChronoUtil;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    // TODO: turn up ogTileSize to 32x32
    final int originalTileSize = 16; // 16x16
    // TODO: Turn scale down to 2, make the horses more visible
    final int scale = 3; // scale due to larger resolutions in modern screens

    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12; // TODO: Resize screen to 10 (elim green space)
    public final int screenWidth = tileSize * maxScreenCol; // 768 Pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 Pixels

    public KeyHandler kh = new KeyHandler(this);

    Thread gameThread;

    // FPS
    public int FPS = 30;
    public int frameCounter;

    // GAME STATE
    public State gameState;

    public TileManager tm = new TileManager(this);
    public CollisionChecker cc = new CollisionChecker(this);
    public AssetSetter as = new AssetSetter(this);
    public ChronoUtil raceTimer;


    //Horses
    public Horse seabiscuit;
    public Horse kryptonite;
    public Horse tornado;
    public Horse lucky;
    public Horse risingStar;
    public Horse silverCharm;
    public Horse lineup[];
    public Horse[] winnerPlacement;
    public int finishCounter;

    public Item item[] = new Item[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
        gameState = new TitleState(this);
        raceTimer = new ChronoUtil();
    }

    public void setupGame() {
        winnerPlacement = new Horse[6];
        raceTimer.reset();
        finishCounter = 0;
        frameCounter = 0;
        as.setupInitialItems();
        setupEntities();
        tm.addRetainingBars();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                // 1. UPDATE: update information such as horse position
                update();

                // 2. DRAW: draw the screen with the updated information
                repaint();

                delta--;
            }

        }
    }

    public void update() {
        frameCounter = frameCounter == FPS ? 0 : frameCounter + 1;
        gameState.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        gameState.render(g2);

        g2.dispose();
    }

    public int getXforCenteredText(String text, Graphics2D g2) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = screenWidth/2 - length/2;
        return x;
    }

    public int getYforCenteredText(String text, Graphics2D g2) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
        int y = screenHeight/2 - length/2;
        return y;
    }

    private void setupEntities(){

        seabiscuit = new Horse(this, "Seabiscuit");
        kryptonite = new Horse(this, "Kryptonite");
        tornado = new Horse(this, "Tornado");
        lucky = new Horse(this, "Lucky");
        risingStar = new Horse(this, "Rising Star");
        silverCharm = new Horse(this, "Silver Charm");
        lineup = new Horse[6];

        lineup[0] = seabiscuit;
        lineup[1] = kryptonite;
        lineup[2] = tornado;
        lineup[3] = lucky;
        lineup[4] = risingStar;
        lineup[5] = silverCharm;
    }
}
