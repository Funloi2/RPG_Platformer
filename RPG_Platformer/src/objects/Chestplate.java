package objects;

public class Chestplate extends Armor{
    /// ------------------------------- ATTRIBUTE ------------------------------- ///

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Chestplate() {
        super("Chestplate", 10 , 1, -1);
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
