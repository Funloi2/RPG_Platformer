package objects;

public abstract class Armor extends Equipment {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///
    protected int armor;
    protected int armorType;
    protected int setNumber;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    protected Armor(String name, int armor, int armorType, int setNumber) {
        super(name, 0, 0, 0);
        this.armor = armor;
        this.armorType = armorType;
        this.setNumber = setNumber;
    }

    /// ------------------------------- METHOD ------------------------------- ///
    /// ------------------------------- GETTER AND SETTER ------------------------------- ///
    public int getArmor() {
        return armor;
    }

    public int getArmorType() {
        return armorType;
    }

    public int getSetNumber() {
        return setNumber;
    }
}
