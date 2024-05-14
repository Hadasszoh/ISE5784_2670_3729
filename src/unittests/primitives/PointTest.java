package unittests.primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import primitives.*;

/**
 * test class to check all function of Point class
 * 
 */
class PointTest {
	Point p1 = new Point(1, 1, 1);
	Point p2 = new Point(2, 2, 2);
	Vector v1 = new Vector(-1, -1, -1);

	/**
	 * checking if the adding work correctly
	 */
	@Test
	void testAdd() {
		// =======Equivalence Partitions Tests=======
		// TC01: testing the adding of point and vector (simple test)
		assertEquals(p1.add(v1), new Point(0, 0, 0), "the adding does not work correctly");
	}

	/**
	 * checking if the subtracting work correctly
	 */
	@Test
	void testSubtract() {
		// =======Equivalence Partitions Tests=======
		// TC01: testing the subtract between two points (simple test)
		assertEquals(p2.subtract(p1), new Vector(1, 1, 1), "ERROR: TC01");

		// ======== subtraction of a point itself ========
		assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "the subtracting does not work correctly");
	}

	/**
	 * checking if the DistanceSquared function work correctly
	 */
	@Test
	void testDistanceSquared() {
		// =======Equivalence Partitions Tests=======
		// TC01: testing if the distance between two points is correct (simple test)
		assertEquals(3, p1.distanceSquared(p2), "ERROR: DistanceSquared doesn't work correctly");
	}

	/**
	 * checking if the Distance func work coectly
	 */
	@Test
	void testDistance() {
		// =======Equivalence Partitions Tests=======
		// TC01: testing if the distance between two points -with sqrt- is correct
		// (simple test)
		assertEquals(Math.sqrt(3), p1.distance(p2), "ERROR: Distance doesn't work correctly");
	}
}