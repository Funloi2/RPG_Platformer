package objects;

import main.Game;
import objects.GameObject;

public class Chest extends GameObject {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Chest(int x, int y, int objType) {
        super(x, y, objType);
        createHitbox();
    }

    /// ------------------------------- METHOD ------------------------------- ///
    private void createHitbox() {
        initHitBox(25, 18);

        xDrawOffset = (int) (7 * Game.SCALE);
        yDrawOffset = (int) (12 * Game.SCALE);

        hitbox.y += yDrawOffset + (int) (Game.SCALE * 2);
        hitbox.x += xDrawOffset / 2;
    }

    public void update() {
        updateAnimationTick();
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

}
