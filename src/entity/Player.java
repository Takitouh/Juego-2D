package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    public GamePanel gamePanel;
    public KeyHandler keyHand;
    public BufferedImage bWalk1, bWalk2, fWalk1, fWalk2, lWalk1, lWalk2, rWalk1, rWalk2;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;


    public Player(GamePanel gamePanel, KeyHandler keyHand) {
        this.gamePanel = gamePanel;
        this.keyHand = keyHand;

        this.hitBox = new Rectangle(9, 16, 30, 32);
        this.screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        this.screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);
        this.hitBoxDefaultX = hitBox.x;
        this.hitBoxDefaultY = hitBox.y;
        setdefaultValues();
        getplayerImage();
    }

    public void setdefaultValues() {
        worldX = gamePanel.tileSize * 4;
        worldY = gamePanel.tileSize * 3;
        speed = 3;
        direction = "down";
    }

    public void getplayerImage() {
        try {
//Load the sprites
            bWalk1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/BackWalk_1.png")));
            bWalk2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/BackWalk_2.png")));
            fWalk1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/FrontWalk_1.png")));
            fWalk2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/FrontWalk_2.png")));
            lWalk1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/Left_1.png")));
            lWalk2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/Left_2.png")));
            rWalk1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/Rigth_1.png")));
            rWalk2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/Rigth_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void update() {
        //We sum or substract the playerSpeed of the position player in X, Y when some key of WASD is pressed
        //And modify the direction
        if (keyHand.upKey || keyHand.downKey || keyHand.leftKey || keyHand.rightKey) {
            if (keyHand.upKey) {
                direction = "up";
            }
            if (keyHand.downKey) {
                direction = "down";
            }
            if (keyHand.leftKey) {
                direction = "left";
            }
            if (keyHand.rightKey) {
                direction = "right";
            }
            //CHECK TILE COLLISION
            collisionOn = false;
            gamePanel.collisionCheck.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gamePanel.collisionCheck.checkObjectCollision(this, true);
            this.pickUpObject(objIndex);

            //IF COLLISION IS FALSE THE PLAYER CAN MOVE
            if (!gamePanel.player.collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;

                        break;
                    case "down":
                        worldY += speed;

                        break;
                    case "left":
                        worldX -= speed;

                        break;
                    case "right":
                        worldX += speed;

                        break;

                }
            }

            spriteCount++;
            if (spriteCount > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCount = 0;
            }
        }


    }

    public void draw(Graphics2D g2d) {
//According to the direction one image will load
        BufferedImage img = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    img = bWalk1;
                } else if (spriteNum == 2) {
                    img = bWalk2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    img = fWalk1;
                } else if (spriteNum == 2) {
                    img = fWalk2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    img = lWalk1;
                } else if (spriteNum == 2) {
                    img = lWalk2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    img = rWalk1;
                } else if (spriteNum == 2) {
                    img = rWalk2;
                }
                break;
        }
        g2d.drawImage(img, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objName = gamePanel.sObjects[i].name;

            switch (objName) {
                case "Key":
                    hasKey++;
                    gamePanel.sObjects[i] = null;
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gamePanel.sObjects[i] = null;
                        hasKey--;
                    }
                    break;

            }
        }
    }
}
