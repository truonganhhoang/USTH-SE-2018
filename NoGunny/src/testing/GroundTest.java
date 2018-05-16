package testing;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import warplane.Ground;

public class GroundTest {
	private static Ground testGround;

	@BeforeClass
	public static void prepareForAllTest() {
		testGround = new Ground();
	}

	/**
	 * Test of update method, of class Ground.
	 */
	@Test
	public void testUpdate() {
		System.out.println("update");
		testGround.update();
	}

	/**
	 * Test of Paint method, of class Ground.
	 */
	@Test
	public void testPaint() {
		System.out.println("Paint");
	}

	/**
	 * Test of getYGround method, of class Ground.
	 */
	@Test
	public void testGetYGround() {
		System.out.println("getYGround");
		int expResult = 470;
		int result = testGround.getYGround();
		assertEquals(expResult, result);

	}

}
