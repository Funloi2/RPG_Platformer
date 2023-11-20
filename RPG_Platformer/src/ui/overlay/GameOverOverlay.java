package ui.overlay;

import gameStates.Playing;
import main.Game;
import ui.button.RespawnButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class GameOverOverlay {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    private RespawnButton respawnButton;
    private BufferedImage backgroundImage;
    private Playing playing;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public GameOverOverlay(Playing playing) {
        this.playing = playing;
        backgroundImage = LoadSave.GetSpriteAtlas(LoadSave.GAME_OVER_BG);
        respawnButton = new RespawnButton();
    }

    /// ------------------------------- METHOD ------------------------------- ///

    public void draw(Graphics g) {
        // Background
        g.drawImage(backgroundImage, Game.GAME_WIDTH / 2 - backgroundImage.getWidth() / 2, Game.GAME_HEIGHT / 2 - backgroundImage.getHeight() / 2, backgroundImage.getWidth(), backgroundImage.getHeight(), null);

        // Respawn button
        respawnButton.draw(g);
    }


    //////////// Event handlers ////////////

    public void mousePressed(MouseEvent e) {
        respawnButton.setMousePressed(false);
        if (respawnButton.getHitBox().contains(e.getX(), e.getY())) {
            respawnButton.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        respawnButton.setMouseReleased(false);
        if (respawnButton.isMousePressed()) {
            respawnButton.setMouseReleased(true);
//            playing.resetAll();
            playing.getPlayer().respawn();
        }

        respawnButton.setMousePressed(false);
    }

    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

}
