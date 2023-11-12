package gameStates;

import entities.EnemyManager;
import entities.Player;
import level.LevelManager;
import main.Game;
import objects.Altar;
import utilz.LoadSave;
import ui.overlay.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class Playing extends State implements StateMethods {
    /// ------------------------------- ATTRIBUTES ------------------------------- ///

    // Class to use in playing state
    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private InventoryOverlay inventoryOverlay;
    private PauseOverlay pauseOverlay;
    private AtlasOverlay atlasOverlay;
    private Altar altar;

    // Game border and level Offset var
    private int xLvlOffset;
    private int yLvlOffset;
    private int leftBorder = (int) (0.50 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.50 * Game.GAME_WIDTH);
    private int topBorder = (int) (0.50 * GAME_HEIGHT);
    private int downBorder = (int) (0.70 * GAME_HEIGHT);

    private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
    private int lvlTilesHeight = LoadSave.GetLevelData().length;

    private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
    private int maxTilesOffsetY = lvlTilesHeight - Game.TILES_IN_HEIGHT;

    private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;
    private int maxLvlOffsetY = maxTilesOffsetY * Game.TILES_SIZE;

    // Overlay boolean
    private boolean inventory = false;
    private boolean paused = false;
    private boolean useAtlas = false;

    // Background
    private BufferedImage backgroundImage, spawnBgImage;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public Playing(Game game) {
        super(game);
        initClass();

        loadBackground();
    }


    /// ------------------------------- METHOD ------------------------------- ///

    private void loadBackground() {
        backgroundImage = LoadSave.GetSpriteAtlas(LoadSave.BG_LEVEL);
        spawnBgImage = LoadSave.GetSpriteAtlas(LoadSave.SPAWN_BG);
    }

    private void initClass() {
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);
        player = new Player(8 * Game.TILES_SIZE, 34 * Game.TILES_SIZE, 18, 39, this);
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
        inventoryOverlay = new InventoryOverlay(this);
        pauseOverlay = new PauseOverlay(this);
        atlasOverlay = new AtlasOverlay(this);
        altar = new Altar(13 * Game.TILES_SIZE, 33 * Game.TILES_SIZE);
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

    public void checkSpeakToAtlas() {
        if (player.getHitBox().intersects(altar.getHitBox())) {
            useAtlas = true;
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
        if (!inventory && !paused && !useAtlas) {
            enemyManager.update(levelManager.getCurrentLevel().getLvlData(), player);
            player.update();
            checkCloseBorder();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0 - xLvlOffset, 0 - yLvlOffset, (int) (GAME_WIDTH * 2.4), (int) (GAME_HEIGHT * 1.8), null);
        g.drawImage(spawnBgImage, (int) (0.3 * Game.TILES_SIZE - xLvlOffset), 27 * Game.TILES_SIZE - yLvlOffset, GAME_WIDTH - 2 * Game.TILES_SIZE, GAME_HEIGHT - 4 * Game.TILES_SIZE, null);


        levelManager.draw(g, xLvlOffset, yLvlOffset);
        player.render(g, xLvlOffset, yLvlOffset);
        enemyManager.draw(g, xLvlOffset, yLvlOffset);
        altar.draw(g, xLvlOffset, yLvlOffset);

        if (inventory || paused || useAtlas) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
            if (inventory)
                inventoryOverlay.draw(g);
            else if (paused)
                pauseOverlay.draw(g);
            else if (useAtlas)
                atlasOverlay.draw(g);
        }
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
            case KeyEvent.VK_F -> player.setAction(true);
            case KeyEvent.VK_I -> {
                if (!paused && !useAtlas)
                    inventory = !inventory;
            }
            case KeyEvent.VK_ESCAPE -> {
                if (!inventory && !useAtlas)
                    paused = !paused;
                if (useAtlas)
                    useAtlas = false;
            }
            case KeyEvent.VK_SPACE -> player.setJump(true);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q -> player.setLeft(false);
            case KeyEvent.VK_D -> player.setRight(false);
            case KeyEvent.VK_F -> player.setAction(false);
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
