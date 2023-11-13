package entities;

import main.Game;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.*;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Direction.*;
import static utilz.HelpMethod.*;

public class Enemy extends Entity {

    /// ------------------------------- ATTRIBUTE ------------------------------- ///
    protected int enemyType;
    protected boolean firstUpdate = true;
    protected int walkDir = LEFT;
    protected int tileY;
    protected float attackDistance = Game.TILES_SIZE;
    protected boolean active = true;
    protected boolean attackChecked;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        this.walkSpeed = 0.35f * Game.SCALE;
        maxHealth = 15;
        currentHealth = maxHealth;

        initHitBox(width, height);
    }

    /// ------------------------------- METHOD ------------------------------- ///


    protected void firstUpdateCheck(int[][] lvlData) {
        firstUpdate = false;
        newState(IDLE);
        if (!IsEntityOnFloor(hitBox, lvlData)) {
            inAir = true;
        }
    }

    protected void updateInAIr(int[][] lvlData) {
        if (CanMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, lvlData)) {
            hitBox.y += airSpeed;
            airSpeed += GRAVITY;
        } else {
            inAir = false;
            hitBox.y = GetEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
            tileY = (int) (hitBox.y / Game.TILES_SIZE);
        }
    }

    protected void move(int[][] lvlData) {
        float xSpeed = 0;

        if (walkDir == LEFT) {
            xSpeed = -walkSpeed;
        } else {
            xSpeed = walkSpeed;
        }

        if (CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData)) {
            if (IsFloor(hitBox, xSpeed, lvlData, walkDir)) {
                hitBox.x += xSpeed;
                return;
            }
        }
        changeWalkDir();
    }

    protected void changeWalkDir() {
        if (walkDir == LEFT) {
            walkDir = RIGHT;
        } else {
            walkDir = LEFT;
        }
    }

    protected void turnTowardsPlayer(Player player) {
        if (player.hitBox.x > hitBox.x) {
            walkDir = RIGHT;
        } else {
            walkDir = LEFT;
        }
    }

    protected boolean canSeePlayer(int[][] lvlData, Player player) {
        int playerTileY = (int) (player.getHitBox().y / Game.TILES_SIZE);
        if (playerTileY == tileY) {
            if (isPlayerInRange(player)) {
                if (IsSightClear(lvlData, hitBox, player.getHitBox(), tileY)) {
                    return true;
                }
            }
        }
        return false;
    }


    protected boolean isPlayerInRange(Player player) {
        int absValue = (int) Math.abs(player.hitBox.x - hitBox.x);
        return absValue <= attackDistance * 5;
    }

    protected boolean isPlayerCloseForAttack(Player player) {
        int absValue = (int) Math.abs(player.hitBox.x - hitBox.x);
        return absValue <= attackDistance;
    }

    protected void newState(int enemyState) {
        this.state = enemyState;
        aniTick = 0;
        aniIndex = 0;
    }

    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ENEMY_ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(enemyType, state)) {
                aniIndex = 0;

                switch (state) {
                    case ATTACK, HURT -> state = IDLE;
                    case DEATH -> active = false;
                }
            }
        }
    }

    protected void checkEnemyHit(Rectangle2D.Float attackBox, Player player) {
        if (attackBox.intersects(player.getHitBox())) {
            player.changeHealth(-GetEnemyDmg(enemyType));
        }
        attackChecked = true;
    }

    public void hurt(int amount) {
        currentHealth -= amount;
        if (currentHealth <= 0) {
            newState(DEATH);
        } else {
            newState(HURT);
            aniIndex = 1;
        }
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

    public boolean isActive() {
        return active;
    }
}
