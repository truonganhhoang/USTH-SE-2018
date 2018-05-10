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
    private BufferedImage enemyImage2;
    private Animation enemyAnimation;

    Random rand = new Random();
    int n = rand.nextInt(100) + 1;

    public static int size = 3;

    public int getRandomY() {
        Random random = new Random();
        int a;
        a = random.nextInt(100);
        return a;
    }

    public Enemy getEnemy(int i) {
        return enemysQueue.get(i);
    }

    public PlaneEnemy() {

        try {
            enemyImage = ImageIO.read(new File("Assets/Enemy2.png"));
            enemyImage2 = ImageIO.read(new File("Assets/noo.png"));

        } catch (IOException ex) {
        }
        Enemy enemy;
        enemysQueue = new QueueList<Enemy>();

        for (int i = 0; i < 3; i++) {

            int deltaY = getRandomY();
            enemy = new Enemy(830 + i * 300, 350 + deltaY, 93, 64);
            enemysQueue.push(enemy);

            enemy = new Enemy(830 + i * 300, deltaY, 93, 64);
            enemysQueue.push(enemy);

        }

    }

    public void resetEnemy() {
        enemysQueue = new QueueList<Enemy>();
        Enemy enemy;

        for (int i = 0; i < 3; i++) {
            int deltaY = getRandomY();
            enemy = new Enemy(830 + i * 300, 350 + deltaY, 93, 64);
            enemysQueue.push(enemy);

            enemy = new Enemy(830 + i * 300, deltaY, 93, 64);
            enemysQueue.push(enemy);
        }
    }

    public void update() {
        for (int i = 0; i < 3; i++) {
            enemysQueue.get(i).update();
        }

        if (enemysQueue.get(0).getPosX() < -70) {

            int deltaY = getRandomY();
            Enemy enemy;
            enemy = enemysQueue.pop();
            enemy.setPosX(enemysQueue.get(4).getPosX() + 300);
            enemy.setPosY(350 + deltaY);
            enemy.setBehindEnemy(false);
            enemysQueue.push(enemy);

        }

    }

    public void paint(Graphics2D g2) {
        for (int i = 0; i < 3; i++) {

            g2.drawImage(enemyImage, (int) enemysQueue.get(i).getPosX(), (int) enemysQueue.get(i).getPosY(), null);

        }

    }

}
