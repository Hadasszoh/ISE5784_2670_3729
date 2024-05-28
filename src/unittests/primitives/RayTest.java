package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import primitives.Ray;
import primitives.Point;
import primitives.Vector;

class RayTest {
	
	/**
     * Test method for {@link primitives.Ray#getPoint(double)}.
     */
    @Test
    void testgetPoint() {
        //Ray initialization.
        Point p0 = Point.ZERO;
        Vector dir = new Vector(1, 0, 0);
        Ray ray = new Ray(p0, dir);

        // ============ Equivalence Partition Test ==============
        // TC01: t is positive (1).
        double t = 1;
        Point result = ray.getPoint(t);
        Point expected = new Point(1,0,0);
        assertEquals(expected, result, "The correct point is (1,0,0)");
        // TC02: t is negative (-1).
        t = -1;
        result = ray.getPoint(t);
        expected = new Point(-1,0,0);
        assertEquals(expected, result, "The correct point is (-1,0,0)");
        // =============== Boundary Values Tests ==================
        // TC11: t is zero.
        t = 0;
        result = ray.getPoint(t);
        expected = Point.ZERO;
        assertEquals(expected, result, "The correct point is (0,0,0)");

    }
}

