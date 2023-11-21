package level;

import main.Game;
import objects.Chest;
import objects.Potion;
import utilz.HelpMethod;
import utilz.LoadSave;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Level {
    /// ------------------------------- ATTRIBUTES ------------------------------- ///
    // Sprite
    private BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_MAP);

    // Lvl data
    private int[][] lvlData;

    // Chest
    private ArrayList<Chest> chests;
    private ArrayList<Potion> potions;

    // Boss room
    private Rectangle2D.Float bossRoomHitBoxBottom, bossRoomHitBoxCenter;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
        createChests();
        createPotions();
        initBossRoomHitBox();

    }

    /// ------------------------------- METHOD ------------------------------- ///

    private void createChests() {
        chests = HelpMethod.GetChests(img);
    }

    private void createPotions() {
        potions = HelpMethod.GetPotions(img);
    }

    private void initBossRoomHitBox() {
        bossRoomHitBoxBottom = new Rectangle2D.Float(50 * Game.TILES_SIZE, 35 * Game.TILES_SIZE, 24 * Game.TILES_SIZE, 1);
        bossRoomHitBoxCenter = new Rectangle2D.Float((int) (62.5 * Game.TILES_SIZE), 26 * Game.TILES_SIZE, 1, 12 * Game.TILES_SIZE);
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///+

    public int[][] getLvlData() {
        return lvlData;
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public ArrayList<Chest> getChests() {
        return chests;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public Rectangle2D.Float getBossRoomHitBoxBottom() {
        return bossRoomHitBoxBottom;
    }

    public Rectangle2D.Float getBossRoomHitBoxCenter() {
        return bossRoomHitBoxCenter;
    }
}
