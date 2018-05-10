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
    private Animation enemyAnimation;
    
  
    
    Random rand = new Random();
    int n = rand.nextInt(100) + 1;

    public static int size = 3;

    public Enemy getEnemy(int i) {
        return enemysQueue.get(i);
    }
    
    

    public PlaneEnemy() {
        enemysQueue = new QueueList<Enemy>();
        try {
            enemyImage = ImageIO.read(new File("Assets/Enemy2.png"));

        } catch (IOException ex) {
        }
        Enemy enemy;

        for (int i = 0; i < 3; i++) {
            enemy = new Enemy(830 + i * 300, 350, 93, 64);
            enemysQueue.push(enemy);

            enemy = new Enemy(830 + i * 300, n, 93, 64);
            enemysQueue.push(enemy);

        }
    }

    public void update() {
        for (int i = 0; i < 3; i++) {
            enemysQueue.get(i).update();
        }

        if (enemysQueue.get(0).getPosX() < -70) {
            Enemy enemy;
            enemy = enemysQueue.pop();
            enemy.setPosX(enemysQueue.get(4).getPosX() + 300);
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
