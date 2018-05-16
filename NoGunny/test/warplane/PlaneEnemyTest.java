/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warplane;

import java.awt.Graphics2D;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author duanp
 */
public class PlaneEnemyTest {
    
    public PlaneEnemyTest() {
        PlaneEnemy  y= new PlaneEnemy();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getRandomY method, of class PlaneEnemy.
     */
    @Test
    public void testGetRandomY() {
        System.out.println("getRandomY");
        PlaneEnemy instance = new PlaneEnemy();
        int expResult = 0;
        int result = instance.getRandomY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnemy method, of class PlaneEnemy.
     */
    @Test
    public void testGetEnemy() {
        System.out.println("getEnemy");
        int i = 0;
        PlaneEnemy instance = new PlaneEnemy();
        Enemy expResult = null;
        Enemy result = instance.getEnemy(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetEnemy method, of class PlaneEnemy.
     */
    @Test
    public void testResetEnemy() {
        System.out.println("resetEnemy");
        PlaneEnemy instance = new PlaneEnemy();
        instance.resetEnemy();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class PlaneEnemy.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        PlaneEnemy instance = new PlaneEnemy();
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paint method, of class PlaneEnemy.
     */
    @Test
    public void testPaint() {
        System.out.println("paint");
        Graphics2D g2 = null;
        PlaneEnemy instance = new PlaneEnemy();
        instance.paint(g2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
