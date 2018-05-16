/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warplane;

import java.awt.Rectangle;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author duanp
 */
public class EnemyTest {
    
    public EnemyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of update method, of class Enemy.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Enemy instance = null;
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRectangle method, of class Enemy.
     */
    @Test
    public void testGetRectangle() {
        System.out.println("getRectangle");
        Enemy instance = null;
        Rectangle expResult = null;
        Rectangle result = instance.getRectangle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBehindEnemy method, of class Enemy.
     */
    @Test
    public void testSetBehindEnemy() {
        System.out.println("setBehindEnemy");
        boolean b = false;
        Enemy instance = null;
        instance.setBehindEnemy(b);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBehindEnemy method, of class Enemy.
     */
    @Test
    public void testGetBehindEnemy() {
        System.out.println("getBehindEnemy");
        Enemy instance = null;
        boolean expResult = false;
        boolean result = instance.getBehindEnemy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
