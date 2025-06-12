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
    final int FPS = 60;
    //Default coordinates for player and his velocity
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 3;

    Thread gameThread;
    KeyHandler keyHand = new KeyHandler();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHand);
        this.setFocusable(true);
    }
    public void starGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

// Sleep time method:
//    @Override
//    public void run() {
//        double drawInterval = System.nanoTime() / 1000000000.0;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while(gameThread.isAlive()){
//            update();
//
//            repaint();
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                if (remainingTime < 0) {
//                    remainingTime = 0;
//                }
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//            }
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
// Delta method:
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread.isAlive()){


        currentTime = System.nanoTime();

        delta += (currentTime - lastTime) / drawInterval; // The time that has been passed between the lastTime and currentime
            //between the drawInterval

        timer += currentTime - lastTime;

        lastTime = currentTime;

        if (delta >= 1) {
            update();
            repaint();
            delta--;
            drawCount++;
        }
        //This display the current FPS of the game
            //If the timer is mayor or equal to 1, because is the FPS
        if (timer >= 1000000000) {
            System.out.println("FPS: " + drawCount);
            drawCount = 0;
            timer = 0;
        }


        }
    }

    public void update(){
        //We sum or substract the playerSpeed of the position player in X, Y when some key of WASD is pressed
        if (keyHand.upKey){
            playerY -= playerSpeed;
        }
        if (keyHand.downKey){
            playerY += playerSpeed;
        }
        if (keyHand.leftKey){
            playerX -= playerSpeed;
        }
        if (keyHand.rightKey){
            playerX += playerSpeed;
        }


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(playerX, playerY, TileSize, TileSize);
        g2d.dispose(); //We save memory
    }
}
