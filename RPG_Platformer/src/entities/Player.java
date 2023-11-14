package entities;

import gameStates.Playing;
import main.Game;
import utilz.LoadSave;

import static java.lang.Math.round;
import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethod.*;
import static utilz.Constants.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    /// ------------------------------- ATTRIBUTES ------------------------------- ///
    // Sprites
    private BufferedImage[][] animation;
    private BufferedImage image;

    // Action boolean
    private boolean moving = false;
    private boolean attacking = false;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private boolean jump;
    private boolean action;

    // Lvl gestion
    private int[][] lvlData;

    // Draw offset
    private float xDrawOffset = 45 * Game.SCALE;
    private float yDrawOffset = 40 * Game.SCALE;

    // JUMPING AND GRAVITY
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;

    // Status bar UI
    private BufferedImage statusBarImg;

    private int statusBarWidth = (int) (192 * Game.SCALE);
    private int statusBarHeight = (int) (58 * Game.SCALE);
    private int statusBarX = (int) (10 * Game.SCALE);
    private int statusBarY = (int) (10 * Game.SCALE);

    private int healthBarWidth = (int) (150 * Game.SCALE);
    private int healthBarHeight = (int) (4 * Game.SCALE);
    private int healthBarXStart = (int) (34 * Game.SCALE);
    private int healthBarYStart = (int) (14 * Game.SCALE);

    private int healthWidth = healthBarWidth;

    // Flip sprite
    private int flipX = 0;
    private int flipY = 1;

    // Stats

    private int argent = 0;
    private int xp = 0;
    private int level = 1;
    private int maxXp = 100;
    private int attackDamage;

    // TODO: Sort this
    private boolean attackChecked;
    private Playing playing;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Player(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        state = IDLE;
        maxHealth = 100;
        currentHealth = maxHealth;
        attack = 100;
        defense = 2;

        loadAnimation();
        initHitBox(width, height);
        initAttackBox(51, 42);
    }

    /// ------------------------------- METHOD ------------------------------- ///

    private void loadAnimation() {
        animation = new BufferedImage[11][12];

        String[] listPath = new String[11];
        listPath[0] = "/player/_Attack.png";
        listPath[1] = "/player/_Attack2.png";
        listPath[2] = "/player/_AttackCombo.png";
        listPath[3] = "/player/_Death.png";
        listPath[4] = "/player/_Fall.png";
        listPath[5] = "/player/_Hit.png";
        listPath[6] = "/player/_Idle.png";
        listPath[7] = "/player/_Jump.png";
        listPath[8] = "/player/_Roll.png";
        listPath[9] = "/player/_Run.png";
        listPath[10] = "/player/_TurnAround.png";

        for (int j = 0; j < animation.length; j++) {
            image = LoadSave.GetSpriteAtlas(listPath[j]);
            for (int i = 0; i < animation[j].length; i++) {
                if (i < GetSpriteAmount(j))
                    animation[j][i] = image.getSubimage(i * 120, 0, 120, 80);
            }
        }
    }

    public void update() {
        updateHealthBar();

        if (currentHealth <= 0) {
//            playing.setGameOver(true);
            return;
        }
        updateAttackBox();

        updatePos();

        if (action) {
            checkSpeakToAtlas();
        }
        if (attacking) {
            checkAttack();
        }

        updateAnimationTick();
        setAnimation();
    }

    private void checkAttack() {
        if (attackChecked || aniIndex != 2) {
            return;
        }
        attackChecked = true;
        playing.checkEnemyHit(attackBox);
        playing.checkObjectHit(attackBox);

    }

    private void updateAttackBox() {
        if (right) {
            attackBox.x = hitBox.x + hitBox.width /*+ (int) (Game.SCALE * 20)*/;
        } else if (left) {
            attackBox.x = hitBox.x - attackBox.width /*- (int) (Game.SCALE * 20)*/;
        }
        attackBox.y = hitBox.y - (Game.SCALE * 5);
    }

    private void updateHealthBar() {
        healthWidth = (int) ((currentHealth / (float) (maxHealth)) * healthBarWidth);
    }


    private void setAnimation() {
        int startAni = state;

        if (moving) {
            state = RUN;
        } else {
            state = IDLE;
        }

        if (inAir) {
            if (airSpeed < 0) {
                state = JUMP;
            }
        }

        if (attacking) {
            state = ATTACK;
            if (startAni != ATTACK) {
                aniIndex = 0;
                aniTick = 0;
                return;
            }
        }

        if (startAni != state) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= PLAYER_ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(state)) {
                aniIndex = 0;
                attacking = false;
                attackChecked = false;
            }
        }
    }

    private void checkSpeakToAtlas() {
        playing.checkSpeakToAtlas();
    }

    public void render(Graphics g, int xLvlOffset, int yLvlOffset) {

        drawHitBox(g, xLvlOffset, yLvlOffset);
        drawAttackBox(g, xLvlOffset, yLvlOffset);

        g.drawImage(animation[state][aniIndex],
                (int) (hitBox.x - xLvlOffset - xDrawOffset + flipX),
                (int) (hitBox.y - yLvlOffset - yDrawOffset),
                (int) (120 * flipY * Game.SCALE), (int) (80 * Game.SCALE), null);
        drawUI(g);
    }

    private void drawUI(Graphics g) {
//        g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        g.setColor(Color.RED);
        g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
    }

    private void updatePos() {
        moving = false;

        if (jump) {
            jump();
        }

        if (!inAir) {
            if ((!left && !right) || (right && left)) {
                return;
            }
        }

        float xSpeed = 0;

//        if (up) {
//            hitBox.y -= 5;
//        }
//        if (down) {
//            hitBox.y += 5;
//        }
        if (left) {
            xSpeed -= walkSpeed;
            flipX = (int) (xDrawOffset * 2 + (120 * Game.SCALE - 2 * xDrawOffset - 18));
            flipY = -1;
        }
        if (right) {
            xSpeed += walkSpeed;
            flipX = 0;
            flipY = 1;
        }


        if (!inAir) {
            inAir = !IsEntityOnFloor(hitBox, lvlData);
        }

        if (inAir) {
            if (CanMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, lvlData)) {
                hitBox.y += airSpeed;
                airSpeed += GRAVITY;
                updateXPos(xSpeed);
            } else {
                hitBox.y = GetEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
                if (airSpeed > 0) {
                    resetInAIr();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        }

        moving = true;

    }

    private void jump() {
        if (inAir) {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }


    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!IsEntityOnFloor(hitBox, lvlData)) {
            inAir = true;
        }
    }


    private void resetInAIr() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData)) {
            hitBox.x += xSpeed;
        } else {
            hitBox.x = GetEntityXPosNextToWall(hitBox, xSpeed);
        }
    }



    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void updateXp(int amount){
        xp += amount;
        if (xp >= maxXp) {
            level++;
            xp = 0;
            maxXp = round(maxXp * 1.5f);
            attack += 5;
            defense += 2;
            maxHealth += 10;
            currentHealth = maxHealth;
            System.out.println( "Level up! You are now level " + level + "!");
            System.out.println("You have " + xp + " xp and need " + (maxXp - xp) + " xp to level up again!");
            System.out.println("You have " + attack + " attack damage and " + defense + " defense!");
            System.out.println("You have " + maxHealth + " health!");
        }
    }

    /// ------------------------------- GETTER AND SETTER ------------------------------- ///


    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent += argent;}


    public int getLevelInt() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getMaxXp() {
        return maxXp;
    }



}
