package objects;

public abstract class Weapon extends Equipment {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///
    protected int damage;
    protected int attackSpeed;
    protected int range;
    protected int attackType;
    protected int tier;
    protected int weaponType;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    protected Weapon(String name, int damage, int attackSpeed, int range, int attackType) {
        super(name, 0, 0, 0);
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.range = range;
        this.attackType = attackType;
    }

    protected Weapon(String name, int damage) {
        super(name, 0, 0, 0);
        this.damage = damage;
    }

    /// ------------------------------- METHOD ------------------------------- ///
    /// ------------------------------- GETTER AND SETTER ------------------------------- ///
    public int getDamage() {
        return damage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int getRange() {
        return range;
    }

    public int getAttackType() {
        return attackType;
    }

}
