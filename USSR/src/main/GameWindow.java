package main;

import controllers.*;
import main.gameScreens.GameScreen;
import main.gameScreens.PlayGameScreen;
import models.Player;
import utilities.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class GameWindow extends Frame implements Runnable {
    private int BACKGROUND_WIDTH = 640,
            BACKGROUND_HEIGHT = 480;

    BufferedImage backBufferImage = new BufferedImage(BACKGROUND_WIDTH, BACKGROUND_HEIGHT + 04, BufferedImage.TYPE_INT_ARGB);
    GameScreen currentGameScreen = null;

    public GameWindow() {
        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        this.setPreferredSize(new Dimension(BACKGROUND_WIDTH, BACKGROUND_HEIGHT));
        this.setResizable(false);
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

    // update the game graphic
    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphics = backBufferImage.getGraphics();
        currentGameScreen.update(backBufferGraphics);
        g.drawImage(backBufferImage, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, this);
    }

    // updating game logic and game images
    @Override
    public void run() {
        long lastLogic = System.currentTimeMillis();
        long lastRender = System.currentTimeMillis();
        while (true) {
            long now = System.currentTimeMillis();
            if (now - lastLogic >= 30) {
                currentGameScreen.run();
                lastLogic = now;
            }
            if (now - lastRender >= 15) {
                repaint();
                lastRender = now;
            }
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


    //********* end-to-end testing **************************************************************//
    // By HuynhVinhNam 8/5/2018

    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.start();

        for (int i = 1; i <= 3; i++) {
            testing(i);
        }
    }

    public static boolean testing(int testNumber) {
        LevelManager levelManager = LevelManager.getInstance();
        GameLevel gameLevel = GameLevel.getInstance();
        Scanner input = null;
        int level, nInstruction = 0, nCondition = 0;
        String[] instructions = new String[50];
        boolean result = true;

        try {
            input = new Scanner(new File("testcases/" + testNumber + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Simulating player input
        nInstruction = input.nextInt();
        input.nextLine();

        long lastRun = System.currentTimeMillis();
        int keyPress = 0;
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= nInstruction + 1; i++) {
            long now = System.currentTimeMillis();
            if (now - lastRun >= 400 && keyPress != 0) robot.keyRelease(keyPress);
            if (now - lastRun <= 1200) {
                i--;
                continue;
            }
            lastRun = now;

            if (i == nInstruction + 1) {
                robot.keyRelease(keyPress);
                break;
            }
            instructions[i] = input.nextLine();
            System.out.println(instructions[i]);

            keyPress = 0;
            if (instructions[i].equals("UP")) keyPress = KeyEvent.VK_UP;
            if (instructions[i].equals("DOWN")) keyPress = KeyEvent.VK_DOWN;
            if (instructions[i].equals("LEFT")) keyPress = KeyEvent.VK_LEFT;
            if (instructions[i].equals("RIGHT")) keyPress = KeyEvent.VK_RIGHT;
            if (instructions[i].equals("STAY")) keyPress = KeyEvent.VK_SPACE;
            if (keyPress != 0) robot.keyPress(keyPress);
        }


        // Checking results
        nCondition = input.nextInt();
        input.nextLine();
        System.out.println(nCondition);

        for (int i = 1; i <= nCondition; i++) {
            String s = input.nextLine();
            String[] words = s.split(" ");
            int x = Integer.parseInt(words[1]);
            int y = Integer.parseInt(words[2]);
            boolean okCondition = false;
            Vector<SingleController> singleControllers;

            if (words[0].equals("PLAYER")) {
                if (x != PlayerController.getInstance().getColumn() || y != PlayerController.getInstance().getRow()) {
                    result = false;
                    System.out.println("Player character error");
                }
            }

            if (words[0].equals("WHITE")) {
                okCondition = false;
                singleControllers = EnemyControllerManager.getInstance().getManager();
                for (SingleController singleController : singleControllers) {
                    System.out.println((singleController instanceof EnemyControllerWhite));
                    if ((singleController instanceof EnemyControllerWhite) && x == singleController.getColumn() && y == singleController.getRow()) {
                        okCondition = true;
                        break;
                    }
                }
                if (!okCondition) {
                    result = false;
                    System.out.println("White mummy error");
                }
            }

            if (words[0].equals("RED")) {
                okCondition = false;
                singleControllers = EnemyControllerManager.getInstance().getManager();
                for (SingleController singleController : singleControllers) {
                    if ((singleController instanceof EnemyControllerRed) && x == singleController.getColumn() && y == singleController.getRow()) {
                        okCondition = true;
                        break;
                    }
                }
                if (!okCondition) {
                    result = false;
                    System.out.println("Red mummy error");
                }
            }

            if (words[0].equals("SCORPION")) {
                okCondition = false;
                singleControllers = EnemyControllerManager.getInstance().getManager();
                for (SingleController singleController : singleControllers) {
                    if ((singleController instanceof EnemyControllerScorpion) && x == singleController.getColumn() && y == singleController.getRow()) {
                        okCondition = true;
                        break;
                    }
                }
                if (!okCondition) {
                    result = false;
                    System.out.println("Scorpion error");
                }
            }
        }

        System.out.println("Result = " + result);

        String lastMove = input.next();
        keyPress = 0;
        if (lastMove.equals("UP")) keyPress = KeyEvent.VK_UP;
        if (lastMove.equals("DOWN")) keyPress = KeyEvent.VK_DOWN;
        if (lastMove.equals("LEFT")) keyPress = KeyEvent.VK_LEFT;
        if (lastMove.equals("RIGHT")) keyPress = KeyEvent.VK_RIGHT;
        if (lastMove.equals("STAY")) keyPress = KeyEvent.VK_SPACE;
        if (keyPress != 0) robot.keyPress(keyPress);

        long now = System.currentTimeMillis();
        while (System.currentTimeMillis() - now <= 100) {

        }
        if (keyPress != 0) robot.keyRelease(keyPress);

        return result;
    }
}
