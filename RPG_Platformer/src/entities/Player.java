package entities;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    /// ------------------------------- ATTRIBUTES ------------------------------- ///
//    // Sprites
//    private BufferedImage[][] animation;
//    private BufferedImage image;

    // Action boolean
    // private boolean moving = false;
    // private boolean attacking = false;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    // private boolean jump;
    // private boolean action;

//    // Lvl gestion
//    private int[][] lvlData;
//    private float xDrawOffset = 24 * Game.SCALE;
//    private float yDrawOffset = 25 * Game.SCALE;
//
//    // JUMPING AND GRAVITY
//    private float jumpSpeed = -2.25f * Game.SCALE;
//    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
//
//    // Status bar UI
//    private BufferedImage statusBarImg;
//
//    private int statusBarWidth = (int) (192 * Game.SCALE);
//    private int statusBarHeight = (int) (58 * Game.SCALE);
//    private int statusBarX = (int) (10 * Game.SCALE);
//    private int statusBarY = (int) (10 * Game.SCALE);
//
//    private int healthBarWidth = (int) (150 * Game.SCALE);
//    private int healthBarHeight = (int) (4 * Game.SCALE);
//    private int healthBarXStart = (int) (34 * Game.SCALE);
//    private int healthBarYStart = (int) (14 * Game.SCALE);
//
//    private int healthWidth = healthBarWidth;
//
//    // Flip sprite
//    private int flipX = 0;
//    private int flipY = 1;
//
//    // TODO: Sort this
//    private boolean attackChecked;
//    private Playing playing;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    /// ------------------------------- METHOD ------------------------------- ///

    public void update() {
        updatePos();
    }

    private void updatePos() {

        if (up) {
            y -= 5;
        }
        if (down) {
            y += 5;
        }
        if (left) {
            x -= 5;
        }
        if (right) {
            x += 5;
        }
    }

    public void render(Graphics g) {
        g.fillRect((int) x, (int) y, width, height);
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    /// ------------------------------- GETTER AND SETTER ------------------------------- ///


    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
