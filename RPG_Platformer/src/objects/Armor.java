package objects;

public abstract class Armor extends Equipment {
/// ------------------------------- ATTRIBUTE ------------------------------- ///
    protected int armor;
    protected int armorType;
    protected int setNumber;



    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    protected Armor(String name, int armor, int armorType, int setNumber) {
        super(name);
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
}
