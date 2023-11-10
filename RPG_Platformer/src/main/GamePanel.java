package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class GamePanel extends JPanel {

    /// ------------------------------- ATTRIBUTES ------------------------------- ///

    MouseInputs mouseInputs;
    private Game game;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public GamePanel(Game game) {
        addKeyListener(new KeyboardInputs(this));
        mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        this.game = game;

        setPanelSize();
    }


    /// ------------------------------- METHOD ------------------------------- ///

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

    public Game getGame() {
        return game;
    }

}