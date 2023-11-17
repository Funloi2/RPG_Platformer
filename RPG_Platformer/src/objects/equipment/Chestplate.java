package objects.equipment;

public class Chestplate extends Armor {
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


}
