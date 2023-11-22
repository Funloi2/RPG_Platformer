package entities;

import main.Game;

import static main.Game.GAME_HEIGHT;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Direction.*;

public class NightBorne extends Enemy {

    public NightBorne(float x, float y) {
        super(x, y, 80, 80, NIGHTBORNE);
        initHitBox(60, 80);
        initAttackBox(90, 35);
        defense = 50;
        maxHealth = 500;
        currentHealth = maxHealth;
        walkDir = LEFT;
        walkSpeed = 1.9f;
        attackDistance = (int) (1.5 * Game.TILES_SIZE);
    }

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
//                    newState(RUN);
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
                    if (aniIndex == 6 && !attackChecked) {
                        checkEnemyHit(attackBox, player);
                    }
                }
            }
        }
    }

    @Override
    protected boolean canSeePlayer(int[][] lvlData, Player player) {
        if (playing.getDownBorder() == GAME_HEIGHT - 2 * Game.TILES_SIZE) {
            return true;
        }

        return false;
    }

    public int flipX() {
        if (walkDir == RIGHT) {

            return 0;
        } else {

            return (int) (NIGHTBORNE_WIDTH + 5 * Game.SCALE);
        }
    }

    public int flipY() {
        if (walkDir == RIGHT) {
            return 1;
        } else {
            return -1;
        }
    }
}

