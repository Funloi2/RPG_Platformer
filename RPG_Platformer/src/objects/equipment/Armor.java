package objects.equipment;

public abstract class Armor extends Equipment {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///
    protected int armor;
    protected int setNumber;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    protected Armor(String name, int armor, int armorType, int setNumber) {
        super(name, 0, 0, armorType);
        this.armor = armor;
        this.setNumber = setNumber;
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
