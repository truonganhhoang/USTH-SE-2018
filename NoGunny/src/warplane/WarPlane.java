/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warplane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;

/**
 *
 * @author duanp
 */
public class WarPlane extends GameScreen {

    private BufferedImage planeImage;

    private Animation planeAni;

    private Animation enemyAni;

    public static float g = 0.1f;

    private Plane plane;
    private static int score = 0;
    private static int highScore = 0;

    private Ground ground;
    private Mountain mountain;
    private PlaneEnemy planeEnemy;
    private int beginGame = 0;
    private int playGame = 1;
    private int overGame = 2;
    private int pauseGame = 3;
    Font myFont = new Font("Serif", Font.BOLD, 20);
    Font myFont2 = new Font("Serif", Font.BOLD, 40);
    private long deltaTime;
    private long beginTimePause;
    private long finalTimePause;
    private int currentScreen = beginGame;

    public WarPlane() {
        super(800, 600);
        try {
            planeImage = ImageIO.read(new File("Assets/phane2.gif"));

        } catch (IOException ex) {
        }

        planeAni = new Animation(100);
        AFrameOnImage f;
        f = new AFrameOnImage(0, 0, 120, 68);
        planeAni.addFrame(f);
        f = new AFrameOnImage(120, 0, 120, 68);
        planeAni.addFrame(f);
        f = new AFrameOnImage(240, 0, 120, 68);
        planeAni.addFrame(f);
        f = new AFrameOnImage(360, 0, 120, 68);
        planeAni.addFrame(f);
        f = new AFrameOnImage(240, 0, 120, 68);
        planeAni.addFrame(f);
        f = new AFrameOnImage(120, 0, 120, 68);
        planeAni.addFrame(f);

        plane = new Plane(100, 350, 50, 50);

        planeEnemy = new PlaneEnemy();
        ground = new Ground();
        mountain = new Mountain();

        beginGame();
    }

    public static void main(String[] args) {
        new WarPlane();

    }

    private void resetGame() {
        plane.setPos(110, 250);
        plane.setVt(0);
        plane.setLive(true);
        score = 0;
        planeEnemy.resetEnemy();
    }

    @Override
    public void gameUpdate(long deltaTime) {
        if (currentScreen == beginGame) {
            resetGame();////va cham
        } else if (currentScreen == playGame) {

            if (plane.getLive()) {
                planeAni.updateMe(deltaTime);
            }

            plane.update(deltaTime);
            ground.update();
            mountain.Update();
            try {
                planeEnemy.update();
            } catch (InterruptedException ex) {
                Logger.getLogger(WarPlane.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < PlaneEnemy.size; i++) {
                if (plane.getRectangle().intersects(planeEnemy.getEnemy(i).getRectangle())) {
                    plane.setLive(false);
                    System.out.println("set Live = false");
                }
            }

            for (int i = 0; i < planeEnemy.size; i++) {
                if (plane.getPosX() > (planeEnemy.getEnemy(i).getPosX()) && !planeEnemy.getEnemy(i).getBehindEnemy()) {
                    score++;
                    planeEnemy.getEnemy(i).setBehindEnemy(true);
                }
            }
            File file = new File("test.txt");
            try {
                PrintWriter output = new PrintWriter(file);
                output.println(score);
                output.close();
            } catch (FileNotFoundException ex) {
                System.out.printf("ERROR: %s\n", ex);
            }
            try {
                Scanner input = new Scanner(file);
                int point = input.nextInt();
                System.out.printf("Points: %d\n", point);
            } catch (IOException ex) {
                System.err.println("ERROR");
            }

            ///get high score
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                while (line != null) // read the score file line by line
                {
                    try {
                        int score = Integer.parseInt(line.trim());   // parse each line as an int
                        if (score > highScore) // and keep track of the largest
                        {
                            highScore = score;
                        }
                    } catch (NumberFormatException e1) {
                        // ignore invalid scores
                        //System.err.println("ignoring invalid score: " + line);
                    }
                    line = reader.readLine();
                }
                reader.close();

            } catch (IOException ex) {
                System.err.println("ERROR reading scores from file");
            }

            //va cham ground
            if (plane.getPosY() + plane.getH() > ground.getYGround()) {
                currentScreen = overGame;
            }
            if (plane.getPosY() + plane.getH() < 0) {
                currentScreen = overGame;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WarPlane.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else if (currentScreen == overGame) {

        }
    }

    @Override
    public void gamePaint(Graphics2D g2) {

        g2.setColor(Color.decode("#b8baef"));
        g2.fillRect(0, 0, masterWidth, masterHeight);

        ground.Paint(g2);
        mountain.Paint(g2);
        planeEnemy.paint(g2);

        if (plane != null) {

            planeAni.paintAnims((int) plane.getPosX(), (int) plane.getPosY(), planeImage, g2, 0, 0);
        }
        if (currentScreen == beginGame) {
            g2.setColor(Color.RED);
            g2.setFont(myFont);
            g2.drawString("Press space to play game", 280, 300);
        }
        if (currentScreen == overGame) {
            g2.setColor(Color.RED);
            g2.setFont(myFont);
            g2.drawString("Press space to turn back begin screen", 250, 320);
            g2.drawString(" YOUR SCORE:" + score, 300, 280);
            g2.drawString(" HIGHSCORE:" + highScore, 300, 300);
            g2.setColor(Color.RED);
            g2.setFont(myFont2);
            g2.drawString("---You Died---", 260, 240);
        }
        g2.setColor(Color.RED);
        g2.setFont(myFont);
        g2.drawString("Score:" + score, 20, 30);
    }

    @Override
    public void keyAction(KeyEvent e, int Event) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (currentScreen == beginGame) {
                try {
                    TimeUnit.SECONDS.sleep((long) 0.8);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WarPlane.class.getName()).log(Level.SEVERE, null, ex);
                }
                currentScreen = playGame;
            } else if (currentScreen == playGame) {
                if (plane.getLive()) {
                    plane.fly();
                }
                plane.setPosX(plane.getPosX() - 4);

            } else if (currentScreen == overGame) {
                currentScreen = beginGame;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("you press Enter  ");
        }
//        if(currentScreen == playGame){
//            if(e.getKeyCode() ==KeyEvent.VK_P){
//                  System.out.println("you press p1 ");
//                  currentScreen=pauseGame;
//                beginTimePause= System.nanoTime();
//                if(e.getKeyCode() ==KeyEvent.VK_P){
//                      System.out.println("you press p lan 2 ");
//                    finalTimePause=System.nanoTime();
//                    
//                }
//                deltaTime=finalTimePause-beginTimePause;
//          
//                try {
//                    TimeUnit.SECONDS.sleep(deltaTime/1000000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(WarPlane.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            
//        }

        //       }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("you press right  ");
            plane.setPosX(plane.getPosX() + 2);
            if (plane.getLive()) {
                plane.fly();
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("you press down  ");
            plane.setPosY(plane.getPosY() + 4);

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("you press left  ");
            plane.setPosX(plane.getPosX() - 2);
            if (plane.getLive()) {
                plane.flyBack();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("you press up  ");
            plane.setPosY(plane.getPosY() - 4);
            if (plane.getLive()) {
                plane.fly();
            }

        }

    }

}
