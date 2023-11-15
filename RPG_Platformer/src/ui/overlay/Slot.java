package ui.overlay;

import org.w3c.dom.events.Event;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

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

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    protected Slot(int xPos, int yPos, int size, int itemType, InventoryOverlay inventoryOverlay) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.itemType = itemType;
        this.inventoryOverlay = inventoryOverlay;
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

        resetPos();
        item.resetPos();

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


    public void handleMouseReleased(MouseEvent e) {
        if (isMousePressed()) {
            for (InventorySlot inventoryItem : inventoryOverlay.getInventorySlots()) {
                if (inventoryOverlay.isIn(e, inventoryItem)) {
                    switchPos(inventoryItem);
                    setSwitched(true);
                }
            }
            for (ArmorSlot armor : inventoryOverlay.getArmorSlots()) {
                if (inventoryOverlay.isIn(e, armor)) {
                    switchPos(armor);
                    setSwitched(true);
                }
            }

            if (inventoryOverlay.isIn(e, inventoryOverlay.getWeaponSlot())) {
                switchPos(inventoryOverlay.getWeaponSlot());
                setSwitched(true);
            }

            if (!isSwitched())
                resetPos();
            switched = false;
        }
        setMousePressed(false);
    }
}
