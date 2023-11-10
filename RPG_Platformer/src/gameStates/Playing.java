package gameStates;

import entities.Player;
import level.LevelManager;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static main.Game.GAME_HEIGHT;

public class Playing extends State implements StateMethods {
    /// ------------------------------- ATTRIBUTES ------------------------------- ///

    // Class to use in playing state
    private Player player;
    private LevelManager levelManager;

    // Game border and level Offset var
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

    public Playing(Game game) {
        super(game);
        initClass();
    }

    /// ------------------------------- METHOD ------------------------------- ///

    private void initClass() {
        levelManager = new LevelManager(game);
        player = new Player(13 * Game.TILES_SIZE, 34 * Game.TILES_SIZE, 18, 39);
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
    }

    private void checkCloseBorder() {
        int playerX = (int) player.getHitBox().x;
        int playerY = (int) player.getHitBox().y;

        int diffX = playerX - xLvlOffset;
        int diffY = playerY - yLvlOffset;

        // TODO: fix method to avoid code duplication

        // X Level Offset
//        xLvlOffset = handleLvlOffset(diffX, leftBorder, rightBorder, xLvlOffset, maxLvlOffsetX);
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
//        yLvlOffset = handleLvlOffset(diffY, topBorder, downBorder, yLvlOffset, maxLvlOffsetY);
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

    private int handleLvlOffset(int diff, int startBorder, int endBorder, int offset, int maxOffset) {
        if (diff > endBorder) {
            return offset + diff - endBorder;
        } else if (diff < startBorder) {
            return offset + diff - startBorder;
        }

        if (offset > maxOffset) {
            return maxOffset;
        } else if (offset < 0) {
            return 0;
        }
        return offset;
    }

    @Override
    public void update() {
        player.update();
        checkCloseBorder();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g, xLvlOffset, yLvlOffset);
        player.render(g, xLvlOffset, yLvlOffset);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q -> player.setLeft(true);
            case KeyEvent.VK_D -> player.setRight(true);
            case KeyEvent.VK_SPACE -> player.setJump(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q -> player.setLeft(false);
            case KeyEvent.VK_D -> player.setRight(false);
            case KeyEvent.VK_SPACE -> player.setJump(false);
        }
    }
    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

    public Player getPlayer() {
        return player;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

}
