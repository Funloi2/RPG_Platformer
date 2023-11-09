package main;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow(GamePanel panel) {
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}