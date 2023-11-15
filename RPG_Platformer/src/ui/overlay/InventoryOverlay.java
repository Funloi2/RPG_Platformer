package ui.overlay;

import gameStates.Playing;
import main.Game;
import objects.GameObject;
import objects.equipment.Equipment;
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

    // Sprites
    private BufferedImage background;
    private BufferedImage playerArt;

    // Hit boxes list
    private Slot[] slots;
    private ArmorSlot[] armorSlots;
    private WeaponSlot weaponSlot;
    private InventorySlot[] inventorySlots;

    //
    private boolean mouseOver;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public InventoryOverlay(Playing playing) {
        this.playing = playing;

        loadBackground();
        initHitBoxes();
    }


    /// ------------------------------- METHOD ------------------------------- ///

    private void initHitBoxes() {
        slots = new Slot[26];

        for (int i = 0; i < slots.length; i++) {
            if (i < 5)
                slots[i] = new Slot(195, 200 + i * 56, 44, -1, this);
            else
                slots[i] = new Slot((int) (534 + ((i - 5) % 7) * 73.8), 279 + ((i - 5) / 7) * 61, 44, -1, this);
        }


//        armorSlots = new ArmorSlot[4];
//
//        for (int i = 0; i < armorSlots.length; i++) {
//            armorSlots[i] = new ArmorSlot(195, 200 + i * 56, 44, i + 4, this);
//        }
//
//        weaponSlot = new WeaponSlot(195, 200 + armorSlots.length * 56, 44, this);
//
//        inventorySlots = new InventorySlot[21];
//
//        for (int i = 0; i < inventorySlots.length; i++) {
//            inventorySlots[i] = new InventorySlot((int) (534 + (i % 7) * 73.8), 279 + (i / 7) * 61, 44, -1, this);
//        }
    }

    private void loadBackground() {
        background = LoadSave.GetSpriteAtlas(LoadSave.INVENTORY);

        bgWidth = (int) (background.getWidth() * 0.8);
        bgHeight = (int) (background.getHeight() * 0.8);

        bgX = Game.GAME_WIDTH / 2 - bgWidth / 2;
        bgY = Game.GAME_HEIGHT / 2 - bgHeight / 2;

        playerArt = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_SPLASH_ART);
    }

    public void update() {
    }

    public void draw(Graphics g) {
        // Background
        g.drawImage(background, bgX, bgY, bgWidth, bgHeight, null);

        // Player splash art
        g.drawImage(playerArt, 265, 187, (int) (playerArt.getWidth() / 2.8), (int) (playerArt.getHeight() / 2.8), null);

        // Hit boxes

        for (Slot slot : slots) {
            slot.draw(g);
        }

//        for (ArmorSlot armor : armorSlots) {
//            armor.draw(g);
//        }
//
//        weaponSlot.draw(g);
//
//        for (InventorySlot item : inventorySlots) {
//            item.draw(g);
//        }

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

    public boolean pickUpItem(Equipment equipment) {
        boolean pickedUp = false;
        for (int i = 5; i < slots.length; i++) {
            if (slots[i].itemType == -1) {
                slots[i].setEquipment(equipment);
                pickedUp = true;
                break;
            }
        }
        return pickedUp;
    }


    public boolean isIn(MouseEvent e, Slot slot) {
        return slot.getBounds().contains(e.getX(), e.getY());
    }


    //////////// Event handlers ////////////
    public void mouseDragged(MouseEvent e) {

        for (Slot item : slots) {
            if (item.isMousePressed()) {
                item.dragItem(e.getX(), e.getY());
            }
        }

//        for (InventorySlot item : inventorySlots) {
//            if (item.isMousePressed()) {
//                item.dragItem(e.getX(), e.getY());
//            }
//        }
//        for (ArmorSlot armor : armorSlots) {
//            if (armor.isMousePressed()) {
//                armor.dragItem(e.getX(), e.getY());
//            }
//        }
//        if (weaponSlot.isMousePressed()) {
//            weaponSlot.dragItem(e.getX(), e.getY());
//        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

        for (Slot item : slots) {
            if (isIn(e, item)) {
                item.setMousePressed(true);
            }
        }

//        for (InventorySlot item : inventorySlots) {
//            if (isIn(e, item)) {
//                item.setMousePressed(true);
//            }
//        }
//        for (ArmorSlot armor : armorSlots) {
//            if (isIn(e, armor)) {
//                armor.setMousePressed(true);
//            }
//        }
//        if (isIn(e, weaponSlot)) {
//            weaponSlot.setMousePressed(true);
//        }
    }

    public void mouseReleased(MouseEvent e) {

        for (Slot item : slots) {
            item.handleMouseReleased(e);
        }

//        for (InventorySlot item : inventorySlots) {
//            item.handleMouseReleased(e);
//        }
//
//        for (ArmorSlot armor : armorSlots) {
//            armor.handleMouseReleased(e);
//        }
//        weaponSlot.handleMouseReleased(e);

    }

    public void mouseMoved(MouseEvent e) {
//        setMouseOver(false);
//
//        for (InventorySlot item : inventorySlots) {
//            if (isIn(e, item)) {
//                setMouseOver(true);
//            }
//        }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///


    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public ArmorSlot[] getArmorSlots() {
        return armorSlots;
    }

    public InventorySlot[] getInventorySlots() {
        return inventorySlots;
    }

    public WeaponSlot getWeaponSlot() {
        return weaponSlot;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public Playing getPlaying() {
        return playing;
    }
}
