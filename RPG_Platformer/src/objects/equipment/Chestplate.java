package objects.equipment;

import static utilz.Constants.ObjectConstants.*;

public class Chestplate extends Armor {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Chestplate(int x, int y, int set, int level) {
        super("Chestplate", 10, CHEST_PLATE, set);
        this.x = x;
        this.y = y;
        this.level = level;
        armor += level * 2;
    }

    /// ------------------------------- METHOD ------------------------------- ///
    /// ------------------------------- GETTER AND SETTER ------------------------------- ///
    public int getArmor() {
        return armor;
    }


}
