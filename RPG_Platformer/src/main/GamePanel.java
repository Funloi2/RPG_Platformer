package main;

import entities.Player;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import level.LevelManager;
import utilz.LoadSave;

import javax.swing.*;
import java.awt.*;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class GamePanel extends JPanel {

    /// ------------------------------- ATTRIBUTES ------------------------------- ///

    MouseInputs mouseInputs;
    public int xDelta = 100;
    public int yDelta = 100;
    private Game game;
    private Player player;
    private LevelManager levelManager;
    private int xLvlOffset;
    private int leftBorder = (int) (0.40 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.60 * Game.GAME_WIDTH);
    private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
    private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
    private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public GamePanel(Game game) {
        addKeyListener(new KeyboardInputs(this));
        mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        this.game = game;

        setPanelSize();
        initClass();
    }


    /// ------------------------------- METHOD ------------------------------- ///

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }

    private void initClass() {
        this.player = new Player(100, 100, 100, 50);
        levelManager = new LevelManager(game);
    }

    public void update() {
        player.update();
    }

    public void render(Graphics g) {
        levelManager.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }

    private void checkCloseBorder() {
        int playerX = (int) player.getHitBox().x;
        int diff = playerX - xLvlOffset;

        if (diff > rightBorder) {
            xLvlOffset += diff - rightBorder;
        } else if (diff < leftBorder) {
            xLvlOffset += diff - leftBorder;
        }

        if (xLvlOffset > maxLvlOffsetX) {
            xLvlOffset = maxLvlOffsetX;
        } else if (xLvlOffset < 0) {
            xLvlOffset = 0;
        }
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

}