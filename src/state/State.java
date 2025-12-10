package state;

import java.awt.*;

public interface State {
    public void update();
    public void render(Graphics2D g2);
    public void handleKeyEvent(int code);
}
