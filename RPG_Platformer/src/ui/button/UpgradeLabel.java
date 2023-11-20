package ui.button;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class UpgradeLabel {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///
    private int x, y, width, height;
    private int labelType;
    private BufferedImage images;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public UpgradeLabel(int x, int y, int labelType) {

        this.x = x;
        this.y = y;
        this.labelType = labelType;

        images = LoadSave.GetSpriteAtlas(LoadSave.UPGRADE_LABEL_ATLAS).getSubimage(0, labelType * 256, 576, 256);
        width = images.getWidth() / 3;
        height = images.getHeight() / 3;
    }

    /// ------------------------------- METHOD ------------------------------- ///
    public void draw(Graphics g) {
        g.drawImage(images, x, y,width , height, null);
    }

    /// ------------------------------- GETTER AND SETTER ------------------------------- ///


    public int getHeight() {
        return height;
    }
}
