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
public class Enemy extends Objects {

	private Rectangle rect;
	private boolean isBehindEnemy = false;
	private int score = 0;

	/**
	 * Constructor
	 */
	public Enemy(int x, int y, int w, int h) {
		super(x, y, w, h);
		rect = new Rectangle(x, y, w, h);
	}

	public void update() throws InterruptedException {

		if (score > 5) {
			setPosX(getPosX() - 25);
		} else if (score > 20) {
			setPosX(getPosX() - 100);
		} else if (score > 15) {
			setPosX(getPosX() - 35);
		} else {
			setPosX(getPosX() - 15);
		}
		// setPosX(getPosX() - 10);
		rect.setLocation((int) this.getPosX(), (int) this.getPosY());
	}

	public Rectangle getRectangle() {
		return rect;
	}

	public void setBehindEnemy(boolean b) {
		isBehindEnemy = b;
	}

	public boolean getBehindEnemy() {
		score++;
		return isBehindEnemy;
	}

}
