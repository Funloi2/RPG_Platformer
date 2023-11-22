package entities;

import main.Game;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Direction.*;

public class Goblin extends Enemy {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public Goblin(float x, float y) {
        super(x, y, GOBLIN_WIDTH, GOBLIN_HEIGHT, GOBLIN);
        initHitBox(20, 39);
        initAttackBox(90, 35);
        defense = 2;
        maxHealth = 20;
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
            attackBox.x = hitBox.x - (int) (Game.SCALE * 35);
        } else if (walkDir == LEFT) {
            attackBox.x = hitBox.x - (int) (attackBox.width - (Game.SCALE * 35) - hitBox.width);
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
                    if (aniIndex == 0) {
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

            return width;
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
