package entities;

import main.Game;

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
//    // In air and movement
//    protected float airSpeed;
//    protected boolean inAir = false;
    protected float walkSpeed = 1.0f;
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
