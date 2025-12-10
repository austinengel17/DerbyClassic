package state;

import entity.Entity;
import main.GamePanel;

public abstract class EntityState extends AbstractState{
    Entity entity;

    public EntityState(GamePanel gp, Entity entity) {
        super(gp);
        this.entity = entity;
    }
}
