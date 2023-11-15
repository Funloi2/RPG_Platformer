package ui.overlay;

import gameStates.Playing;
import main.Game;
import objects.GameObject;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class InventoryOverlay {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    // Access to playing method
    private final Playing playing;

    // Position and size
    private int bgX;
    private int bgY;
    private int bgWidth;
    private int bgHeight;

    // Background sprite
    private BufferedImage background;

    // Hit boxes list
    private Rectangle2D.Float[] armorSlots;
    private Rectangle2D.Float weaponSlot;
    private Rectangle2D.Float[] inventorySlots;

    // potion and currency counter
    private int nbLifePotions = 0;
    private int nbSTMPotions = 0;
    private int nbCurrency = 0;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public InventoryOverlay(Playing playing) {
        this.playing = playing;

        loadBackground();
        initHitBoxes();
    }


    /// ------------------------------- METHOD ------------------------------- ///

    public void addLifePotion() {
        nbLifePotions++;
    }

    public void addSTMPotion() {
        nbSTMPotions++;
    }

    public void addCurrency(int currencyAdded) {
        nbCurrency += currencyAdded;
    }

    private void initHitBoxes() {
        armorSlots = new Rectangle2D.Float[4];

        for (int i = 0; i < armorSlots.length; i++) {
            armorSlots[i] = new Rectangle2D.Float(199, 204 + i * 56, 37, 37);
        }

        weaponSlot = new Rectangle2D.Float(199, 204 + armorSlots.length * 56, 37, 37);

        inventorySlots = new Rectangle2D.Float[21];

        for (int i = 0; i < inventorySlots.length; i++) {
            inventorySlots[i] = new Rectangle2D.Float((int) (534 + (i % 7) * 73.8), 279 + (i / 7) * 61, 44, 44);
        }
    }

    private void loadBackground() {
        background = LoadSave.GetSpriteAtlas(LoadSave.INVENTORY);

        bgWidth = (int) (background.getWidth() * 0.8);
        bgHeight = (int) (background.getHeight() * 0.8);

        bgX = Game.GAME_WIDTH / 2 - bgWidth / 2;
        bgY = Game.GAME_HEIGHT / 2 - bgHeight / 2;
    }

    public void update() {
    }

    public void draw(Graphics g) {
        // Background
        g.drawImage(background, bgX, bgY, bgWidth, bgHeight, null);


        // Hit boxes
        g.setColor(Color.RED);
        for (Rectangle2D.Float armor : armorSlots) {
            g.drawRect((int) armor.x, (int) armor.y, (int) armor.width, (int) armor.height);
        }
        g.drawRect((int) weaponSlot.x, (int) weaponSlot.y, (int) weaponSlot.width, (int) weaponSlot.height);

        for (Rectangle2D.Float item : inventorySlots) {
            g.drawRect((int) item.x, (int) item.y, (int) item.width, (int) item.height);
        }

        // Currency and potions amount
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(playing.getPlayer().getArgent()), 540, 225);
        g.drawString(String.valueOf(playing.getPlayer().getNbLifePotions()), 923, 225);
        g.drawString(String.valueOf(playing.getPlayer().getNbSTMPotions()), 1005, 225);

        // Stats
        if (playing.getPlayer().getMaxHealth() / 10 < 1)
            g.drawString(String.valueOf(playing.getPlayer().getMaxHealth()), 294, 458);
        else if (playing.getPlayer().getMaxHealth() / 10 < 10)
            g.drawString(String.valueOf(playing.getPlayer().getMaxHealth()), 291, 458);
        else
            g.drawString(String.valueOf(playing.getPlayer().getMaxHealth()), 288, 458);

        if (playing.getPlayer().getStamina() / 10 < 1)
            g.drawString(String.valueOf(playing.getPlayer().getStamina()), 324, 458);
        else if (playing.getPlayer().getStamina() / 10 < 10)
            g.drawString(String.valueOf(playing.getPlayer().getStamina()), 321, 458);
        else
            g.drawString(String.valueOf(playing.getPlayer().getStamina()), 318, 458);

        if (playing.getPlayer().getAttackDamage() / 10 < 1)
            g.drawString(String.valueOf(playing.getPlayer().getAttackDamage()), 358, 458);
        else if (playing.getPlayer().getAttackDamage() / 10 < 10)
            g.drawString(String.valueOf(playing.getPlayer().getAttackDamage()), 355, 458);
        else
            g.drawString(String.valueOf(playing.getPlayer().getAttackDamage()), 352, 458);

        if (playing.getPlayer().getSelfDefense() / 10 < 1)
            g.drawString(String.valueOf(playing.getPlayer().getSelfDefense()), 390, 458);
        else if (playing.getPlayer().getSelfDefense() / 10 < 10)
            g.drawString(String.valueOf(playing.getPlayer().getSelfDefense()), 388, 458);
        else
            g.drawString(String.valueOf(playing.getPlayer().getSelfDefense()), 385, 458);

    }


    //////////// Event handlers ////////////

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

}
