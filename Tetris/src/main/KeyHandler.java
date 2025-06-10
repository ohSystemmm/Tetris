package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public static String keyString = "";
    public static String status = "continue";

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                keyString = "left";
                break;
            case KeyEvent.VK_RIGHT:
                keyString = "right";
                break;
            case KeyEvent.VK_UP:
                keyString = "up";
                break;
            case KeyEvent.VK_DOWN:
                keyString = "down";
                break;
            case KeyEvent.VK_SPACE:
                keyString = "space";
                break;
            case KeyEvent.VK_ENTER:
                keyString = "enter";
                break;
            case KeyEvent.VK_ESCAPE:
                keyString = "escape";
                break;
            case KeyEvent.VK_P:
                if (status.equals("pause"))
                    status = "continue";
                else
                    status = "pause";
                break;
            default:
                keyString = "";
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

}
