/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warplane;

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
public class WarPlane extends GameScreen{
private BufferedImage planes;
    private Animation plane_ani;

    public static float g = 0.1f;

    private Plane plane;

    public WarPlane() {
        super(800, 600);
        try {
            planes = ImageIO.read(new File("Assets/phane2.gif"));
        } catch (IOException ex) {
        }

        plane_ani = new Animation(100);
        AFrameOnImage f;
        f = new AFrameOnImage(0, 0, 120, 68);
        plane_ani.AddFrame(f);
        f = new AFrameOnImage(120, 0, 120, 68);
        plane_ani.AddFrame(f);
        f = new AFrameOnImage(240, 0, 120, 68);
        plane_ani.AddFrame(f);
        f = new AFrameOnImage(360, 0, 120, 68);
        plane_ani.AddFrame(f);
        f = new AFrameOnImage(240, 0, 120, 68);
        plane_ani.AddFrame(f);
        f = new AFrameOnImage(120, 0, 120, 68);
        plane_ani.AddFrame(f);

        plane = new Plane(100, 250, 50, 50);

        BeginGame();
    }

    public static void main(String[] args) {
        new WarPlane();
    }

    @Override
    public void GAME_UPDATE(long deltaTime) {
        plane_ani.Update_Me(deltaTime);

    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {

        if (plane != null) {
            plane_ani.PaintAnims((int) plane.getPosX(), (int) plane.getPosY(), planes, g2, 0, 0);
        }

    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {

    }
    
}
