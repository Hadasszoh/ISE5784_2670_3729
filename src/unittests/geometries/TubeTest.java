package unittests.geometries;
import java.util.List;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class to check all functions of the Tube class.
 * This class contains unit tests for the getNormal(Point) method of the Tube class.
 * 
 * @authord Hadass & Hodaya
 */
class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     * This method tests the correctness of the getNormal function.
     */
    @Test
    void TestGetNormal() {
        Ray ray = new Ray(Point.ZERO, new Vector(0, 0, 1));
        Tube tube = new Tube(Math.sqrt(2), ray);

        // =============== Equivalence Partitions Tests ==============
        // TC01: Simple check for the normal vector
        assertEquals(new Vector(1, 1, 0), tube.getNormal(new Point(1, 1, 2)), "The normal is not correct");

        // =============== Boundary Values Tests ==================
        // TC11: Check normal vector when the point lies on the tube's surface along the axis
        assertEquals(new Vector(1, 1, 0), tube.getNormal(new Point(1, 1, 1)), "The normal is not correct");
    }
    
    /**
	 * 
	 * Tests the {@link Triangle#findIntersections(Ray)} method to verify its
	 * behavior
	 * 
	 * in different scenarios.
	 */
	void testFindIntersections() {

		Triangle triangle = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(-1, 0, 0));
		List<Point> points;

		// ============ Equivalence Partitions Tests ==============

		// TC01: Inside triangle
		points = triangle.findIntersections(new Ray(new Point(0, 2, 0.5), new Vector(0, -1, 0)));
		assertEquals(List.of(new Point(0, 0, 0.5)), points, "ERROR: TC01");

		// TC02: Outside against edge
		assertNull(triangle.findIntersections(new Ray(new Point(0.5, -2, -1), new Vector(0, 1, 0))), "ERROR: TC02");

		// TC03: Outside against vertex
		assertNull(triangle.findIntersections(new Ray(new Point(1.5, -2, -0.2), new Vector(0, 1, 0))), "ERROR: TC03");

		// =============== Boundary Values Tests ==================

		// TC11: Point on edge
		assertNull(triangle.findIntersections(new Ray(new Point(0.5, -2, 0), new Vector(0, 1, 0))), "ERROR: TC11");

		// TC12: Point in vertex
		assertNull(triangle.findIntersections(new Ray(new Point(1, -1, 0), new Vector(0, 1, 0))), "ERROR: TC12");

		// TC13: Point on edge's continuation
		assertNull(triangle.findIntersections(new Ray(new Point(2, -2, 0), new Vector(0, 1, 0))), "ERROR: TC13");
	}
}
