/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgamesframework;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author duanp
 */
public class GameScreenTest {
    
    public GameScreenTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of RegisterImage method, of class GameScreen.
     */
    @Test
    public void testRegisterImage() {
        System.out.println("RegisterImage");
        int id = 0;
        BufferedImage image = null;
        GameScreen instance = new GameScreenImpl();
        instance.RegisterImage(id, image);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getImageWithID method, of class GameScreen.
     */
    @Test
    public void testGetImageWithID() {
        System.out.println("getImageWithID");
        int id = 0;
        GameScreen instance = new GameScreenImpl();
        BufferedImage expResult = null;
        BufferedImage result = instance.getImageWithID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of beginGame method, of class GameScreen.
     */
    @Test
    public void testBeginGame() {
        System.out.println("beginGame");
        GameScreen instance = new GameScreenImpl();
        instance.beginGame();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of keyTyped method, of class GameScreen.
     */
    @Test
    public void testKeyTyped() {
        System.out.println("keyTyped");
        KeyEvent e = null;
        GameScreen instance = new GameScreenImpl();
        instance.keyTyped(e);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of keyPressed method, of class GameScreen.
     */
    @Test
    public void testKeyPressed() {
        System.out.println("keyPressed");
        KeyEvent e = null;
        GameScreen instance = new GameScreenImpl();
        instance.keyPressed(e);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of keyReleased method, of class GameScreen.
     */
    @Test
    public void testKeyReleased() {
        System.out.println("keyReleased");
        KeyEvent e = null;
        GameScreen instance = new GameScreenImpl();
        instance.keyReleased(e);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of gameUpdate method, of class GameScreen.
     */
    @Test
    public void testGameUpdate() {
        System.out.println("gameUpdate");
        long deltaTime = 0L;
        GameScreen instance = new GameScreenImpl();
        instance.gameUpdate(deltaTime);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of gamePaint method, of class GameScreen.
     */
    @Test
    public void testGamePaint() {
        System.out.println("gamePaint");
        Graphics2D g2 = null;
        GameScreen instance = new GameScreenImpl();
        instance.gamePaint(g2);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of keyAction method, of class GameScreen.
     */
    @Test
    public void testKeyAction() {
        System.out.println("keyAction");
        KeyEvent e = null;
        int Event = 0;
        GameScreen instance = new GameScreenImpl();
        instance.keyAction(e, Event);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    public class GameScreenImpl extends GameScreen {

        public void gameUpdate(long deltaTime) {
        }

        public void gamePaint(Graphics2D g2) {
        }

        public void keyAction(KeyEvent e, int Event) {
        }
    }
    
}
