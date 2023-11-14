package level;

import objects.Chest;
import objects.Potion;
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
    private ArrayList<Potion> potions;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
        createChests();
        createPotions();

    }

    /// ------------------------------- METHOD ------------------------------- ///

    private void createChests() {
        chests = HelpMethod.GetChests(img);
    }

    private void createPotions() {
        potions = HelpMethod.GetPotions(img);
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
}
