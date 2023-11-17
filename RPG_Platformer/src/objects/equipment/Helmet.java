package objects.equipment;

import objects.equipment.Armor;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.ObjectConstants.*;


public class Helmet extends Armor {

    /// ------------------------------- ATTRIBUTE ------------------------------- ///


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Helmet(int x, int y) {
        super("Helmet", 10, HELMET, -1);
        this.x = x;
        this.y = y;
        level = x/10;
        armor += level * 2;


        loadImage();
        initHitBox(ARMOR_WIDTH, ARMOR_HEIGHT);
    }

    /// ------------------------------- METHOD ------------------------------- ///


    public void loadImage() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.ARMOR_ATLAS);

        image = temp.getSubimage(0, 32, ARMOR_WIDTH_DEFAULT, ARMOR_HEIGHT_DEFAULT);

    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///
    public int getArmor() {
        return armor;
    }

}
