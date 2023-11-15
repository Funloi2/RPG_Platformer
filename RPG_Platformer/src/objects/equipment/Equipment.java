package objects.equipment;

import objects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.ObjectConstants.ARMOR_HEIGHT;
import static utilz.Constants.ObjectConstants.ARMOR_WIDTH;

public abstract class Equipment extends GameObject {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///
    protected String name;
    protected int level;

    // Sprite
    protected BufferedImage image;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    protected Equipment(String name, int x, int y, int objType) {
        super(x, y, objType);
        this.name = name;
        active = true;
    }
    /// ------------------------------- METHOD ------------------------------- ///

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.drawImage(image, x - xLvlOffset, y - yLvlOffset, ARMOR_WIDTH, ARMOR_HEIGHT, null);
        drawHitBox(g, xLvlOffset, yLvlOffset);
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///


    public int getLevel() {
        return level;
    }

    public BufferedImage getImage() {
        return image;
    }
}
