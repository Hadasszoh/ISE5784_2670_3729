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

        // Check constructor with two identical points
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 2, 6.3), new Point(1, 2, 6.3), new Point(0, 0, 0)),
                "ERROR: Constructor should throw an exception when two points are the same");

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
}

