package objects;

import main.Game;
import utilz.LoadSave;

import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import static utilz.Constants.PLAYER_ANI_SPEED;
import static utilz.Constants.PlayerConstants.GetSpriteAmount;

public class Altar {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    // Sprite
    private BufferedImage[] altarSprite;

    // Position and size
    private int x, y;
    private int width, height;
    private Rectangle2D.Float hitBox;
    private int yDrawOffset = 160;


    // Animation
    protected int aniIndex;
    protected int aniTick;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public Altar(int x, int y) {
        this.x = x;
        this.y = y;
        width = 150;
        height = 300;

        initHitBox(100, 100);
        loadAltarSprite();
    }
    /// ------------------------------- METHOD ------------------------------- ///

    protected void initHitBox(int width, int height) {
        hitBox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    public void loadAltarSprite() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.ALTAR_SPRITE);

        altarSprite = new BufferedImage[13];

        for (int i = 0; i < altarSprite.length; i++) {
            altarSprite[i] = temp.getSubimage(i * 200, 0, 200, 400);
        }
    }

    public void update() {
        updateAnimationTick();
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= PLAYER_ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= altarSprite.length) {
                aniIndex = 0;
            }
        }
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
//        g.setColor(Color.RED);
//        drawHitbox(g, xLvlOffset, yLvlOffset);

        g.drawImage(altarSprite[aniIndex], x - xLvlOffset, y - yLvlOffset - yDrawOffset, width, height, null);


    }

    private void drawHitbox(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.drawRect((int) (hitBox.x - xLvlOffset), (int) (hitBox.y - yLvlOffset), (int) hitBox.width, (int) hitBox.height);
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }
}
