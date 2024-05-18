package unittests.geometries;

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
}
