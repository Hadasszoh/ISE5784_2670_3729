package unittests.geometries;

import org.junit.jupiter.api.Test;
import java.util.List;
import geometries.Triangle;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to check all functions of the Triangle class.
 * This class contains unit tests for the constructors and getNormal(Point) method of the Triangle class.
 * 
 * @authord Hadass & Hodaya
 */
class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     * This method tests the correctness of the getNormal function.
     */
    @Test
    void getNormal() {
        // =============== Equivalence Partitions Tests ==============

        // TC01: Constructor handling invalid input with two identical points
        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(new Point(0, 1, 0), new Point(0, 1, 0), new Point(1, 1, 0)), "ERROR: TC01");

        // TC02: Simple check for normal vector
        Triangle t = new Triangle(new Point(0, 1, 0), new Point(1, 0, 0), new Point(1, 1, 0));
        assertEquals(new Vector(0, 0, 1), t.getNormal(new Point(0, 1, 0)), "ERROR: TC02");
    }
}
