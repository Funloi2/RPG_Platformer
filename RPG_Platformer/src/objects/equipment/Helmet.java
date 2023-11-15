package objects.equipment;

import objects.equipment.Armor;

public class Helmet extends Armor {

    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Helmet() {
        super("Helmet", 10 , 0, -1);
        armor = armor + level * 2;
    }
    /// ------------------------------- METHOD ------------------------------- ///
    /// ------------------------------- GETTER AND SETTER ------------------------------- ///
    public int getArmor() {
        return armor;
    }

    public int getArmorType() {
        return armorType;
    }
}
