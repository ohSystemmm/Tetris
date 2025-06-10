package main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public final int FPS = 60;
    private Thread gameThread;
    private PlayManager pm;
    public static Sound music = new Sound();
    public static Sound se = new Sound();

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        this.setLayout(null);
        pm = new PlayManager();
        this.addKeyListener(new KeyHandler());
        this.setFocusable(isFocusable());
    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();

        music.play(0, true);
        music.loop();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update() {
        if (KeyHandler.status == "pause" || pm.GameOver) {
            return;
        }
        if (KeyHandler.status == "continue") {
            pm.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
        // g2d.setColor(Color.WHITE);

        Graphics2D g2 = (Graphics2D) g;
        pm.draw(g2);
    }

}
