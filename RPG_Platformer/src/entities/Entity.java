package entities;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Entity {
    /// ------------------------------- ATTRIBUTES ------------------------------- ///

    // Size and position
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitBox;


    //    // Animation
//    protected int aniIndex;
//    protected int aniTick;
//    protected int state;
//
    // In air and movement
    protected float airSpeed;
    protected boolean inAir = false;
    protected float walkSpeed = 1.5f;
//
//    //Health
//    protected int maxHealth;
//    protected int currentHealth;
//
//    // AttackBox
//    protected Rectangle2D.Float attackBox;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /// ------------------------------- METHOD ------------------------------- ///

    protected void initHitBox(int width, int height) {
        hitBox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    protected void drawHitBox(Graphics g, int xLVlOffset, int yLvlOffset) {
        // for debugging the hitbox
        g.setColor(Color.RED);
        g.drawRect((int) hitBox.x - xLVlOffset, (int) hitBox.y - yLvlOffset, (int) hitBox.width, (int) hitBox.height);
    }

    /// ------------------------------- GETTER AND SETTER ------------------------------- ///


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }
}
