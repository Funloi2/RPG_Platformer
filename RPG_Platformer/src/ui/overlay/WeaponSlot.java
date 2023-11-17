package ui.overlay;

import java.awt.*;

public class WeaponSlot extends Slot {

    /// ------------------------------- ATTRIBUTE ------------------------------- ///


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    protected WeaponSlot(int xPos, int yPos, int size, InventoryOverlay inventoryOverlay) {
        super(xPos, yPos, size, 3, inventoryOverlay);

        loadImage();
        initBounds();
    }


    /// ------------------------------- METHOD ------------------------------- ///

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(xPos, yPos, size, size);
        g.setColor(Color.WHITE);
        g.drawString("weapon", xPos + 3, yPos + 25);
        drawBounds(g);
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

}
