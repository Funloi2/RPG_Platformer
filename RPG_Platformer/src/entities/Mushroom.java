package entities;

import main.Game;

import static utilz.Constants.Direction.LEFT;
import static utilz.Constants.Direction.RIGHT;
import static utilz.Constants.EnemyConstants.*;

public class Mushroom extends Enemy {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public Mushroom(float x, float y) {
        super(x, y, MUSHROOM_WIDTH, MUSHROOM_HEIGHT, MUSHROOM);
        initHitBox(20, 39);
        initAttackBox(90, 35);

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
            attackBox.x = hitBox.x - attackBox.width + (int) (Game.SCALE * 20);
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
                    if (canSeePlayer(lvlData, player)) {
                        turnTowardsPlayer(player);

                        if (isPlayerCloseForAttack(player)) {
                            newState(ATTACK);
                        }
                    }
                    move(lvlData);
                }
                case ATTACK -> {
                    if (aniIndex == 0) {
                        attackChecked = false;
                    }

                    if (aniIndex == 6 && !attackChecked) {
                        checkEnemyHit(attackBox, player);
                    }
                }
                case HURT -> {

                }
            }
        }
    }




}
