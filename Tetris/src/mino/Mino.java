package mino;

import java.awt.Color;
import java.awt.Graphics2D;
import main.KeyHandler;

import main.PlayManager;

public class Mino {
    public Block b[] = new Block[4];
    public Block tempB[] = new Block[4];
    public int autoDropCounter = 0;
    public int direction = 1;
    public Boolean active = true;
    //public Boolean deactive = false;
    public int deactiveCount = 0;

    public Boolean LeftCollision, RightCollision, BottomCollision, RotationCollision;

    public void create(Color c) {
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);
        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);
    }

    public void setXY(int x, int y) {

    }

    public void updateXY(int Direction) {

        CheckRotation();
        if (!BottomCollision && !LeftCollision && !RightCollision) {
            this.direction = Direction;
            for (int i = 0; i < 4; ++i) {
                b[i].x = tempB[i].x;
                b[i].y = tempB[i].y;
            }
        }

    }

    public void getDirection1() {

    }

    public void getDirection2() {

    }

    public void getDirection3() {

    }

    public void getDirection4() {

    }

    public Boolean CheckCollision() {
        LeftCollision = false;
        RightCollision = false;
        BottomCollision = false;

        CheckCollionBlock();

        for (int i = 0; i < b.length; ++i) {
            if (b[i].x == PlayManager.left_x) {
                LeftCollision = true;
            }
            if (b[i].x + Block.size == PlayManager.right_x) {
                RightCollision = true;
            }
            if (b[i].y + Block.size == PlayManager.bottom_y) {
                BottomCollision = true;
            }
        }
        return false;
    }

    public Boolean CheckRotation() {
        LeftCollision = false;
        RightCollision = false;
        BottomCollision = false;

        CheckCollionBlock();

        for (int i = 0; i < b.length; i++){
            if (b[i].x - Block.size < PlayManager.left_x) {
                LeftCollision = true;
            }
            if (b[i].x + Block.size > PlayManager.right_x) {
                RightCollision = true;
            }
            if (b[i].y + Block.size > PlayManager.bottom_y) {
                BottomCollision = true;
            }
        }

        return false;
    }

    public Boolean CheckCollionBlock(){
        for (int i = 0; i < PlayManager.staticBlock.size(); i++) {
            for (int j = 0; j < b.length; j++) {
                if (b[j].x == PlayManager.staticBlock.get(i).x && b[j].y + Block.size == PlayManager.staticBlock.get(i).y) {
                    BottomCollision = true;
                }
                if (b[j].x == PlayManager.staticBlock.get(i).x + Block.size && b[j].y == PlayManager.staticBlock.get(i).y) {
                    LeftCollision = true;
                }
                if (b[j].x == PlayManager.staticBlock.get(i).x - Block.size && b[j].y == PlayManager.staticBlock.get(i).y) {
                    RightCollision = true;
                }
            }
        }
        return false;
    }

    public void deactive() {
        deactiveCount++;
        if (deactiveCount == 30) {
            active = false;
        }
    }

    public void update() {
        //if (!active) {
            //return;
        //}

        CheckCollision();
        switch (KeyHandler.keyString) {
            case "enter":
                switch (direction) {
                    case 1:
                        if (!CheckRotation()) {

                            getDirection2();
                        }
                        break;
                    case 2:
                        if (!CheckRotation()) {
                            getDirection3();
                        }
                        break;
                    case 3:
                        if (!CheckRotation()) {
                            getDirection4();
                        }
                        break;
                    case 4:
                        if (!CheckRotation()) {
                            getDirection1();
                        }
                        break;
                }
                KeyHandler.keyString = "";
                // autoDropCounter = 0;
                break;
            case "up":
                // for (int i = 0; i < 4; i++) {
                // b[i].y -= Block.size;
                // }
                // KeyHandler.keyString = "";
                // autoDropCounter = 0;
                // break;
            case "down":
                if (!BottomCollision) {
                    for (int i = 0; i < 4; i++) {
                        b[i].y += Block.size;
                    }
                    KeyHandler.keyString = "";
                    autoDropCounter = 1;
                }
                break;
            case "left":
                if (!LeftCollision) {
                    for (int i = 0; i < 4; i++) {
                        b[i].x -= Block.size;
                    }
                    KeyHandler.keyString = "";
                    autoDropCounter = 1;
                }
                break;
            case "right":
                if (!RightCollision) {
                    for (int i = 0; i < 4; i++) {
                        b[i].x += Block.size;
                    }
                    KeyHandler.keyString = "";
                    autoDropCounter = 1;
                }
                break;
            default:
                break;
        }

        if (!BottomCollision){
            autoDropCounter++;
            if (autoDropCounter == PlayManager.dropInterval) {
                autoDropCounter = 0;
                for (int i = 0; i < 4; i++) {
                    b[i].y += Block.size;
                }
            }
        } else {
            deactive();
        }
            
    }

    public void draw(Graphics2D g2) {
        int margin = 1;
        g2.setColor(b[0].c);
        for (int i = 0; i < 4; i++) {
            g2.fillRect(b[i].x + margin, b[i].y + margin, Block.size - margin * 2, Block.size - margin * 2);
        }
    }
}
