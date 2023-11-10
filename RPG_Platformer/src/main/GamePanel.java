package main;

import entities.Player;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    MouseInputs mouseInputs;
    public int xDelta = 100;
    public int yDelta = 100;
    private Game game;
    private Player player;

    public GamePanel(Game game) {
        addKeyListener(new KeyboardInputs(this));
        mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        this.game = game;
        this.player = new Player(100, 100, 100, 50);

    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRecPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }

    public Player getPlayer() {
        return player;
    }
}