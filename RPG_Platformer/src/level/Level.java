package level;

import utilz.LoadSave;

import java.awt.image.BufferedImage;

public class Level {
    /// ------------------------------- ATTRIBUTES ------------------------------- ///

    // Sprite
    private BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_MAP);
    // Lvl data
    private int[][] lvlData;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    /// ------------------------------- METHOD ------------------------------- ///


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///+

    public int[][] getLvlData() {
        return lvlData;
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }
}
