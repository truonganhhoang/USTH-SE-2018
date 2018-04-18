package main.gameScreens;

import controllers.ControllerController;
import controllers.PlayerController;
import main.GameConfig;
import main.GameMap;
import utilities.Utils;

import javax.naming.ldap.Control;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class PlayGameScreen extends GameScreen {

    public static boolean[][] wallDown = new boolean[12][12];
    public static boolean[][] wallRight = new boolean[12][12];
    public static int MAP_LEFT = 213, MAP_TOP = 80;

    public Image backdrop = null;
    public static int exitX = 0, exitY = 0; // chứa tọa độ trên bản đồ game
    public static int screenExitX = 0, screenExitY = 0; // TỌA DỘ EXIT TRÊN MÀN HÌNH
    public Image exitImage;

    public PlayGameScreen() {
        backdrop = Utils.getImage("backdrop.jpg");
    }

    @Override
    public void init() {

    }

    @Override
    public void update(Graphics g) {
        g.drawImage(backdrop,0,0, GameConfig.BACKGROUND_WIDTH,GameConfig.BACKGROUND_HEIGHT,null);

        BufferedImage gameMap = new BufferedImage(360, 360, BufferedImage.TYPE_INT_ARGB);
        Graphics gameMapGraphics = gameMap.getGraphics();
        GameMap.getInstance().draw(gameMapGraphics);
        ControllerController.instance.draw(gameMapGraphics);

        g.drawImage(gameMap,MAP_LEFT,MAP_TOP,360,360,null);
    }

    @Override
    public void run() {
        ControllerController.instance.run();
    }




    //**************************   INPUT WITH KEYBOARD
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("aa");
        PlayerController.getInstance().keyInputListener.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        PlayerController.getInstance().keyInputListener.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
