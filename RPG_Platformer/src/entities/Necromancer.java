package entities;

import main.Game;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Direction.*;
import static utilz.HelpMethod.IsSightClear;

public class Necromancer extends Enemy {
    public Necromancer(float x, float y) {
        super(x, y, NECROMANCER_WIDTH, NECROMANCER_HEIGHT, NECROMANCER);
        initHitBox(38, 80);
        initAttackBox(80, 35);
        defense = 50;
        maxHealth = 500;
        currentHealth = maxHealth;
        walkDir = LEFT;
        attackDistance = Game.TILES_SIZE * 5;
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
                    newState(RUN);
                }
                case RUN -> {
                    if (canSeePlayer(lvlData, player)) {
                        turnTowardsPlayer(player);

                        if (isPlayerCloseForAttack(player) && player.getCurrentHealth() > 0) {
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
            }
        }
    }

    @Override
    protected boolean canSeePlayer(int[][] lvlData, Player player) {
        if (isPlayerInRange(player)) {
            return true;
        }

        return false;
    }

    public int flipX() {
        if (walkDir == RIGHT) {

            return 0;
        } else {

            return (int) (NECROMANCER_WIDTH + NECROMANCER_DRAWOFFSET_X + 20 * Game.SCALE);
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
