package level;

import objects.Chest;
import utilz.HelpMethod;
import utilz.LoadSave;

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

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
        createChests();
    }

    /// ------------------------------- METHOD ------------------------------- ///

    private void createChests() {
        chests = HelpMethod.GetChests(img);
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
}
