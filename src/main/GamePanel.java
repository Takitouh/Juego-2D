package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //     SCREEN SETTINGS
    public final int originalTileSize = 16; //16x16
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize; //768 pixels
    public final int screenHeight = maxScreenRow * tileSize; //576 pixels

    //      WORLD SETTINGS
    public final int maxworldCol = 50;
    public final int maxworldRow = 50;
    //UI
    public UI ui = new UI(this);

    //AUDIO
    Sound music = new Sound();
    Sound se = new Sound();


    //FPS
    final int FPS = 60;
    TileManager tileMan = new TileManager(this);
    KeyHandler keyHand = new KeyHandler();
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    public Player player = new Player(this, keyHand);
    public SuperObject[] sObjects = new SuperObject[10];
    public AssetSetter assetSetter = new AssetSetter(this);
    Thread gameThread;


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

    public void setupGame() {
        assetSetter.setObject();
        playMusic(0);
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

        while (gameThread.isAlive()) {


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

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        //TILES
        tileMan.draw(g2d);
        //OBJECTS
        for (SuperObject sObject : sObjects) {
            if (sObject != null) {
                sObject.draw(g2d, this);
            }
        }
        //PLAYER
        player.draw(g2d);
        //UI
        ui.draw(g2d);
    }
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        se.setFile(i);
        se.play();
    }
}
