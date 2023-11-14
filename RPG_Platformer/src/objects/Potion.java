package objects;

import main.Game;

public class Potion extends GameObject {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///
    private float hoverOffset;
    private int maxHoverOffset;
    private int hoverDir = 1;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Potion(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        initHitBox(7, 14);
        xDrawOffset = (int) (3 * Game.SCALE);
        yDrawOffset = (int) (2 * Game.SCALE);

        maxHoverOffset = (int) (10 * Game.SCALE);
    }

    /// ------------------------------- METHOD ------------------------------- ///
    public void update() {
        updateAnimationTick();
        updateHover();
    }

    private void updateHover() {
        hoverOffset += (0.075f * Game.SCALE * hoverDir);

        if (hoverOffset >= maxHoverOffset) {
            hoverDir = -1;
        } else if (hoverOffset < 0) {
            hoverDir = 1;
        }

        hitbox.y = y + hoverOffset;

    }

    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

}
