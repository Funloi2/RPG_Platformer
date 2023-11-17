package entities;

import gameStates.Playing;
import main.Game;
import objects.equipment.*;

import java.awt.geom.Rectangle2D;
import java.util.Random;

import static java.lang.Math.random;
import static java.lang.Math.round;
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
    private Playing playing;
    private int enemyLevel = 1;
    private int enemyDropRate = 1;
    private int enemyDropRateLevel = 1;
    private Random random = new Random();


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
            player.changeHealth(-(getEnemyDmg(enemyType)) / player.getDefense());
        }
        attackChecked = true;
    }

    public void hurt(int amount) {
        currentHealth -= (amount / defense);
        if (currentHealth <= 0) {
            enemyDeath();
        } else {
            newState(HURT);
            aniIndex = 1;
        }
    }

    public void enemyDeath() {
        newState(DEATH);
        playing.getPlayer().updateXp((int) round(20 + enemyLevel * 1.5));
        playing.getPlayer().updateGold((int) round(10 + enemyLevel * 1.5));
        drop();
    }

    private void drop() {
        double drop = random();
        if (drop < 0.30) {
            System.out.println("drop");
            drop = random();
            if (drop < 0.70) {
                dropArmor();
            } else {
                dropWeapon();
            }
        }
    }

    private void dropWeapon() {
        double drop = random.nextDouble();
        if (drop < 0.70) {
            System.out.println("t1");
            playing.getObjectManager().dropItem(new Sword((int) (x), (int) (y + hitBox.height), 0, enemyLevel));
        } else if (drop < 0.90) {
            System.out.println("t2");
            playing.getObjectManager().dropItem(new Sword((int) (x), (int) (y + hitBox.height), 1, enemyLevel));
        } else if (drop < 0.98) {
            System.out.println("t3");
            playing.getObjectManager().dropItem(new Sword((int) (x), (int) (y + hitBox.height), 2, enemyLevel));
        } else if (drop < 0.99) {
            System.out.println("TerraPrisma");
            playing.getObjectManager().dropItem(new Sword((int) (x), (int) (y + hitBox.height), 3, enemyLevel));
        } else {
            double t5DropChance = 0.0000001 / 100;
            if (drop < t5DropChance) {
                System.out.println("God Slayer");
                playing.getObjectManager().dropItem(new Sword((int) (x), (int) (y + hitBox.height), 4, enemyLevel));
            }
        }
    }

    private void dropArmor() {
        double dropItemType = random.nextDouble();
        System.out.println("drop armor");
        if (dropItemType < .25)
            dropArmorSet(0);
        else if (dropItemType < .50)
            dropArmorSet(1);
        else if (dropItemType < .75)
            dropArmorSet(2);
        else
            dropArmorSet(3);

    }

    private void dropArmorSet(int set) {
        double dropItemSet = random.nextDouble();
        if (dropItemSet < .25)
            playing.getObjectManager().dropItem(new Helmet((int) (x), (int) (y + hitBox.height), set, enemyLevel));
        else if (dropItemSet < .50)
            playing.getObjectManager().dropItem(new Chestplate((int) (x), (int) (y + hitBox.height), set, enemyLevel));
        else if (dropItemSet < .75)
            playing.getObjectManager().dropItem(new Pants((int) (x), (int) (y + hitBox.height), set, enemyLevel));
        else
            playing.getObjectManager().dropItem(new Boots((int) (x), (int) (y + hitBox.height), set, enemyLevel));
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

    public boolean isActive() {
        return active;
    }

    public void setPlaying(Playing playing) {
        this.playing = playing;
    }
}
