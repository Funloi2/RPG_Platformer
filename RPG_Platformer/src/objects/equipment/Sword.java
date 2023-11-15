package objects;

public class Sword extends Weapon{

    /// ------------------------------- ATTRIBUTE ------------------------------- ///
    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Sword(String name, int damage) {
        super("Sword", 10);
        damage = damage + level * 3;
    }
    /// ------------------------------- GETTER AND SETTER ------------------------------- ///
    /// ------------------------------- METHOD ------------------------------- ///

    public void attack() {

    }

    public void update() {

    }

    public void draw() {

    }

    public boolean isGodSlayer() {
        return tier == 5;
    }

    public void buffGod(){
        if(isGodSlayer()) {
            damage = damage * 2000000000;
        }
    }



}
