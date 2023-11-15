package ui.overlay;

import org.w3c.dom.events.Event;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.ObjectConstants.*;

public class Slot {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    // Overlay data
    protected InventoryOverlay inventoryOverlay;

    // Size and position
    protected int xPos, yPos, size;

    protected int itemType;

    // Hit box
    protected Rectangle bounds;

    // Sprite
    protected BufferedImage image;

    // Event boolean
    protected boolean mouseOver, mousePressed;
    protected boolean switched = false;

    // debug
    private String display;
    private static int cpt = 0;
    private int id;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    protected Slot(int xPos, int yPos, int size, int itemType, InventoryOverlay inventoryOverlay) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.itemType = itemType;
        this.inventoryOverlay = inventoryOverlay;
        id = cpt;
        cpt++;

        initBounds();
        initDisplay();

    }


    /// ------------------------------- METHOD ------------------------------- ///
    protected void initBounds() {
        bounds = new Rectangle(xPos, yPos, size, size);
    }

    protected void loadImage() {
//        image = new BufferedImage[3];
//        BufferedImage temp = LoadSave.GetSpriteAtlas("/Menu/button_atlas.png");
//
//        for (int i = 0; i < image.length; i++) {
//            image[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
//        }
    }

    private void initDisplay() {
        switch (itemType) {
            case 3 -> display = "helmet";
            case 4 -> display = "chest";
            case 5 -> display = "pants";
            case 6 -> display = "boots";
            case 7 -> display = "weapon";
            default -> display = "bag_item " + id;
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(xPos, yPos, size, size);
        g.setColor(Color.WHITE);
        g.drawString(display, xPos + 5, yPos + 25);
        drawBounds(g);
    }

    protected void drawBounds(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void dragItem(int x, int y) {
        xPos = x - size / 2;
        yPos = y - size / 2;
    }

    public void resetPos() {
        xPos = bounds.x;
        yPos = bounds.y;
    }

    public void switchPos(Slot item) {
        int xTemp = bounds.x;
        int yTemp = bounds.y;

        bounds.x = item.bounds.x;
        bounds.y = item.bounds.y;

        item.bounds.x = xTemp;
        item.bounds.y = yTemp;

        int idTemp = id;
        id = item.id;
        item.id = idTemp;

        resetPos();
        item.resetPos();

    }

    public void handleMouseReleased(MouseEvent e) {
        if (isMousePressed()) {

            for (Slot item : inventoryOverlay.getSlots()) {
                if (inventoryOverlay.isIn(e, item)) {
                    if (itemType == item.itemType || item.id >= 5) {
                        switchPos(item);
                        setSwitched(true);
                    } else if (itemType == HELMET && item.id == 0){
                        switchPos(item);
                        setSwitched(true);
                    }else if (itemType == CHEST_PLATE && item.id == 1){
                        switchPos(item);
                        setSwitched(true);
                    }else if (itemType == LEGS && item.id == 2){
                        switchPos(item);
                        setSwitched(true);
                    }else if (itemType == SHOES && item.id == 3){
                        switchPos(item);
                        setSwitched(true);
                    }else if (itemType == WEAPON && item.id == 4){
                        switchPos(item);
                        setSwitched(true);
                    }
                }
            }


//            for (InventorySlot inventoryItem : inventoryOverlay.getInventorySlots()) {
//                if (inventoryOverlay.isIn(e, inventoryItem)) {
//                    if (itemType == inventoryItem.itemType || inventoryItem.itemType == -1) {
//                        switchPos(inventoryItem);
//                        setSwitched(true);
//                    }
//                }
//            }
//            for (ArmorSlot armor : inventoryOverlay.getArmorSlots()) {
//                if (inventoryOverlay.isIn(e, armor)) {
//                    if (itemType == armor.itemType) {
//                        switchPos(armor);
//                        setSwitched(true);
//                    }
//                }
//            }
//
//            if (inventoryOverlay.isIn(e, inventoryOverlay.getWeaponSlot())) {
//                if (itemType == inventoryOverlay.getWeaponSlot().itemType) {
//                    switchPos(inventoryOverlay.getWeaponSlot());
//                    setSwitched(true);
//                }
//            }

            if (!isSwitched())
                resetPos();
            switched = false;
        }
        setMousePressed(false);
    }
    /// ------------------------------- GETTER AND SETTER ------------------------------- ///


    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isSwitched() {
        return switched;
    }

    public void setSwitched(boolean switched) {
        this.switched = switched;
    }


}
