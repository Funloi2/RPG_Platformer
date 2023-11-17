package objects.equipment;

import static utilz.Constants.ObjectConstants.*;

public class Boots extends Armor {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Boots(int x, int y, int set, int level) {
        super("Boots", 10, SHOES, set);
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
