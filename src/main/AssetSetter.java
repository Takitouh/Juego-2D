package main;

import object.OBJChest;
import object.OBJDoor;
import object.OBJKey;
import object.OBJSketcher;

public class AssetSetter {

    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void setObject() {

        gamePanel.sObjects[0] = new OBJKey();
        gamePanel.sObjects[0].worldX = gamePanel.tileSize * 2;
        gamePanel.sObjects[0].worldY = gamePanel.tileSize * 2;

        gamePanel.sObjects[1] = new OBJKey();
        gamePanel.sObjects[1].worldX = gamePanel.tileSize * 13;
        gamePanel.sObjects[1].worldY = gamePanel.tileSize * 2;

        gamePanel.sObjects[2] = new OBJKey();
        gamePanel.sObjects[2].worldX = gamePanel.tileSize * 3;
        gamePanel.sObjects[2].worldY = gamePanel.tileSize * 13;

        gamePanel.sObjects[3] = new OBJDoor();
        gamePanel.sObjects[3].worldX = gamePanel.tileSize * 5;
        gamePanel.sObjects[3].worldY = gamePanel.tileSize * 5;

        gamePanel.sObjects[4] = new OBJDoor();
        gamePanel.sObjects[4].worldX = gamePanel.tileSize * 15;
        gamePanel.sObjects[4].worldY = gamePanel.tileSize * 5;

        gamePanel.sObjects[5] = new OBJDoor();
        gamePanel.sObjects[5].worldX = gamePanel.tileSize * 46;
        gamePanel.sObjects[5].worldY = gamePanel.tileSize * 5;

        gamePanel.sObjects[6] = new OBJChest();
        gamePanel.sObjects[6].worldX = gamePanel.tileSize * 44;
        gamePanel.sObjects[6].worldY = gamePanel.tileSize * 2;

        gamePanel.sObjects[7] = new OBJSketcher();
        gamePanel.sObjects[7].worldX = gamePanel.tileSize * 7;
        gamePanel.sObjects[7].worldY = gamePanel.tileSize * 45;
    }
}
