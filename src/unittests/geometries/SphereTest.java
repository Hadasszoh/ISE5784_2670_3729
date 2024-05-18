package unittests.geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import geometries.Sphere;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to check all functions of the Sphere class.
 * This class contains unit tests for the getNormal(Point) method of the Sphere class.
 * 
 * @authord Hadass & Hodaya
 */
class SphereTest {
    // Sphere with positive coordinate center
    Sphere sphere1 = new Sphere(10, new Point(1, 2, 3));
    // Point with negative coordinates
    Point p1 = new Point(-1, -2, -4);

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     * The method checks the correctness of the getNormal function.
     */
    @Test
    void getNormal() {
        // Positive coordinate sphere and point
        assertEquals(new Vector(-2 / Math.sqrt(69), -4 / Math.sqrt(69), -7 / Math.sqrt(69)), sphere1.getNormal(p1),
                "ERROR: TC 01");
    }
}
