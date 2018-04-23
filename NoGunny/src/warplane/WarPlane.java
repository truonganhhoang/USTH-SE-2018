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

    private BufferedImage planes;
    private Animation planeAni;

    public static float g = 0.1f;

    private Plane plane;
    private Ground ground;
    private Mountain mountain;
    private int beginGame = 0;
    private int playGame = 1;
    private int overGame = 2;
    Font myFont = new Font("Serif", Font.BOLD, 20);

    private int currentScreen = beginGame;

    public WarPlane() {
        super(800, 600);
        try {
            planes = ImageIO.read(new File("Assets/phane2.gif"));
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

    }

    @Override
    public void gameUpdate(long deltaTime) {

//        planeAni.updateMe(deltaTime);
//        plane.update(deltaTime);
        if (currentScreen == beginGame) {
            resetGame();////va cham
        } else if (currentScreen == playGame) {
            planeAni.updateMe(deltaTime);
            plane.update(deltaTime);
            ground.Update();
            mountain.Update();
            ///va cham ground
            if (plane.getPosY() + plane.getH() > ground.getYGround()) {
                currentScreen = overGame;
            }

        } else if (currentScreen == overGame) {

        }
    }

    @Override
    public void gamePaint(Graphics2D g2) {

//        if (plane != null) {
//            planeAni.paintAnims((int) plane.getPosX(), (int) plane.getPosY(), planes, g2, 0, 0);
//        }
//        
        ground.Paint(g2);
        mountain.Paint(g2);

        if (plane != null) {
            planeAni.paintAnims((int) plane.getPosX(), (int) plane.getPosY(), planes, g2, 0, 0);
        }

        //if(phane.getIsFlying()) phane_ani.PaintAnims((int)phane.getPosX(), (int)phane.getPosY(), phanes, g2,0, 0);
        //else phane_ani.PaintAnims((int)phane.getPosX(), (int)phane.getPosY(), phanes, g2,0, 0.1f);
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

    }

    @Override
    public void keyAction(KeyEvent e, int Event) {
//        if (Event == keyPressed) {
//            plane.fly();
//        }
        if (Event == keyPressed) {
            if (currentScreen == beginGame) {
                currentScreen = playGame;
            } else if (currentScreen == playGame) {
                plane.fly();
            } else if (currentScreen == overGame) {
                currentScreen = beginGame;
            }

        }

    }

}
