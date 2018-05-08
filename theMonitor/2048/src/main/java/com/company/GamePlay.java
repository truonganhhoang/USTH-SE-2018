package com.company;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

//The Runnable interface defines a single method: run(), meant to contain the code executed in the thread
//The listener interface for receiving keyboard events (keystrokes).

public class GamePlay extends JPanel implements KeyListener,Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 500;    // WIDTH of the Board Game
    public static final int HEIGHT = 530;   // HEIGHT of the Board Game
    public static final Font main = new Font("Bebas Neue Regular", Font.PLAIN, 28); // FONT of the number in tile
    private Thread game;    //  Create Game thread
    private boolean running;    // Game in running state
    private BufferedImage Image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); //Buffer the Board Game
    private GameBoard board; // Create Board Game

    private long startTime;
    private long elapsed;
    private boolean set;

    public GamePlay() {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Create the components'size of the Board game
        addKeyListener(this);

        board = new GameBoard(WIDTH / 2 - GameBoard.BOARD_WIDTH / 2, HEIGHT - GameBoard.BOARD_HEIGHT - 10);  //Set Board game in the center of JFrame

    }

    private void update() {
        board.update();
        Keyboard.update();
    }

    private void render() {
        Graphics2D g = (Graphics2D) Image.getGraphics(); // Create Board game in 2d
        g.setColor(Color.gray);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //render board
        board.render(g);
        g.dispose();


        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.drawImage(Image, 0, 0, null);
        g2d.dispose();
    }

    @Override
    public void run(){ // Run thread

        int fps = 0, updates = 0;
        long fpsTimer = System.currentTimeMillis();
        double nsPerUpdate = 1000000000.0 / 60;

        //last update time in nanoseconds
        double then = System.nanoTime();
        double unprocessed = 0;

        while(running) {

            boolean shouldRender = false;
            double now = System.nanoTime();
            unprocessed += (now - then)/nsPerUpdate;
            then = now;
            //update queue
            while (unprocessed >= 1) {
                updates++;
                update();
                unprocessed--;
                shouldRender = true;
            }

            if (shouldRender) {
                fps++;
                render();
                shouldRender = false;
            } else {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //FPS Timer
        if(System.currentTimeMillis() - fpsTimer > 1000){
            System.out.printf("%d fps %d updates", fps, updates);
            System.out.println();
            fps = 0;
            updates = 0;
            fpsTimer += 1000;
        }
    }

    public synchronized  void start(){
        if(running) return;
        running = true;
        game = new Thread(this,"game demo");
        game.start();
    }

    public synchronized  void stop(){
        if(!running) return;
        running = false;
        System.exit(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Keyboard.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Keyboard.keyRealeased(e);
    }
}