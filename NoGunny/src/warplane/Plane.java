/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warplane;

import java.awt.Rectangle;
import java.io.File;
import pkg2dgamesframework.Objects;
import pkg2dgamesframework.SoundPlayer;

/**
 *
 * @author duanp
 */
public class Plane extends Objects {

    private float vt = 0; //velocity of gravity
    private Rectangle rect;
    private boolean isLive = true;
    private boolean isFlying = false;  ///fall down effect

    SoundPlayer contactSound;
    SoundPlayer planeSound;
    SoundPlayer introSound;

    /**
     * Constructor
     */
    public Plane(int x, int y, int w, int h) {
        super(x, y, w, h);
        rect = new Rectangle(x, y, w, h);
        contactSound = new SoundPlayer(new File("Assets/contact.wav"));
        //planeSound= new SoundPlayer(new File("Assets/dongco.wav"));
        introSound = new SoundPlayer(new File("Assets/start3.wav"));
    }

    public Rectangle getRectangle() {
        return rect;
    }

    public void setLive(boolean b) {
        // if(isLive =true || b==false) contactSound.play();
        isLive = b;
    }

    public boolean getLive() {
        return isLive;
    }

    public void setVt(float vt) {//contact
        this.vt = vt;
    }
    

    public void update(long deltaTime) {
        vt += WarPlane.g;
        this.setPosY(this.getPosY() + vt);
        this.setPosX(this.getPosX() - vt);
        this.rect.setLocation((int) this.getPosX(), (int) this.getPosY());
        if (vt < 0) {
            isFlying = true;//updown
        } else {
            isFlying = false;
        }
    }

    public void fly() {
        vt = -3;
        //planeSound.play();      
    }

    public void flyBack() {
        vt = -(long) 0.2;
    }

    public boolean getIsFlying() {//updown
        return isFlying;
    }

}
