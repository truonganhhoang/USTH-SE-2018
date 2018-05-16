package testing;

import warplane.Plane;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class PlaneTest {

	private static Plane testPlane;

	@BeforeClass
	public static void prepareForAllTest() {
		testPlane = new Plane(0, 0, 0, 0);
	}

	/**
	 * Test of getLive method, of class Plane.
	 */
	@Test
	public void tesGetLive() {
		System.out.println("test getlive..");
		assertTrue(testPlane.getLive());
	}

	/**
	 * Test of getIsFlying method, of class Plane.
	 */
	@Test
	public void tesGetISFlying() {
		System.out.println("test getIsFlying.");
		assertFalse(testPlane.getIsFlying());

	}

	/**
	 * Test of update method, of class Plane.
	 */
	@Test
	public void testUpdate() {

		System.out.println("update");
		long deltaTime = 0L;
		testPlane.update(deltaTime);
	}

	/**
	 * Test of setVt method, of class Plane.
	 */
	@Test
	public void testSetVt() {
		System.out.println("setVt");
		float vt = 0.0F;
		testPlane.setVt(vt);
	}

	/**
	 * Test of flyBack method, of class Plane.
	 */
	@Test
	public void testFlyBack() {
		System.out.println("flyBack");
		testPlane.flyBack();
		// TODO review the generated test code and remove the default call to fail.
		// fail("The test case is a prototype.");
	}

	/**
	 * Test of getRectangle method, of class Plane.
	 */
	@Test
	public void testGetRectangle() {
		System.out.println("getRectangle");
		java.awt.Rectangle result = testPlane.getRectangle();
		assertTrue(testPlane.getRectangle().equals(result));

	}

}
