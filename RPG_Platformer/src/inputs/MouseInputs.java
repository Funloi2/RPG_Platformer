package inputs;

import gameStates.GameState;
import main.Game;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state) {
            case PLAYING -> {
                gamePanel.getGame().getPlaying().mouseClicked(e);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case PLAYING -> {
                if (gamePanel.getGame().getPlaying().isInventory()) {
                    gamePanel.getGame().getPlaying().getInventoryOverlay().mousePressed(e);
                } else if (gamePanel.getGame().getPlaying().isAltar()) {
                    gamePanel.getGame().getPlaying().getAltarOverlay().mousePressed(e);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case PLAYING -> {
                if (gamePanel.getGame().getPlaying().isInventory()) {
                    gamePanel.getGame().getPlaying().getInventoryOverlay().mouseReleased(e);
                } else if (gamePanel.getGame().getPlaying().isAltar()) {
                    gamePanel.getGame().getPlaying().getAltarOverlay().mouseReleased(e);
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (GameState.state) {
            case PLAYING -> {
                if (gamePanel.getGame().getPlaying().isInventory()) {
                    gamePanel.getGame().getPlaying().getInventoryOverlay().mouseDragged(e);
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case PLAYING -> {
                if (gamePanel.getGame().getPlaying().isInventory()) {
                    gamePanel.getGame().getPlaying().getInventoryOverlay().mouseMoved(e);
                }
            }
        }
    }
}