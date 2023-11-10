package main;

import java.awt.*;
import java.lang.management.PlatformLoggingMXBean;

public class Game implements Runnable {
    /// ------------------------------- ATTRIBUTES ------------------------------- ///
    private GameWindow window;
    private GamePanel panel;

    // Game Loop and FPS settings
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    // Game size
    public static final int TILES_DEFAULT_SIZE = 32;
    public static final float SCALE = 1.5f;
    public static final int TILES_IN_WIDTH = 26;
    public static final int TILES_IN_HEIGHT = 14;
    public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public Game() {

        panel = new GamePanel(this);
        window = new GameWindow(panel);
        panel.setFocusable(true);
        panel.requestFocus();

        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        panel.update();
    }

    public void render(Graphics g) {
        panel.render(g);
    }


    @Override
    public void run() {
        // Set FPS and UPS
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        // Var
        long previousTime = System.nanoTime();

        int frame = 0;
        int update = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                update++;
                deltaU--;
            }

            if (deltaF >= 1) {
                panel.repaint();
                frame++;
                deltaF--;
            }


            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS : " + frame + " | UPS: " + update);
                frame = 0;
                update = 0;
            }
        }
    }

    public void windowFocusLost() {

        panel.getPlayer().resetDirBooleans();

    }
}