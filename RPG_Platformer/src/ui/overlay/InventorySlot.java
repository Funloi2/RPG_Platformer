package ui.overlay;

import gameStates.GameState;
import objects.Equipment;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Year;

public class InventorySlot extends Slot {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    // Equipment holder
    private Equipment equipment;

    // Id
    private static int cpt = 0;
    private int id;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public InventorySlot(int xPos, int yPos, int size, int itemType, InventoryOverlay inventoryOverlay) {
        super(xPos, yPos, size, itemType, inventoryOverlay);

        loadImage();
        initBounds();
        id = cpt;
        cpt++;
    }

    /// ------------------------------- METHOD ------------------------------- ///


    public void draw(Graphics g) {
//        g.drawImage(image, xPos, yPos, size, size, null);

        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(xPos, yPos, size, size);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(id), xPos + 5, yPos + 25);
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
