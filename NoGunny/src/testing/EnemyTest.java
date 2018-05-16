package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import warplane.Enemy;

public class EnemyTest {
	private static Enemy testEnemy;

	@BeforeClass
	public static void prepareForAllTest() {
		testEnemy = new Enemy(0, 0, 0, 0);
	}

	/**
	 * Test of update method, of class Enemy.
	 */
	@Test
	public void testUpdate() throws Exception {
		System.out.println("update");
		testEnemy.update();		
	}
	 /**
     * Test of getRectangle method, of class Enemy.
     */
    @Test
	public void testGetRectangle() {
		System.out.println("getRectangle");
		java.awt.Rectangle result = testEnemy.getRectangle();
		assertTrue(testEnemy.getRectangle().equals(result));
	}
    /**
     * Test of setBehindEnemy method, of class Enemy.
     */
    @Test
    public void testSetBehindEnemy() {
        System.out.println("setBehindEnemy");
        boolean b = false;
        testEnemy.setBehindEnemy(b);
    }
    /**
     * Test of getBehindEnemy method, of class Enemy.
     */
    @Test
    public void testGetBehindEnemy() {
        System.out.println("getBehindEnemy");
        boolean expResult = false;
        boolean result = testEnemy.getBehindEnemy();
        assertEquals(expResult, result);
    }
    

}
