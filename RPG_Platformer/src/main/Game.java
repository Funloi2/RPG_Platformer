package main;

public class Game {
    private GameWindow window;
    private GamePanel panel;

    public Game() {

        panel = new GamePanel();
        window = new GameWindow(panel);

        panel.requestFocus();
    }
}