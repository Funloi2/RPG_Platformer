package ui.overlay;

import main.Game;
import objects.equipment.*;
import org.w3c.dom.events.Event;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.ObjectConstants.*;

public class Slot {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    // Overlay data
    private InventoryOverlay inventoryOverlay;

    // Size and position
    private int xPos, yPos, size;

    // Item
    private int itemType;
    private Equipment equipment;

    // Hit box
    private Rectangle bounds;

    // Sprite
    private BufferedImage image;

    // Event boolean
    private boolean mouseOver, mousePressed;
    private boolean switched = false;

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
//        g.setColor(new Color(0, 0, 0, 100));
//        g.fillRect(xPos, yPos, size, size);
//        g.setColor(Color.WHITE);
//        if (equipment != null)
//            g.drawString(String.valueOf(equipment.getLevel()), xPos + 5, yPos + 25);
//        else
//            g.drawString(itemType + " " + id, xPos + 5, yPos + 25);
//        drawBounds(g);

        g.drawImage(image, (int) (xPos + (size - size * 0.7) / 2) + 2, yPos + (int) ((size - size * 0.7) / 2) + 2, (int) (size * 0.7), (int) (size * 0.7), null);

        if (isMouseOver() && equipment != null) {
            ItemCard itemCard = new ItemCard(xPos, yPos, equipment);
            itemCard.draw(g);
        }

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

    private void dropItemFromInventory() {
        equipment.setX((int) (inventoryOverlay.getPlaying().getPlayer().getHitBox().x));
        equipment.setY((int) (inventoryOverlay.getPlaying().getPlayer().getHitBox().y + inventoryOverlay.getPlaying().getPlayer().getHitBox().height - equipment.getHitbox().height));

        equipment.initHitBox(ARMOR_WIDTH, ARMOR_HEIGHT);

        equipment.setActive(true);

        inventoryOverlay.getPlaying().getObjectManager().dropItem(equipment);

        equipment = null;
        itemType = -1;
        image = null;
    }

    public void handleMouseReleased(MouseEvent e) {
        setMousePressed(false);

        if (itemType == -1) {
            resetPos();
            return;
        }

        if (!inventoryOverlay.getOverlayHitbox().contains(e.getX(), e.getY())) {
            System.out.println("dropItemFromInventory");
            dropItemFromInventory();
        }

        for (Slot item : inventoryOverlay.getSlots()) {
            if (inventoryOverlay.isIn(e, item)) {
                if (itemType == HELMET && item.id == 0) {
                    System.out.println("branch 2");
                    inventoryOverlay.getPlaying().getPlayer().setHelmet((Helmet) equipment);
                    switchPos(item);
                    setSwitched(true);
                } else if (itemType == CHEST_PLATE && item.id == 1) {
                    inventoryOverlay.getPlaying().getPlayer().setChestplate((Chestplate) equipment);
                    switchPos(item);
                    setSwitched(true);
                } else if (itemType == LEGS && item.id == 2) {
                    inventoryOverlay.getPlaying().getPlayer().setPants((Pants) equipment);
                    switchPos(item);
                    setSwitched(true);
                } else if (itemType == SHOES && item.id == 3) {
                    inventoryOverlay.getPlaying().getPlayer().setBoots((Boots) equipment);
                    switchPos(item);
                    setSwitched(true);
                } else if (itemType == WEAPON && item.id == 4) {
                    inventoryOverlay.getPlaying().getPlayer().setSword((Sword) equipment);
                    switchPos(item);
                    setSwitched(true);
                } else if (itemType == item.itemType || item.id >= 5) {
                    System.out.println("switch");
                    switch (id) {

                        case 0 -> inventoryOverlay.getPlaying().getPlayer().setHelmet((Helmet) item.equipment);
                        case 1 -> inventoryOverlay.getPlaying().getPlayer().setChestplate((Chestplate) item.equipment);
                        case 2 -> inventoryOverlay.getPlaying().getPlayer().setPants((Pants) item.equipment);
                        case 3 -> inventoryOverlay.getPlaying().getPlayer().setBoots((Boots) item.equipment);
                        case 4 -> inventoryOverlay.getPlaying().getPlayer().setSword((Sword) item.equipment);
                    }
                    switchPos(item);
                    setSwitched(true);

                }
            }
        }

        if (!isSwitched())
            resetPos();
        switched = false;


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


    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
        itemType = equipment.getObjType();
        image = equipment.getImage();
    }

    public int getItemType() {
        return itemType;
    }

    public int getId() {
        return id;
    }
}
