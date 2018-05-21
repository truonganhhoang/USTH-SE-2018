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
public class GroundTest {
    
    public GroundTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of update method, of class Ground.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Ground instance = new Ground();
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Paint method, of class Ground.
     */
    @Test
    public void testPaint() {
        System.out.println("Paint");
        Graphics2D g2 = null;
        Ground instance = new Ground();
        instance.Paint(g2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getYGround method, of class Ground.
     */
    @Test
    public void testGetYGround() {
        System.out.println("getYGround");
        Ground instance = new Ground();
        int expResult = 0;
        int result = instance.getYGround();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
