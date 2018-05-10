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
import java.io.File;
import java.io.IOException;
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
    private int score = 0;

    private Ground ground;
    private Mountain mountain;
    private PlaneEnemy planeEnemy;
    private int beginGame = 0;
    private int playGame = 1;
    private int overGame = 2;
    Font myFont = new Font("Serif", Font.BOLD, 20);

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

//    	enemyAni = new Animation(100);
//        AFrameOnImage g;
//       g = new AFrameOnImage(0, 0, 176, 140);
//     AFrameOnImage g;
//      g = new AFrameOnImage(176, 0, 176, 140);
//      AFrameOnImage g;
//      g = new AFrameOnImage(353, 0, 176, 140);
//       AFrameOnImage g;
//        g = new AFrameOnImage(176, 0, 176, 140);
//       enemy = new Enemy(0,0,0,0);        
//        
        planeEnemy = new PlaneEnemy();
        ground = new Ground();
        mountain = new Mountain();

        beginGame();
    }

    public static void main(String[] args) {
        new WarPlane();
        // new PlaneEnemy();
    }

    private void resetGame() {
        plane.setPos(110, 250);
        plane.setVt(0);
        plane.setLive(true);
        score=0;
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
            /// enemyAni.updateMe(deltaTime);

            plane.update(deltaTime);
            ground.update();
            mountain.Update();
            planeEnemy.update();

            for (int i = 0; i < PlaneEnemy.size; i++) {
                if (plane.getRectangle().intersects(planeEnemy.getEnemy(i).getRectangle())) {
                    plane.setLive(false);
                    System.out.println("set Live = false");
                }
            }

            for (int i = 0; i < planeEnemy.size; i++) {
                if (plane.getPosX() > planeEnemy.getEnemy(i).getPosX() && !planeEnemy.getEnemy(i).getBehindEnemy()) {
                    score++;
                    planeEnemy.getEnemy(i).setBehindEnemy(true);
                }
            }

            ///va cham ground
            if (plane.getPosY() + plane.getH() > ground.getYGround()) {
                currentScreen = overGame;
            }
            if (plane.getPosY() + plane.getH() < 0) {
                currentScreen = overGame;
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
//        
//        if (enemy!=null){
//        	 enemyAni.paintAnims((int)enemy.getPosX(), (int)enemy.getPosY(), enemyImage, g2, 0, 0);
//        }
//       

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
            g2.drawString("Press space to turn back begin screen", 250, 300);
        }
        g2.setColor(Color.RED);
        g2.setFont(myFont);
        g2.drawString("Score:" + score, 20, 30);
    }

    @Override
    public void keyAction(KeyEvent e, int Event) {
        if (Event == keyPressed) {
            if (currentScreen == beginGame) {
                currentScreen = playGame;
            } else if (currentScreen == playGame) {
                if (plane.getLive()) {
                    plane.fly();
                }
            } else if (currentScreen == overGame) {
                currentScreen = beginGame;
            }
        }

    }

}
