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
class Enemy extends Objects {

    private Rectangle rect;
    private boolean isBehindEnemy = false;

    public Enemy(int x, int y, int w, int h) {
        super(x, y, w, h);
        rect = new Rectangle(x, y, w, h);

    }

    public void update() {
        setPosX(getPosX() - 2);
        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }

    public Rectangle getRectangle() {
        return rect;
    }

    public void setBehindEnemy(boolean b) {
        isBehindEnemy = b;
    }

    public boolean getBehindEnemy() {
        return isBehindEnemy;
    }

}
