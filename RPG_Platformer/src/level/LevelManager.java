package level;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TILES_SIZE;

public class LevelManager {
    /// ------------------------------- ATTRIBUTES ------------------------------- ///

    private BufferedImage[] levelSprite;
    private Level levelOne;
    private Game game;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprite();
        levelOne = new Level(LoadSave.GetLevelData());
    }


    /// ------------------------------- METHOD ------------------------------- ///

    private void importOutsideSprite() {
        BufferedImage image = LoadSave.GetSpriteAtlas(LoadSave.BLOCK_SPRITE);

        levelSprite = new BufferedImage[48];

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = image.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g, int xLvlOffset) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < levelOne.getLvlData()[0].length; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], TILES_SIZE * i - xLvlOffset, TILES_SIZE * j, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }

    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

    public Level getCurrentLevel() {
        return levelOne;
    }

}
