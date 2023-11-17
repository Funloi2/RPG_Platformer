package objects.equipment;

import static utilz.Constants.ObjectConstants.*;


public class Pants extends Armor {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Pants(int x, int y, int set, int level) {
        super("Pants", 10, LEGS, set);
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
