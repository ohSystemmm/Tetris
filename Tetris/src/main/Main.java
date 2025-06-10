package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame("Tetris");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setSize(12 * 26 + 200, 26 * 23 + 25);
       

        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();
        window.addKeyListener(null);



        window.setVisible(true);
        window.setResizable(false);

        gp.launchGame();
    }


}
