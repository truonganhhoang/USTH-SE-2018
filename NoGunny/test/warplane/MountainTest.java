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
public class MountainTest {
    
    public MountainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of Update method, of class Mountain.
     */
    @Test
    public void testUpdate() {
        System.out.println("Update");
        Mountain instance = new Mountain();
        instance.Update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Paint method, of class Mountain.
     */
    @Test
    public void testPaint() {
        System.out.println("Paint");
        Graphics2D g2 = null;
        Mountain instance = new Mountain();
        instance.Paint(g2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
