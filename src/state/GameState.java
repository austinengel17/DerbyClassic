package state;

import main.GamePanel;
import util.MessageUtil;

public abstract class GameState extends AbstractState{
    MessageUtil messageUtil;

    public GameState(GamePanel gp) {
        super(gp);
        messageUtil = MessageUtil.getMessageUtil();
    }
}
