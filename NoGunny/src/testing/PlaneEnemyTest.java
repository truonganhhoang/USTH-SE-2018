package testing;

import org.junit.BeforeClass;
import org.junit.Test;

import warplane.PlaneEnemy;

public class PlaneEnemyTest {
	private static PlaneEnemy planeEnemy;

	@BeforeClass
	public static void prepareForAllTest() {
		planeEnemy = new PlaneEnemy();
	}

	/**
	 * Test of getRandomY method, of class PlaneEnemy.
	 */
	@Test
	public void getRandomYTest() {
		System.out.println("getRandomY");
		planeEnemy.getRandomY();
	}

	/**
	 * Test of resetEnemy method, of class PlaneEnemy.
	 */
	@Test
	public void testResetEnemy() {
		System.out.println("resetEnemy");
		planeEnemy.resetEnemy();

	}

	/**
	 * Test of update method, of class PlaneEnemy.
	 */
	@Test
	public void testUpdate() throws Exception {
		System.out.println("update");
		// planeEnemy.update();
	}
}
