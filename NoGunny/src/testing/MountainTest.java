package testing;

import java.awt.Graphics2D;

import org.junit.BeforeClass;
import org.junit.Test;

import warplane.Mountain;

public class MountainTest {

	private static Mountain testMountain;

	@BeforeClass
	public static void prepareForAllTest() {
		testMountain = new Mountain();
	}

	/**
	 * Test of Update method, of class Mountain.
	 */
	@Test
	public void testUpdate() {
		System.out.println("Update");
		testMountain.Update();
		// TODO review the generated test code and remove the default call to fail.
		// fail("The test case is a prototype.");
	}

	/**
	 * Test of Paint method, of class Mountain.
	 */
	@Test
	public void testPaint() {
		// System.out.println("Paint");
		// boolean g2= g2.drawImage(null, 0, 0, 0, 0, null);
		// assertfalse(testMountain.Paint(g2);)
		// // TODO review the generated test code and remove the default call to fail.
		// //fail("The test case is a prototype.");
	}
}
