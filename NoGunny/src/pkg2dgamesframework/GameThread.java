/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgamesframework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import pkg2dgamesframework.GameScreen;

/**
 *
 * @author phamn
 */
public class GameThread extends JPanel implements Runnable {

    private GameScreen context;
    private Thread thread;
    private Graphics ThisGraphics;
    public static int FPS = 70;
    private BufferedImage buffImage;
    private int MasterWidth, MasterHeight;
    public static float scaleX_ = 1, scaleY_ = 1;

    public GameThread(GameScreen context) {
        this.context = context;
        MasterWidth = context.customWidth;
        MasterHeight = context.customHeight;
        this.thread = new Thread(this);
    }

    public void startThread() {
        thread.start();
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, context.customWidth, context.customHeight);
        Graphics2D g2 = (Graphics2D) g;
        if (buffImage != null) {
            g2.scale(scaleX_, scaleY_);
            g2.drawImage(buffImage, 0, 0, this);
        }
    }

    private void updateSize() {
        if (this.getWidth() <= 0) {
            return;
        }
        context.customWidth = this.getWidth();
        context.customHeight = this.getHeight();

        scaleX_ = (float) context.customWidth / (float) MasterWidth;
        scaleY_ = (float) context.customHeight / (float) MasterHeight;
    }

    @Override
    public void run() {
        long T = 1000 / FPS;
        long TimeBuffer = T / 2;
        long BeginTime = System.currentTimeMillis();
        long EndTime;
        long sleepTime;

        while (true) {
            updateSize();
            context.gameUpdate(System.currentTimeMillis());
            try {
                buffImage = new BufferedImage(MasterWidth, MasterHeight, BufferedImage.TYPE_INT_ARGB);
                if (buffImage == null) {
                    return;
                }
                Graphics2D g2 = (Graphics2D) buffImage.getGraphics();
                if (g2 != null) {
                    context.gamePaint(g2);
                }

            } catch (Exception myException) {
                myException.printStackTrace();
            }
            repaint();
            EndTime = System.currentTimeMillis();
            sleepTime = T - (EndTime - BeginTime);
            if (sleepTime < 0) {
                sleepTime = TimeBuffer;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
            }
            BeginTime = System.currentTimeMillis();
        }
    }
}
