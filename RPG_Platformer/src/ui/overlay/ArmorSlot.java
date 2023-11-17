package ui.overlay;

import java.awt.*;

public class ArmorSlot extends Slot {

    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    private String type;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    protected ArmorSlot(int xPos, int yPos, int size, int armorType, InventoryOverlay inventoryOverlay) {
        super(xPos, yPos, size, armorType, inventoryOverlay);
        setType();

        loadImage();
        initBounds();
    }


    /// ------------------------------- METHOD ------------------------------- ///

    public void setType() {
        switch (itemType) {
            case 4 -> type = "helmet";
            case 5 -> type = "chest";
            case 6 -> type = "pants";
            case 7 -> type = "boots";
            default -> type = "not an armor";
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(xPos, yPos, size, size);
        g.setColor(Color.WHITE);
        g.drawString(type, xPos + 3, yPos + 25);
        drawBounds(g);
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

}
