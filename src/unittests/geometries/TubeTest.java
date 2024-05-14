package unittests.geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class to check all function of Tube class
 * 
 * @author David
 *
 */
class TubeTest {

	/**
	 * test get normal
	 */
	@Test
	void TestGetNormal() {
		Ray ray = new Ray(Point.ZERO, new Vector(0, 0, 1));
		Tube tube = new Tube(Math.sqrt(2), ray);

		// =============== Equivalence Partitions Tests ==============
		// TC01: simple check
		assertEquals(new Vector(1, 1, 0), tube.getNormal(new Point(1, 1, 2)), "the normal is not correct");

		// =============== Boundary Values Tests ==================
		// TC11: checking if the
		assertEquals(new Vector(1, 1, 0), tube.getNormal(new Point(1, 1, 1)), "the normal is not correct");
	}

	/**
	 * 
	 */
	@Test
	void TestFindIntersections() {

	}
}