package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

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

    void findClosestPoint() {

        List<Point> pointList = new LinkedList<>();

        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(2, 2, 2);
        Point p3 = new Point(3, 3, 3);

        pointList.add(p1);
        pointList.add(p2);
        pointList.add(p3);

        Vector vector = new Vector(0, -0.5, 0);

        // ============ Equivalence Partitions Tests ==============
        //TC01: The closest point is in the middle of the list
        Ray ray1 = new Ray(new Point(2, 2.5, 2), vector);
        assertEquals(p2, ray1.findClosestPoint(pointList), "The point in the middle");

        // =============== Boundary Values Tests ==================
        //TC10: The closest point is the first point in the list
        Ray ray2 = new Ray(new Point(1, 1.25, 1), vector);
        assertEquals(p1, ray2.findClosestPoint(pointList), "The point is the first one");

        //TC11: The closest point is the last point in the list
        Ray ray3 = new Ray(new Point(3, 3.5, 3), vector);
        assertEquals(p3, ray3.findClosestPoint(pointList), "The point is the last one");

        //TC12: The list is null
        pointList.clear();
        assertNull(ray3.findClosestPoint(pointList), "The list is empty");
    }
}

