package main.gameScreens;

import controllers.ControllerController;
import controllers.PlayerController;
import main.GameConfig;
import main.GameMap;
import main.LevelManager;
import utilities.Utils;

import javax.naming.ldap.Control;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.logging.Level;

public class PlayGameScreen extends GameScreen {

    public static int MAP_LEFT = 213, MAP_TOP = 80;
    private LevelManager levelManager;

    public Image backdrop = null;
    public static int exitX = 0, exitY = 0; // position of the exit on game map grid
    public static Image exitImage;

    public PlayGameScreen() {
        backdrop = Utils.getImage("backdrop.jpg");
    }

    @Override
    public void init() {
        levelManager = LevelManager.getInstance();
    }

    // update the game graphics
    @Override
    public void update(Graphics g) {
        g.drawImage(backdrop, 0, 0, GameConfig.BACKGROUND_WIDTH, GameConfig.BACKGROUND_HEIGHT, null);

        BufferedImage gameMap = new BufferedImage(360, 360, BufferedImage.TYPE_INT_ARGB);
        Graphics gameMapGraphics = gameMap.getGraphics();
        GameMap.getInstance().draw(gameMapGraphics);
        ControllerController.instance.draw(gameMapGraphics);
        drawExit(g);

        g.drawImage(gameMap, MAP_LEFT, MAP_TOP, 360, 360, null);
    }

    // we draw the exit separately because the exit does not belong to the map where
    // the player and the enemy units move around
    private void drawExit(Graphics g) {
        int tileLength = GameMap.getInstance().getTILE_LENGTH();
        int cornerX = MAP_LEFT + (exitX - 1) * tileLength;
        int cornerY = MAP_TOP + (exitY - 1) * tileLength;
        g.drawImage(exitImage, cornerX, cornerY, tileLength, tileLength, null);
    }

    //
    @Override
    public void run() {
        if (levelManager == null) init();
        levelManager.run();
        ControllerController.instance.run();
    }

    //**************************   INPUT WITH KEYBOARD
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
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
