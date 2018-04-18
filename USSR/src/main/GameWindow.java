package main;

import main.gameScreens.GameScreen;
import main.gameScreens.PlayGameScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GameWindow extends Frame implements Runnable{
    private int BACKGROUND_WIDTH = 640,
                BACKGROUND_HEIGHT = 480;

    BufferedImage backBufferImage  = new BufferedImage(BACKGROUND_WIDTH,BACKGROUND_HEIGHT+04,BufferedImage.TYPE_INT_ARGB);
    GameScreen currentGameScreen = null;

    public GameWindow()
    {
        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH,BACKGROUND_HEIGHT);
        this.setPreferredSize(new Dimension(BACKGROUND_WIDTH,BACKGROUND_HEIGHT));
        this.pack();

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        currentGameScreen = new PlayGameScreen();
        attach(currentGameScreen);

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphics = backBufferImage.getGraphics();
        currentGameScreen.update(backBufferGraphics);
        g.drawImage(backBufferImage,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,this);
    }

    @Override
    public void run() {
        long lastLogic = System.currentTimeMillis();
        long lastRender = System.currentTimeMillis();
        while (true) {
            long now = System.currentTimeMillis();
            if (now-lastLogic >= 30) {currentGameScreen.run(); lastLogic = now;}
            if (now-lastRender >= 15) {repaint(); lastRender = now;}
        }
    }


    //**************    CODE FOR CHANGING GAME SCREEN
    public void detach(GameScreen oldGameScreen) {
        this.removeKeyListener(oldGameScreen);
        this.removeMouseListener(oldGameScreen);
    }

    public void attach(GameScreen newGameScreen) {
        this.currentGameScreen = newGameScreen;
        this.addKeyListener(newGameScreen);
        this.addMouseListener(newGameScreen);
    }
}
