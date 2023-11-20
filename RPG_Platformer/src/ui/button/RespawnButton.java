package ui.button;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class RespawnButton {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    private int x, y, width, height;
    private BufferedImage[] buttonImage;
    private Rectangle2D.Float hitBox;

    private boolean mousePressed, mouseReleased;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public RespawnButton() {
        loadImage();
        width = (int) (buttonImage[0].getWidth() * 0.8);
        height = (int) (buttonImage[0].getHeight() * 0.8);
        this.x = Game.GAME_WIDTH / 2 - width / 2;
        this.y = Game.GAME_HEIGHT / 2 + (int) (110 * Game.SCALE);

        hitBox = new Rectangle2D.Float(x, y, buttonImage[0].getWidth(), buttonImage[0].getHeight());

    }

    /// ------------------------------- METHOD ------------------------------- ///

    public void loadImage() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.RESPAWN_BUTTON_ATLAS);

        buttonImage = new BufferedImage[2];

        for (int i = 0; i < 2; i++) {
            buttonImage[i] = temp.getSubimage(0, i * 100, 266, 100);
        }
    }

    public void draw(Graphics g) {
        if (mousePressed)
            g.drawImage(buttonImage[1], x, y, width, height, null);
        else
            g.drawImage(buttonImage[0], x, y, width, height, null);
    }

    /// ------------------------------- GETTER AND SETTER ------------------------------- ///


    public boolean isMousePressed() {
        return mousePressed;
    }

    public boolean isMouseReleased() {
        return mouseReleased;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void setMouseReleased(boolean mouseReleased) {
        this.mouseReleased = mouseReleased;
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }
}
