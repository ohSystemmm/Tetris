package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import mino.*;

public class PlayManager {
    // Game Play Area
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    // Minos
    Mino currentMino;
    final int Mino_Start_X;
    final int Mino_Start_Y;
    Mino nextMino;
    final int NextMino_Start_X;
    final int NextMino_Start_Y;
    public static ArrayList<Block> staticBlock = new ArrayList<>();

    public static int dropInterval = 60;
    public boolean GameOver = false;

    // Score
    private int score = 0;
    public  int level = 1;

    public PlayManager() {
        left_x = (GamePanel.WIDTH - WIDTH) / 2 - 100;
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        Mino_Start_X = left_x + WIDTH / 2 - Block.size;
        Mino_Start_Y = top_y + 2 * Block.size;

        NextMino_Start_X = right_x + 175;
        NextMino_Start_Y = top_y + 525;

        currentMino = RandomPick();
        currentMino.setXY(Mino_Start_X, Mino_Start_Y);

        nextMino = RandomPick();
        nextMino.setXY(NextMino_Start_X, NextMino_Start_Y);

    }

    public Mino RandomPick() {
        int random = (int) (Math.random() * 7);
        Mino mino = null;
        switch (random) {
            case 0:
                mino = new Mino_L1();
                break;
            case 1:
                mino = new Mino_L2();
                break;
            case 2:
                mino = new Mino_Square();
                break;
            case 3:
                mino = new Mino_Bar();
                break;
            case 4:
                mino = new Mino_T();
                break;
            case 5:
                mino = new Mino_Z1();
                break;
            case 6:
                mino = new Mino_Z2();
                break;
            default:
                break;
        }
        return mino;
    }

    public void update() {
        
        if (currentMino.active == false) {
            for (int i = 0; i < currentMino.b.length; i++) {
                staticBlock.add(currentMino.b[i]);
            }

            if (currentMino.b[0].x == Mino_Start_X && currentMino.b[0].y == Mino_Start_Y) {
                GameOver = true;
                GamePanel.music.stop();
                GamePanel.se.play(2, false);
            }

            currentMino = nextMino;
            currentMino.setXY(Mino_Start_X, Mino_Start_Y);
            nextMino = RandomPick();
            nextMino.setXY(NextMino_Start_X, NextMino_Start_Y);

            CheckDelete();
        } else {
            currentMino.update();
        }
    }

    public void CheckDelete() {
        int x = left_x;
        int y = top_y;
        int count = 0;

        while (x < right_x && y < bottom_y) {
            for (int i = 0; i < staticBlock.size(); i++) {
                if (staticBlock.get(i).x == x && staticBlock.get(i).y == y) {
                    count++;
                }
            }

            x += Block.size;

            if (x == right_x) {
                if (count == 12) {
                    DeleteLine(y);
                }
                count = 0;
                x = left_x;
                y += Block.size;
            }
        }
    }

    public void Score_Level(){
        score += 100;
        if (score % 500 == 0) {
            if (level < 6){
                level++;
            }
            dropInterval -= level * 10;
            if (dropInterval < 0) {
                dropInterval = 1;
            }
        }
    }

    public void DeleteLine(int y) {
        Score_Level();
        
        for (int i = staticBlock.size() - 1; i > -1; --i) {
            if (staticBlock.get(i).y == y) {
                staticBlock.remove(i);
            }
        }

        for (int i = staticBlock.size() - 1; i > -1; --i) {
            if (staticBlock.get(i).y < y) {
                staticBlock.get(i).y += Block.size;
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        // PLAY AREA
        g2.drawRect(left_x - 4, top_y - 4, WIDTH + 8, HEIGHT + 8);

        int x = right_x + 100;
        int y = bottom_y - 200;
        // NEXT MINO
        g2.drawRect(x, y, 200, 200);

        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x + 60, y + 60);

        // SCORE BOARD
        g2.drawRect(x, top_y, 200, 300);
        x += 5;
        y = top_y + 50;
        g2.setFont(new Font("Arial", Font.PLAIN, 40));
        g2.drawString("LEVEL: ", x + 20, y);
        y += 50;
        g2.drawString("" + level, x + 20, y);
        y += 100;
        g2.drawString("SCORE: ", x + 20, y);
        y += 50;
        g2.drawString("" + score, x + 20, y);

        if (currentMino != null) {
            currentMino.draw(g2);
        }

        if (nextMino != null) {
            nextMino.draw(g2);
        }

        for (int i = 0; i < staticBlock.size(); i++) {
            staticBlock.get(i).draw(g2);
        }

        if (KeyHandler.status == "pause") {
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.PLAIN, 50));
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString("PAUSE", GamePanel.WIDTH / 2 - 100, GamePanel.HEIGHT / 2);
        }

        if (GameOver) {
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.PLAIN, 50));
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString("GAME OVER", GamePanel.WIDTH / 2 - 150, GamePanel.HEIGHT / 2);
        }
    }

}
