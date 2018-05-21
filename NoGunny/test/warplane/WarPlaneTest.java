/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warplane;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author duanp
 */
public class WarPlaneTest {
    
    public WarPlaneTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
         
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of main method, of class WarPlane.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        WarPlane.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gameUpdate method, of class WarPlane.
     */
    @Test
    public void testGameUpdate() {
        System.out.println("gameUpdate");
        long deltaTime = 0L;
        WarPlane instance = new WarPlane();
        instance.gameUpdate(deltaTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gamePaint method, of class WarPlane.
     */
    @Test
    public void testGamePaint() {
        System.out.println("gamePaint");
        Graphics2D g2 = null;
        WarPlane instance = new WarPlane();
        instance.gamePaint(g2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyAction method, of class WarPlane.
     */
    @Test
    public void testKeyAction() {
        System.out.println("keyAction");
        KeyEvent e = null;
        int Event = 0;
        WarPlane instance = new WarPlane();
        instance.keyAction(e, Event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
