package unittests.geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class to check all function of Plane class
 * 
 * @author David
 *
 */
class PlaneTest {
	/**
	 * test for constructor
	 */
	@Test
	void testConstructor() {
		// =============== Boundary Values Tests ==================

		// check constructor two point the same
		assertThrows(IllegalArgumentException.class,
				() -> new Plane(new Point(1, 2, 6.3), new Point(1, 2, 6.3), new Point(0, 0, 0)),
				"ERROR: ctor get two point the same");

		// check constructor all point on the same line
		assertThrows(IllegalArgumentException.class,
				() -> new Plane(new Point(1, 2, 6.3), new Point(2, 4, 12.6), new Point(0.5, 1, 3.15)),
				"ERROR: ctor get all point on the same line");
	}

	/**
	 * tests for getNormal(Point)
	 */
	@Test
	void testGetNormal() {
		// plane to tests
		Plane p = new Plane(new Point(1, 1, 0), new Point(2, 1, 0), new Point(1, 2, 0));

		// ============ Equivalence Partitions Tests ==============

		// test normal to plane (its can be 2 sides)
		boolean bool = new Vector(0, 0, 1).equals(p.getNormal(new Point(3, 2, 0)))
				|| new Vector(0, 0, -1).equals(p.getNormal(new Point(3, 2, 0)));
		assertTrue(bool, " ERROR: getNormal() worng result");
	}
}