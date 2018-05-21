/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgamesframework;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author phamn
 */
public abstract class GameScreen extends JFrame implements KeyListener{

    public static int keyPressed = 0;
    public static int keyReleased = 1; 
    public int  customWidth  = 500;
    public int  customHeight = 500;
    private GameThread gThread;
    public static int masterWidth = 500, masterHeight = 500;
    
    public GameScreen(){
        InitThread();
        InitScreen();
    }
    
    public void RegisterImage(int id, BufferedImage image){
    }
    
    public BufferedImage getImageWithID(int id){
        return null;
    }
    
    public GameScreen(int w, int h){
        this.customWidth  = w;
        this.customHeight= h;
        masterWidth = customWidth ;
        masterHeight = customHeight;
        InitThread();
        InitScreen();
    }
    
    private void InitScreen(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        setSize(customWidth,customHeight);
        setVisible(true);  
    }
    
    public void beginGame(){
        gThread.startThread();
    }
    
    private void InitThread(){
        gThread = new GameThread(this);
        add(gThread);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keyAction(e, GameScreen.keyPressed);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyAction(e, GameScreen.keyReleased);
    }
    
    public abstract void gameUpdate(long deltaTime);
    public abstract void gamePaint(Graphics2D g2);
    public abstract void keyAction(KeyEvent e, int Event);
    
}

