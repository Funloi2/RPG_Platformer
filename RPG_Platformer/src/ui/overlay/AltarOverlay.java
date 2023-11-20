package ui.overlay;

import gameStates.Playing;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class AltarOverlay {

    // Access to playing method
    private final Playing playing;

    // Position and size
    private int bgX;
    private int bgY;
    private int bgWidth;
    private int bgHeight;

    // Background sprite
    private BufferedImage background;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public AltarOverlay(Playing playing) {
        this.playing = playing;
        background = LoadSave.GetSpriteAtlas(LoadSave.ALTAR_UI);

        bgX = Game.GAME_WIDTH / 2 - background.getWidth() / 2;
        bgY = Game.GAME_HEIGHT / 2 - background.getHeight() / 2;
        bgWidth = background.getWidth();
        bgHeight = background.getHeight();
    }

    /// ------------------------------- METHOD ------------------------------- ///

    public void update() {
    }

    public void draw(Graphics g) {
        // Background
        g.drawImage(background, bgX, bgY, bgWidth, bgHeight, null);

    }


    //////////// Event handlers ////////////

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

}
