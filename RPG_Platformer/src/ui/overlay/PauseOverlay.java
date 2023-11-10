package ui.overlay;

import gameStates.Playing;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PauseOverlay {

    // Access to playing method
    private final Playing playing;

    // Position and size
    private int bgX;
    private int bgY;
    private int bgWidth;
    private int bgHeight;

    // Background sprite
//    private BufferedImage background;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public PauseOverlay(Playing playing) {
        this.playing = playing;

        loadBackground();
    }

    /// ------------------------------- METHOD ------------------------------- ///

    public void update() {
    }

    public void draw(Graphics g) {
        // Background
//        g.drawImage(background, bgX, bgY, bgWidth, bgHeight, null);

        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(bgX, bgY, bgWidth, bgHeight);
    }

    private void loadBackground() {
//        background = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
//        bgWidth = (int) (background.getWidth() * Game.SCALE);
//        bgHeight = (int) (background.getHeight() * Game.SCALE);


        bgWidth = (int) (200 * Game.SCALE);
        bgHeight = (int) (300 * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgWidth / 2;
        bgY = Game.GAME_HEIGHT / 2 - bgHeight / 2;

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
