/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warplane;

import java.awt.Rectangle;
import pkg2dgamesframework.Objects;

/**
 *
 * @author duanp
 */
public class Plane extends Objects {

    private float vt = 0; //velocity of gravity
    private float vt2 = 0; 
    private Rectangle rect;
    private boolean isLive = true;


    private boolean isFlying = false;  ///fall down effect

    public Plane(int x, int y, int w, int h) {
        super(x, y, w, h);
        rect = new Rectangle(x, y, w, h);
    }

    public Rectangle getRectangle() {
        return rect;
    }

    public void setLive(boolean b) {
        isLive = b;
    }

    public boolean getLive() {
        return isLive;
    }

    public void setVt(float vt) {////va cham
        this.vt = vt;
    }

    public void update(long deltaTime) {
        vt += WarPlane.g;
     

        this.setPosY(this.getPosY() + vt);
        this.setPosX(this.getPosX() - vt);
        this.rect.setLocation((int) this.getPosX(), (int) this.getPosY());

        if (vt < 0) {
            isFlying = true;///updown
        } else {
            isFlying = false;
        }
    }

    public void fly() {
        vt = -3;
    }
    public void flyBack(){
        vt=-(long) 0.2;
    }
   
    public boolean getIsFlying() {////updown
        return isFlying;
    }

}
