package state;

import main.GamePanel;

public abstract class AbstractState implements State{
    GamePanel gp;
    int frameCounter;

    public AbstractState(GamePanel gp) {
        this.gp = gp;
        frameCounter = 0;
    }
}
