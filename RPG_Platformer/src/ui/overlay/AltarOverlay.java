package ui.overlay;

import entities.Enemy;
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
    private Rectangle hitbox1;
    // Background sprite
    private BufferedImage background;

    private boolean isRespawnPressed = false;
    private boolean isRespawnReleased = false;

    private boolean isDropPressed = false;
    private boolean isDropReleased = false;

    private boolean isGoldPressed = false;
    private boolean isGoldReleased = false;
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
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        g.setColor(Color.RED);
        hitbox1 = new Rectangle(450, 125, 350, 50);
        g.drawRect( 450 , 125, 350, 50);
    }



    //////////// Event handlers ////////////

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (hitbox1.contains(e.getX(), e.getY())) {
            isRespawnPressed = true;
        }

    }

    public void mouseReleased(MouseEvent e) {
        if (hitbox1.contains(e.getX(), e.getY()) && isRespawnPressed) {
            System.out.println("Released");
            isRespawnReleased = true;
            playing.resetAll();

            isRespawnReleased = false;
            isRespawnPressed = false;
        }

//        if(isRespawnReleased) {
//            playing.getObjectManager().resetObjects();
//            isRespawnReleased = false;
//            isRespawnPressed = false;
//        }

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

}
