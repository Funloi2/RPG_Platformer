package objects;

import main.Game;

import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Altar {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    // Sprite
    private BufferedImage altarSprite;

    // Position and size
    private int x, y;
    private int width, height;
    private Rectangle2D.Float hitBox;
//    private int xDrawOffset;
//    private int yDrawOffset;


    // Animation
//    protected int aniIndex;
//    protected int aniTick;
//    protected int state;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public Altar(int x, int y) {
        this.x = x;
        this.y = y;

        initHitBox(100, 100);
    }
    /// ------------------------------- METHOD ------------------------------- ///

    protected void initHitBox(int width, int height) {
        hitBox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    public void update() {

    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.setColor(Color.RED);
        drawHitbox(g, xLvlOffset, yLvlOffset);
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
