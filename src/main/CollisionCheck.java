package main;

import entity.Entity;

public class CollisionCheck {
    public GamePanel gamePanel;

    public CollisionCheck(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + gamePanel.player.hitBox.x;
        int entityRigthWorldX = entity.worldX + gamePanel.player.hitBox.x + gamePanel.player.hitBox.width;
        int entityTopWorldY = entity.worldY + gamePanel.player.hitBox.y;
        int entityBottomWorldY = entity.worldY + gamePanel.player.hitBox.y + gamePanel.player.hitBox.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRigthWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (gamePanel.player.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileMan.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileMan.mapTileNum[entityRightCol][entityTopRow];
                if (gamePanel.tileMan.tiles[tileNum1].collision || gamePanel.tileMan.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileMan.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileMan.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileMan.tiles[tileNum1].collision || gamePanel.tileMan.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileMan.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileMan.mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.tileMan.tiles[tileNum1].collision || gamePanel.tileMan.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRigthWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileMan.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileMan.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileMan.tiles[tileNum1].collision || gamePanel.tileMan.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

        }

    }
}
