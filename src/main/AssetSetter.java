package main;

import object.FinishLine;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setupInitialItems() {
        gp.item[0] = new FinishLine();
        gp.item[0].worldX = 15 * gp.tileSize;
        gp.item[0].worldY = 3 * gp.tileSize;

        gp.item[1] = new FinishLine();
        gp.item[1].worldX = 15 * gp.tileSize;
        gp.item[1].worldY = 4 * gp.tileSize;

        gp.item[2] = new FinishLine();
        gp.item[2].worldX = 15 * gp.tileSize;
        gp.item[2].worldY = 5 * gp.tileSize;

        gp.item[3] = new FinishLine();
        gp.item[3].worldX = 15 * gp.tileSize;
        gp.item[3].worldY = 6 * gp.tileSize;

        gp.item[4] = new FinishLine();
        gp.item[4].worldX = 15 * gp.tileSize;
        gp.item[4].worldY = 7 * gp.tileSize;

        gp.item[5] = new FinishLine();
        gp.item[5].worldX = 15 * gp.tileSize;
        gp.item[5].worldY = 8 * gp.tileSize;
    }
}
