package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //     SCREEN SETTINGS
    final int originalTileSize = 16; //16x16
    final int scale = 3;
    final int TileSize = originalTileSize * scale; //48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * TileSize; //768 pixels
    final int screenHeight = maxScreenRow * TileSize; //576 pixels

    Thread gameThread;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
    public void starGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

    }
}
