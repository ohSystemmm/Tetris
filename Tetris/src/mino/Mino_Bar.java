package mino;

import java.awt.Color;

public class Mino_Bar extends Mino{
    public Mino_Bar(){
        create(Color.CYAN);
    }

    public void setXY(int x, int y){
        // o
        // o
        // o
        // o

        b[0].x = x;
        b[0].y = y;

        b[1].x = b[0].x;
        b[1].y = b[0].y + Block.size;

        b[2].x = b[0].x;
        b[2].y = b[0].y - Block.size;

        b[3].x = b[0].x;
        b[3].y = b[0].y - Block.size * 2;
    }

    public void getDirection1() {
        // o
        // o
        // o
        // o

        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;

        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y + Block.size;

        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y - Block.size;

        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y - Block.size * 2;

        updateXY(1);
    }

    public void getDirection2() {
        // o o o o

        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;

        tempB[1].x = b[0].x + Block.size;
        tempB[1].y = b[0].y;

        tempB[2].x = b[0].x - Block.size;
        tempB[2].y = b[0].y;

        tempB[3].x = b[0].x - Block.size * 2;
        tempB[3].y = b[0].y;

        updateXY(2);
    }

    public void getDirection3() {
        // o
        // o
        // o
        // o

        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;

        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y + Block.size;

        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y - Block.size;

        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y - Block.size * 2;

        updateXY(3);
    }

    public void getDirection4() {
        // o o o o

        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;

        tempB[1].x = b[0].x + Block.size;
        tempB[1].y = b[0].y;

        tempB[2].x = b[0].x - Block.size;
        tempB[2].y = b[0].y;

        tempB[3].x = b[0].x - Block.size * 2;
        tempB[3].y = b[0].y;

        updateXY(4);
    }
}
