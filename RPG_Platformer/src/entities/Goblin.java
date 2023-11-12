package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Direction.*;

public class Goblin extends Enemy {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public Goblin(float x, float y) {
        super(x, y, GOBLIN_WIDTH, GOBLIN_HEIGHT, GOBLIN);
        initHitBox(20, 39);


    }

    /// ------------------------------- METHOD ------------------------------- ///

    public void update(int[][] lvlData, Player player) {
        updateBehavior(lvlData, player);
        updateAnimationTick();
    }

    private void updateBehavior(int[][] lvlData, Player player) {
        if (firstUpdate) {
            firstUpdateCheck(lvlData);
        }
        if (inAir) {
            updateInAIr(lvlData);
        } else {
            switch (state) {
                case IDLE -> {newState(RUN);}
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
//                    if (aniIndex == 0) {
//                        attackChecked = false;
//                    }
//
//                    if (aniIndex == 3 && !attackChecked) {
//                        checkEnemyHit(attackBox, player);
//                    }
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
