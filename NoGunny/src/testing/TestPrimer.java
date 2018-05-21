package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import warplane.Plane;
import warplane.WarPlane;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	GroundTest.class,
	PlaneEnemyTest.class,
	PlaneTest.class,
	WarPlaneTest.class,
	EnemyTest.class,
	MountainTest.class,
	
	
})
public class TestPrimer {
	//JUnitCore.runClasses(TestPrimer.class);
}
