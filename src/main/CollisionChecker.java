package main;

import entity.Entity;
import object.Item;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.x + entity.solidArea.x;
        int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.y + entity.solidArea.y;
        int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;
        //TODO: add AIOB Exception checks for each direction movement (this is only rightside)
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down": // TODO: add down collision if needed
                break;
            case "left": // TODO: add left collision if needed
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                if(entityRightCol < gp.maxScreenCol) { // Eliminate AIOB Exception, we will not be checking for collision on tiles outside of map
                    tileNum1 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];
                    if (gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                }
                break;
            case "stationary":
                break;
        }
    }

    public int checkObject(Entity entity) {

        int index = 999;
        int tracker = 0;
        for(Item item : gp.item) {
            if(item != null) {
                // Get entity's solid area position
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;
                // Get the item's solid area position
                item.solidArea.x = item.worldX + item.solidArea.x;
                item.solidArea.y = item.worldY + item.solidArea.y;

                switch(entity.direction) {
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(item.solidArea)) {
                            if (item.collision == true) {
                                System.out.println(entity.name + " finished!");
                                index = tracker;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.getSolidAreaDefaultX();
                entity.solidArea.y = entity.getSolidAreaDefaultY();
                item.solidArea.x = item.getSolidAreaDefaultX();
                item.solidArea.y = item.getSolidAreaDefaultY();
            }
            tracker++;
        }
        return index;
    }
}
