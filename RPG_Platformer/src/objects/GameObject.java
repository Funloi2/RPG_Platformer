package objects;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.*;
import static utilz.Constants.ObjectConstants.*;

public class GameObject {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///
    protected int x;
    protected int y;
    protected int objType;
    protected Rectangle2D.Float hitbox;
    protected boolean doAnimation;
    protected boolean active = true;
    protected int aniTick;
    protected int aniIndex;
    protected int xDrawOffset;
    protected int yDrawOffset;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public GameObject(int x, int y, int objType) {
        this.x = x;
        this.y = y;
        this.objType = objType;
    }


    /// ------------------------------- METHOD ------------------------------- ///
    public void initHitBox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    public void drawHitBox(Graphics g, int xLvlOffset, int yLvlOffset) {
        // for debugging the hitbox
        g.setColor(Color.RED);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y - yLvlOffset, (int) hitbox.width, (int) hitbox.height);
    }

    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= OBJECT_ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(objType)) {
                aniIndex = 0;
                if (objType == CHEST) {
                    doAnimation = false;
                    active = false;
                }
            }
        }
    }

    public void reset() {
        aniIndex = 0;
        aniTick = 0;
        active = true;

        doAnimation = objType != CHEST;
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///
    public int getObjType() {
        return objType;
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

    public void setDoAnimation(boolean doAnimation) {
        this.doAnimation = doAnimation;
    }

    public int getxDrawOffset() {
        return xDrawOffset;
    }

    public int getyDrawOffset() {
        return yDrawOffset;
    }

    public int getAniIndex() {
        return aniIndex;
    }
}
