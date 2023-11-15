package objects.equipment;

import objects.GameObject;

public abstract class Equipment extends GameObject {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///
    protected String name;
    protected int level;

    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    protected Equipment(String name, int x, int y, int objType) {
        super(x, y, objType);
        this.name = name;
    }
    /// ------------------------------- METHOD ------------------------------- ///
    /// ------------------------------- GETTER AND SETTER ------------------------------- ///


    public int getLevel() {
        return level;
    }
}
