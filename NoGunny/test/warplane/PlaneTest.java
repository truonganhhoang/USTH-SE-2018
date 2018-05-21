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
public class PlaneTest {
    
    public PlaneTest() {
    }
    @Test
    public void getLiveTest(){
       //Plane planeTest = new Plane();
       //assertTrue(planeTest.getLive());
    }
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getRectangle method, of class Plane.
     */
    @Test
    public void testGetRectangle() {
        System.out.println("getRectangle");
        Plane instance = null;
        Rectangle expResult = null;
        Rectangle result = instance.getRectangle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLive method, of class Plane.
     */
    @Test
    public void testSetLive() {
        System.out.println("setLive");
        boolean b = false;
        Plane instance = null;
        instance.setLive(b);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLive method, of class Plane.
     */
    @Test
    public void testGetLive() {
        System.out.println("getLive");
        Plane instance = null;
        boolean expResult = false;
        boolean result = instance.getLive();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVt method, of class Plane.
     */
    @Test
    public void testSetVt() {
        System.out.println("setVt");
        float vt = 0.0F;
        Plane instance = null;
        instance.setVt(vt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class Plane.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        long deltaTime = 0L;
        Plane instance = null;
        instance.update(deltaTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fly method, of class Plane.
     */
    @Test
    public void testFly() {
        System.out.println("fly");
        Plane instance = null;
        instance.fly();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of flyBack method, of class Plane.
     */
    @Test
    public void testFlyBack() {
        System.out.println("flyBack");
        Plane instance = null;
        instance.flyBack();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsFlying method, of class Plane.
     */
    @Test
    public void testGetIsFlying() {
        System.out.println("getIsFlying");
        Plane instance = null;
        boolean expResult = false;
        boolean result = instance.getIsFlying();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
