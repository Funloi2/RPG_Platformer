package inputs;

import main.Game;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q -> gamePanel.getPlayer().setLeft(true);
            case KeyEvent.VK_D -> gamePanel.getPlayer().setRight(true);
            case KeyEvent.VK_SPACE -> gamePanel.getPlayer().setJump(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q -> gamePanel.getPlayer().setLeft(false);
            case KeyEvent.VK_D -> gamePanel.getPlayer().setRight(false);
            case KeyEvent.VK_SPACE -> gamePanel.getPlayer().setJump(false);
        }
    }
}