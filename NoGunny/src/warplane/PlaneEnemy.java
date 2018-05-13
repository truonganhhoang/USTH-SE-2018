/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warplane;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

import javafx.animation.Animation;
import pkg2dgamesframework.QueueList;

/**
 *
 * @author duanp
 */
public class PlaneEnemy {

    private QueueList<Enemy> enemysQueue;
    private BufferedImage enemyImage;
    public static int size = 6;

    public int getRandomY() {
        Random random = new Random();
        int a;
        a = random.nextInt(200);
        return a;
    }

    public Enemy getEnemy(int i) {
        return enemysQueue.get(i);
    }
    public PlaneEnemy() {

        try {
            enemyImage = ImageIO.read(new File("Assets/Enemy2.png"));
        } catch (IOException ex) {
        }
        Enemy enemy;
        enemysQueue = new QueueList<Enemy>();
       
            int deltaY = getRandomY();
            enemy = new Enemy(830 +  300, -350 + deltaY, 93, 64);
            enemysQueue.push(enemy);

            enemy = new Enemy(830 + 300, 200 + deltaY, 93, 64);
            enemysQueue.push(enemy);
        

    }

    public void resetEnemy() {
        enemysQueue = new QueueList<Enemy>();
        Enemy enemy;
        for (int i = 0; i < size / 2; i++) {
            int deltaY = getRandomY();
            enemy = new Enemy(830 + i * 300, -100 + deltaY, 93, 64);
            enemysQueue.push(enemy);

            enemy = new Enemy(830 + i * 200, 200 + deltaY, 93, 64);
            enemysQueue.push(enemy);
        }
    }

    public void update() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            enemysQueue.get(i).update();
        }
        if (enemysQueue.get(0).getPosX() < -400) {
            int deltaY = getRandomY();
            Enemy enemy;
            enemy = enemysQueue.pop();
            enemy.setPosX(enemysQueue.get(4).getPosX() + 350);
            enemy.setPosY(-100 + deltaY);
            enemy.setBehindEnemy(false);
            enemysQueue.push(enemy);

            enemy = enemysQueue.pop();
            enemy.setPosX(enemysQueue.get(4).getPosX() + 350);
            enemy.setPosY(deltaY + 250);
            enemy.setBehindEnemy(false);
            enemysQueue.push(enemy);
        }
    }

    public void paint(Graphics2D g2) {
        for (int i = 0; i < 4; i++) {
            g2.drawImage(enemyImage, (int) enemysQueue.get(i).getPosX(), (int) enemysQueue.get(i).getPosY(), null);
        }

    }

}
