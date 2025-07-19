package main;

import object.OBJBook;
import object.OBJKey;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gamePanel;
    Font timesRoman_32, timesRoman_48B;
    BufferedImage keyImage;
    BufferedImage bookImage;
    public boolean activeMessage = false;
    public String message = "";
    public int countMessage = 0;
    public boolean finishGame = false;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        timesRoman_32 = new Font("TimesRoman", Font.PLAIN, 32);
        timesRoman_48B = new Font("TimesRoman", Font.BOLD, 48);
        OBJKey key = new OBJKey();
        OBJBook book = new OBJBook();
        keyImage = key.image;
        bookImage = book.image;
    }

    public void showMessage(String text) {
        message = text;
        activeMessage = true;
    }

    public void draw(Graphics2D g2d) {
        if (finishGame) {
            g2d.setFont(timesRoman_48B);
            g2d.setColor(Color.BLUE);
            g2d.drawString("Congratulations :)", gamePanel.tileSize*4, gamePanel.screenHeight/2);
            gamePanel.gameThread = null;
        }else {
            g2d.setFont(timesRoman_32);
            g2d.setColor(Color.WHITE);
            g2d.drawImage(keyImage, 24, 24, 36, 36, null);
            g2d.drawImage(bookImage, 24, 72, 36, 36, null);
            g2d.drawString(": " + gamePanel.player.hasKey, 66, 54);
            g2d.setFont(timesRoman_32);
            g2d.setColor(Color.WHITE);
            g2d.drawString(": ", 66, 94);
            if (activeMessage) {
                g2d.setFont(g2d.getFont().deriveFont(25f));
                g2d.drawString(message, gamePanel.tileSize*5+24, gamePanel.tileSize+12);
                countMessage++;
                if(countMessage > 90){
                    activeMessage = false;
                    countMessage = 0;
                }
            }
        }
    }
}
