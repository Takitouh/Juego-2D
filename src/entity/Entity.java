package entity;

import java.awt.*;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public int spriteNum = 1, spriteCount = 0;
    public Rectangle hitBox;
    public String direction;
    public int hitBoxDefaultX, hitBoxDefaultY;
    public boolean collisionOn;
}
