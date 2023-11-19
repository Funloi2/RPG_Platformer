package ui.overlay;

import objects.equipment.Armor;
import objects.equipment.Equipment;
import objects.equipment.Sword;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ItemCard {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    private BufferedImage background;

    private int x, y;

    private Equipment item;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///

    public ItemCard(int x, int y, Equipment item) {
        this.x = x + 50;
        this.y = y - 96;
        this.item = item;

        background = LoadSave.GetSpriteAtlas(LoadSave.ITEM_CARD);
    }

    /// ------------------------------- METHOD ------------------------------- ///

    public void draw(Graphics g) {
        g.drawImage(background, x, y, (int) (background.getWidth() * 0.75), (int) (background.getHeight() * 0.75), null);

        String[] listItemType = new String[5];
        listItemType[0] = "HELMET";
        listItemType[1] = "CHEST PLATE";
        listItemType[2] = "LEGS";
        listItemType[3] = "BOOTS";
        listItemType[4] = "SWORD";

        g.setColor(Color.WHITE);

        g.drawString(listItemType[item.getObjType() - 3], x + 30, y + 30);

        if (item instanceof Sword) {
            g.drawString("DMG : " + ((Sword) item).getDamage(), x + 30, y + 65);
            g.drawString("Rarity : " + ((Sword) item).getTier(), x + 30, y + 85);
        } else if (item instanceof Armor) {
            g.drawString("ARMOR : " + ((Armor) item).getArmor(), x + 30, y + 65);
            g.drawString("SET : " + ((Armor) item).getSetNumber(), x + 30, y + 85);
        }

        g.drawString("Sell price : ", x+ 30, y + 120 );

    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

}
