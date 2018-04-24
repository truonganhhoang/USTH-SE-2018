package com.company;

import javax.swing.*;
import java.applet.Applet;

public class Main extends Applet {

    public static void main(String[] args) {
        GamePlay game = new GamePlay();

        JFrame window = new JFrame("2048 demo");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        game.start();
    }
}
