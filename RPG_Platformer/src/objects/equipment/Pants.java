package objects.equipment;

import objects.equipment.Armor;

public class Pants extends Armor {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Pants() {
        super("Pants", 10 , 2, -1);
        armor = armor + level * 2;
    }
    /// ------------------------------- METHOD ------------------------------- ///
    /// ------------------------------- GETTER AND SETTER ------------------------------- ///
    public int getArmor() {
        return armor;
    }

}
