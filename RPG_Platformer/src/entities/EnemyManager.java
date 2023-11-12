package entities;

import gameStates.Playing;
import utilz.HelpMethod;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {
    /// ------------------------------- ATTRIBUTES ------------------------------- ///
    private Playing playing;
    private BufferedImage[][] goblinArr;
    private ArrayList<Goblin> goblins = new ArrayList<>();

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs();
        addEnemies();
    }

    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

    /// ------------------------------- METHOD ------------------------------- ///

    private void addEnemies() {
        goblins = HelpMethod.GetGoblins();
    }

    public void update(int[][] lvlData, Player player) {
        for (Goblin gob : goblins) {
            if (gob.isActive())
                if (gob.isActive())
                    gob.update(lvlData, player);
        }
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        drawGoblins(g, xLvlOffset, yLvlOffset);
    }

    private void drawGoblins(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Goblin gob : goblins) {
            if (gob.isActive()) {
                g.drawImage(goblinArr[gob.getState()][gob.getAniIndex()],
                        (int) (gob.getHitBox().x - xLvlOffset - GOBLIN_DRAWOFFSET_X) + gob.flipX(),
                        (int) (gob.getHitBox().y - yLvlOffset - GOBLIN_DRAWOFFSET_Y)
                        , GOBLIN_WIDTH * gob.flipY(), GOBLIN_HEIGHT, null);
                gob.drawHitBox(g, xLvlOffset, yLvlOffset);
                gob.drawAttackBox(g, xLvlOffset, yLvlOffset);
            }
        }
    }

    private void loadEnemyImgs() {
        goblinArr = new BufferedImage[6][12];
        BufferedImage temp;

        String[] listPath = new String[10];
        listPath[ATTACK] = "/ennemies/Goblin/Attack.png";
        listPath[ATTACK_2] = "/ennemies/Goblin/Attack2.png";
        listPath[ATTACK_3] = "/ennemies/Goblin/Attack3.png";
        listPath[DEATH] = "/ennemies/Goblin/Death.png";
        listPath[IDLE] = "/ennemies/Goblin/Idle.png";
        listPath[RUN] = "/ennemies/Goblin/Run.png";
        listPath[HURT] = "/ennemies/Goblin/Take_Hit.png";

        for (int j = 0; j < goblinArr.length; j++) {
            temp = LoadSave.GetSpriteAtlas(listPath[j]);
            for (int i = 0; i < goblinArr[j].length; i++) {
                {
                    if (i < GetSpriteAmount(GOBLIN, j)) {
                        goblinArr[j][i] = temp.getSubimage(i * GOBLIN_WIDTH_DEFAULT, 0, GOBLIN_WIDTH_DEFAULT, GOBLIN_HEIGHT_DEFAULT);
                    }
                }
            }
        }


    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for (Goblin gob : goblins) {
            if (gob.isActive())
                if (attackBox.intersects(gob.getHitBox())) {
                    gob.hurt(10);
                    return;
                }
        }
    }


//    public void resetAllEnemies() {
//        for (Tengu ten : tengus) {
//            ten.resetEnemy();
//        }
//    }
}
