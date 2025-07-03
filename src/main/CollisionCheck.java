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

    public int checkObjectCollision(Entity entity, boolean player) {
        int index = 999;
        for (int i = 0; i<gamePanel.sObjects.length; i++) {
            if (gamePanel.sObjects[i] != null) {
                SuperObject ob = gamePanel.sObjects[i];

                entity.hitBox.x = entity.worldX + entity.hitBoxDefaultX;
                entity.hitBox.y= entity.worldY + entity.hitBoxDefaultY;

                ob.hitBoxObject.x = ob.worldX + ob.hitBoxDefaultX;
                ob.hitBoxObject.y = ob.worldY + ob.hitBoxDefaultY;

                switch (entity.direction) {
                    case "up":
                        entity.hitBox.y -= entity.speed;
                        if (entity.hitBox.intersects(ob.hitBoxObject)){
                            if (ob.collision){
                                entity.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.hitBox.y += entity.speed;
                        if (entity.hitBox.intersects(ob.hitBoxObject)){
                            if (ob.collision){
                                entity.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.hitBox.x -= entity.speed;
                        if (entity.hitBox.intersects(ob.hitBoxObject)){
                            if (ob.collision){
                                entity.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.hitBox.x += entity.speed;
                        if (entity.hitBox.intersects(ob.hitBoxObject)){
                            if (ob.collision){
                                entity.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;

                }
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;

                ob.hitBoxObject.x = ob.hitBoxDefaultX;
                ob.hitBoxObject.y = ob.hitBoxDefaultY;
            }
        }

        return index;
    }

}
