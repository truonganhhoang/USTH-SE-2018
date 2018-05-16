package testing;

import org.junit.BeforeClass;
import org.junit.Test;


import warplane.WarPlane;

public class WarPlaneTest {

	private static WarPlane warPlane;

	@BeforeClass
	public static void prepareForAllTest() {
		warPlane = new WarPlane();
	}

	/**
	 * Test of gameUpdate method, of class WarPlane.
	 */
	@Test
	public void updateTest() {
		System.out.println("gameUpdate");
		long deltaTime = 0L;
		warPlane.gameUpdate(deltaTime);
	}

	/**
	 * Test of main method, of class WarPlane.
	 */
	@Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        WarPlane.main(args);
    }

	
}
