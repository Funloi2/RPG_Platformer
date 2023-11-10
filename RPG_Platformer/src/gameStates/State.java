package gameStates;

import main.Game;

import java.awt.event.MouseEvent;

public class State {
    /// ---------------------- ATTRIBUTES ---------------------- ///

    protected Game game;
    /// ---------------------- CONSTRUCTOR ---------------------- ///

    public State(Game game) {
        this.game = game;
    }
    /// ---------------------- GETTER AND SETTER ---------------------- ///

    public Game getGame() {
        return game;
    }

    /// ---------------------- METHOD ---------------------- ///

//    public boolean isIn(MouseEvent e, MenuButton mb) {
//        return mb.getBounds().contains(e.getX(), e.getY());
//    }
}
