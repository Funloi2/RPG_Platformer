package ui.overlay;

import gameStates.Playing;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class InventoryOverlay {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

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
    public InventoryOverlay(Playing playing) {
        this.playing = playing;

//        loadBackground();
    }

    /// ------------------------------- METHOD ------------------------------- ///

    public void update() {
    }

    public void draw(Graphics g) {
        // Background
//        g.drawImage(background, bgX, bgY, bgWidth, bgHeight, null);

        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(113, 61, 1020, 550);

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
