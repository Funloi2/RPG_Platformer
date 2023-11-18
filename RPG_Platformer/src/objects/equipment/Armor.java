package objects.equipment;

import static utilz.Constants.ObjectConstants.ARMOR_HEIGHT;
import static utilz.Constants.ObjectConstants.ARMOR_WIDTH;

public abstract class Armor extends Equipment {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///
    protected int armor;
    protected int setNumber;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    protected Armor(String name, int x, int y, int armor, int armorType, int setNumber) {
        super(name, x, y, armorType);
        this.armor = armor;
        this.setNumber = setNumber;

        initHitBox((int) (ARMOR_WIDTH * 0.8), (int) (ARMOR_HEIGHT * 0.8));
    }

    /// ------------------------------- METHOD ------------------------------- ///
    /// ------------------------------- GETTER AND SETTER ------------------------------- ///
    public int getArmor() {
        return armor;
    }

    public int getSetNumber() {
        return setNumber;
    }
}
