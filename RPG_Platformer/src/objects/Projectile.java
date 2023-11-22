package objects;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Projectiles.*;

public class Projectile {

    private Rectangle2D.Float hitbox;
    private int dir;
    private boolean active = true;

    public Projectile(int x, int y, int dir) {
        hitbox = new Rectangle2D.Float(x, y, VFX1_WIDTH, VFX1_HEIGHT);
        this.dir = dir;
    }

    public void update() {
        hitbox.x += dir * SPEED;
    }

    public void setPos(int x, int y) {
        hitbox.x = x;
        hitbox.y = y;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
