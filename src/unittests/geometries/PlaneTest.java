package unittests.geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to check all functions of the Plane class.
 * This class contains unit tests for the constructors and getNormal(Point) method of the Plane class.
 * 
 * @authord Hadass & Hodaya 
 * 
 */
class PlaneTest {

    /**
     * Test for the constructor of the Plane class.
     * This method tests the constructor for handling invalid input such as two identical points and collinear points.
     */
    @Test
    void testConstructor() {
        // =============== Boundary Values Tests ==================

        //TC:01 Check constructor with two identical points
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 2, 6.3), new Point(1, 2, 6.3), new Point(0, 0, 0)),
                "ERROR: TC01");

        // Check constructor with all points on the same line
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 2, 6.3), new Point(2, 4, 12.6), new Point(0.5, 1, 3.15)),
                "ERROR: Constructor should throw an exception when all points are on the same line");
    }

    /**
     * Test for the getNormal(Point) method of the Plane class.
     * This method tests the correctness of the normal vector calculation for the plane.
     */
    @Test
    void testGetNormal() {
        // Plane for tests
        Plane p = new Plane(new Point(1, 1, 0), new Point(2, 1, 0), new Point(1, 2, 0));

        // ============ Equivalence Partitions Tests ==============

        // Test normal to plane (it can be in 2 opposite directions)
        boolean bool = new Vector(0, 0, 1).equals(p.getNormal(new Point(3, 2, 0)))
                || new Vector(0, 0, -1).equals(p.getNormal(new Point(3, 2, 0)));
        assertTrue(bool, "ERROR: getNormal() returned the wrong result");
    }
    /**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntersections() {
		Sphere sphere = new Sphere(1d, new Point(1, 0, 0));

		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray's line is outside the sphere (0 points)
		assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))), "ERROR: TC01");

		// TC02: Ray starts before and crosses the sphere (2 points)
		Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
		Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
		List<Point> points = sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0)));

		if (points.get(0).getX() > points.get(1).getX())
			points = List.of(points.get(1), points.get(0));
		assertEquals(List.of(p1, p2), points, "ERROR: TC02");

		// TC03: Ray starts inside the sphere (1 point)
		points = sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)));
		assertEquals(List.of(new Point(1, 1, 0)), points, "Error TC03");

		// TC04: Ray starts after the sphere (0 points)
		assertNull(sphere.findIntersections(new Ray(new Point(10, 10, 10), new Vector(1, 1, 1))), "ERROR TC04");

		// =============== Boundary Values Tests ==================

		// ** Group: Ray'sphere line crosses the sphere (but not the center)
		// TC11: Ray starts at sphere and goes inside (1 points)
		points = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 1, 0)));
		assertEquals(points, List.of(new Point(1, 1, 0)), "Error TC11");

		// TC12: Ray starts at sphere and goes outside (0 points)
		assertNull(sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(-1, -1, 0))), "ERROR TC12");

		// ** Group: Ray'sphere line goes through the center

		// TC13: Ray starts before the sphere (2 points)
		points = sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 0, 0)));
		// ray cross the sphere
		p1 = new Point(0, 0, 0);
		p2 = new Point(2, 0, 0);

		if (points.get(0).getX() > points.get(1).getX())
			points = List.of(points.get(1), points.get(0));

		assertEquals(List.of(p1, p2), points, "ERROR: TC13");

		// TC14: Ray starts at sphere and goes inside (1 points)
		points = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)));

		assertEquals(List.of(new Point(2, 0, 0)), points, "ERROR TC14");

		// TC15: Ray starts inside (1 points)
		points = sphere.findIntersections(new Ray(new Point(0.5, 0, 0), new Vector(1, 0, 0)));
		assertEquals(List.of(new Point(2, 0, 0)), points, "ERROR TC15");

		// TC16: Ray starts at the center (1 points)
		points = sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0)));
		assertEquals(List.of(new Point(2, 0, 0)), points, "ERROR TC16");
		// TC17: Ray starts at sphere and goes outside (0 points)

		assertNull(sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(-1, 0, 0))), "ERROR TC17");

		// TC18: Ray starts after sphere (0 points)
		assertNull(sphere.findIntersections(new Ray(new Point(4, 0, 0), new Vector(1, 0, 0))), "ERROR TC18");
		// ** Group: Ray'sphere line is tangent to the sphere (all tests 0 points)
		// TC19: Ray starts before the tangent point
		assertNull(sphere.findIntersections(new Ray(new Point(0.5, 1, 0), new Vector(1, 0, 0))), "ERROR TC19");
		// TC20: Ray starts at the tangent point
		assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(1, 0, 0))), "ERROR TC20");

		// TC21: Ray starts after the tangent point
		assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 0), new Vector(1, 0, 0))), "ERROR TC21");
		// ** Group: Special cases
		// TC22: Ray'sphere line is outside, ray is orthogonal to ray start to
		// sphere'sphere center line
		assertNull(sphere.findIntersections(new Ray(new Point(-0.5, 0, 0), new Vector(0, 1, 0))), "ERROR TC22");
	}

}


