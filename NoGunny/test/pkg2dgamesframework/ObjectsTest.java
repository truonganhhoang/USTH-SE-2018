/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgamesframework;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author duanp
 */
public class ObjectsTest {
    
    public ObjectsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of isCollisionHappenWith method, of class Objects.
     */
    @Test
    public void testIsCollisionHappenWith_float_float() {
        System.out.println("isCollisionHappenWith");
        float x = 0.0F;
        float y = 0.0F;
        Objects instance = new Objects();
        boolean expResult = false;
        boolean result = instance.isCollisionHappenWith(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCollisionHappenWith method, of class Objects.
     */
    @Test
    public void testIsCollisionHappenWith_4args() {
        System.out.println("isCollisionHappenWith");
        float x = 0.0F;
        float y = 0.0F;
        float w = 0.0F;
        float h = 0.0F;
        Objects instance = new Objects();
        boolean expResult = false;
        boolean result = instance.isCollisionHappenWith(x, y, w, h);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPos method, of class Objects.
     */
    @Test
    public void testSetPos() {
        System.out.println("setPos");
        float x = 0.0F;
        float y = 0.0F;
        Objects instance = new Objects();
        instance.setPos(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosX method, of class Objects.
     */
    @Test
    public void testSetPosX() {
        System.out.println("setPosX");
        float x = 0.0F;
        Objects instance = new Objects();
        instance.setPosX(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosY method, of class Objects.
     */
    @Test
    public void testSetPosY() {
        System.out.println("setPosY");
        float y = 0.0F;
        Objects instance = new Objects();
        instance.setPosY(y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosX method, of class Objects.
     */
    @Test
    public void testGetPosX() {
        System.out.println("getPosX");
        Objects instance = new Objects();
        float expResult = 0.0F;
        float result = instance.getPosX();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosY method, of class Objects.
     */
    @Test
    public void testGetPosY() {
        System.out.println("getPosY");
        Objects instance = new Objects();
        float expResult = 0.0F;
        float result = instance.getPosY();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getW method, of class Objects.
     */
    @Test
    public void testGetW() {
        System.out.println("getW");
        Objects instance = new Objects();
        float expResult = 0.0F;
        float result = instance.getW();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getH method, of class Objects.
     */
    @Test
    public void testGetH() {
        System.out.println("getH");
        Objects instance = new Objects();
        float expResult = 0.0F;
        float result = instance.getH();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of increasePosX method, of class Objects.
     */
    @Test
    public void testIncreasePosX() {
        System.out.println("increasePosX");
        float m = 0.0F;
        Objects instance = new Objects();
        instance.increasePosX(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of increasePosY method, of class Objects.
     */
    @Test
    public void testIncreasePosY() {
        System.out.println("increasePosY");
        float m = 0.0F;
        Objects instance = new Objects();
        instance.increasePosY(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
