package entities;

import main.Game;

import static utilz.Constants.Direction.LEFT;
import static utilz.Constants.Direction.RIGHT;
import static utilz.Constants.EnemyConstants.*;

public class FlyingEye extends Enemy {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public FlyingEye(float x, float y) {
        super(x, y, FLYING_EYE_WIDTH, FLYING_EYE_HEIGHT, FLYING_EYE);
        initHitBox(33, 39);
        initAttackBox(15, 20);
        defense = 2;
        maxHealth = 15;
        currentHealth = maxHealth;

    }

    /// ------------------------------- METHOD ------------------------------- ///

    public void update(int[][] lvlData, Player player) {
        updateBehavior(lvlData, player);
        updateAnimationTick();
        updateAttackBox();
    }

    private void updateAttackBox() {
        if (walkDir == RIGHT) {
            attackBox.x = hitBox.x + hitBox.width - (Game.SCALE * 15);
        } else if (walkDir == LEFT) {
            attackBox.x = hitBox.x;
        }
        attackBox.y = hitBox.y + (Game.SCALE * 4);
    }

    private void updateBehavior(int[][] lvlData, Player player) {
        if (firstUpdate) {
            firstUpdateCheck(lvlData);
        }
        if (inAir) {
            updateInAIr(lvlData);
        } else {
            switch (state) {
                case IDLE -> {
                    newState(RUN);
                }
                case RUN -> {
                    if (player.getCurrentHealth() > 0) {
                        if (canSeePlayer(lvlData, player)) {
                            turnTowardsPlayer(player);

                            if (isPlayerCloseForAttack(player)) {
                                newState(ATTACK);
                            }
                        }
                    }
                    move(lvlData);
                }
                case ATTACK -> {
                    if (aniIndex == 4) {
                        attackChecked = false;
                    }
                    // Frame où on check les dmg
                    if (aniIndex == 6 && !attackChecked) {
                        checkEnemyHit(attackBox, player);
                    }
                }
                case HURT -> {

                }
            }
        }
    }

    public int flipX() {
        if (walkDir == RIGHT) {

            return 0;
        } else {

            return width + (int) (Game.SCALE * 4);
        }
    }

    public int flipY() {
        if (walkDir == RIGHT) {
            return 1;
        } else {
            return -1;
        }
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///
}
