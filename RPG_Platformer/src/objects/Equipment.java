package objects;

 public abstract class Equipment {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///
    protected String name;
    protected int level;
    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    protected Equipment(String name) {
        this.name = name;
    }
    /// ------------------------------- METHOD ------------------------------- ///
    /// ------------------------------- GETTER AND SETTER ------------------------------- ///


     public int getLevel() {
         return level;
     }
 }
