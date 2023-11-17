package ui.overlay;

import objects.equipment.Equipment;

import java.awt.*;

public class InventorySlot extends Slot {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    // Equipment holder
    private Equipment equipment;




    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public InventorySlot(int xPos, int yPos, int size, int itemType, InventoryOverlay inventoryOverlay) {
        super(xPos, yPos, size, itemType, inventoryOverlay);

        loadImage();
        initBounds();

    }

    /// ------------------------------- METHOD ------------------------------- ///


    public void draw(Graphics g) {
//        g.drawImage(image, xPos, yPos, size, size, null);

        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(xPos, yPos, size, size);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(1), xPos + 5, yPos + 25);
        drawBounds(g);
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///


    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }


}
