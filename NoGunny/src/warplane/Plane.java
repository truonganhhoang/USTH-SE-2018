/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warplane;

import pkg2dgamesframework.Objects;

/**
 *
 * @author duanp
 */
public class Plane extends Objects{
    private float vt = 0;

    private boolean isFlying = false;  ///fall down effect

    public Plane(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public void setVt(float vt) {////va cham
        this.vt = vt;
    }

    

    public void update(long deltaTime) {
        vt += WarPlane.g;

        this.setPosY(this.getPosY() + vt);
        if (vt < 0) {
            isFlying = true;///updown
        } else {
            isFlying = false;
        }

    }

    public void fly() {
        vt = -3;
    }

    public boolean getIsFlying() {////updown
        return isFlying;
    }

}
