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
    private int yLvlOffset;
    private int leftBorder = (int) (0.50 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.50 * Game.GAME_WIDTH);
    private int topBorder = (int) (0.50 * GAME_HEIGHT);
    private int downBorder = (int) (0.50 * GAME_HEIGHT);

    private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
    private int lvlTilesHeight = LoadSave.GetLevelData().length;

    private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
    private int maxTilesOffsetY = lvlTilesHeight - Game.TILES_IN_HEIGHT;

    private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;
    private int maxLvlOffsetY = maxTilesOffsetY * Game.TILES_SIZE;


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
        levelManager = new LevelManager(game);
        player = new Player(13 * Game.TILES_SIZE, 34 * Game.TILES_SIZE, 18, 39);
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
    }

    public void update() {
        player.update();
        checkCloseBorder();
    }

    public void render(Graphics g) {
        levelManager.draw(g, xLvlOffset, yLvlOffset);
        player.render(g, xLvlOffset, yLvlOffset);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }

    private void checkCloseBorder() {
        int playerX = (int) player.getHitBox().x;
        int playerY = (int) player.getHitBox().y;

        int diffX = playerX - xLvlOffset;
        int diffY = playerY - yLvlOffset;


        // X Level Offset
        if (diffX > rightBorder) {
            xLvlOffset += diffX - rightBorder;
        } else if (diffX < leftBorder) {
            xLvlOffset += diffX - leftBorder;
        }

        if (xLvlOffset > maxLvlOffsetX) {
            xLvlOffset = maxLvlOffsetX;
        } else if (xLvlOffset < 0) {
            xLvlOffset = 0;
        }

        // Y Level Offset
        if (diffY > downBorder) {
            yLvlOffset += diffY - downBorder;
        } else if (diffY < topBorder) {
            yLvlOffset += diffY - topBorder;
        }

        if (yLvlOffset > maxLvlOffsetY) {
            yLvlOffset = maxLvlOffsetY;
        } else if (yLvlOffset < 0) {
            yLvlOffset = 0;
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