package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Fixed the issue with the focus of the window
        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game Adventure");
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.starGameThread();

    }
}
