package objects.equipment;

import utilz.LoadSave;

import java.awt.image.BufferedImage;

import static utilz.Constants.ObjectConstants.*;

public class Chestplate extends Armor {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Chestplate(int x, int y, int set, int level) {
        super("Chestplate", 10, CHEST_PLATE, set);
        this.x = x;
        this.y = y;
        this.level = level;
        armor += level * 2;

        loadImage();
        initHitBox(ARMOR_WIDTH, ARMOR_HEIGHT);
    }

    /// ------------------------------- METHOD ------------------------------- ///

    public void loadImage() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.ARMOR_ATLAS);

        switch (setNumber) {
            case 0 -> image = temp.getSubimage(32, 0, ARMOR_WIDTH_DEFAULT, ARMOR_HEIGHT_DEFAULT);
            case 1 -> image = temp.getSubimage(32, 3 * 32, ARMOR_WIDTH_DEFAULT, ARMOR_HEIGHT_DEFAULT);
            case 2 -> image = temp.getSubimage(32, 6 * 32, ARMOR_WIDTH_DEFAULT, ARMOR_HEIGHT_DEFAULT);
            default -> image = temp.getSubimage(32, 7 * 32, ARMOR_WIDTH_DEFAULT, ARMOR_HEIGHT_DEFAULT);
        }
    }

    /// ------------------------------- GETTER AND SETTER ------------------------------- ///
    public int getArmor() {
        return armor;
    }


}
