package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testFindClosestPoint() {

        Point p100 = new Point(1, 0, 0);
        Point p200 = new Point(2, 0, 0);
        Point p300 = new Point(3, 0, 0);
        Vector v100 = new Vector(1,0,0);
        List<Point> list = List.of(p100, p200, p300);   //הפונקציה מקבלת רשימה ולכן שמנו את הנקודות ברשימה
        // ============ Equivalence Partitions Tests ==============
        // EP01: closest point is in the middle of the list
        assertEquals(p200,new Ray(new Point(2.1,0,0),v100).findClosestPoint(list),"closest point is in the middle of the list" );


        // =============== Boundary Values Tests ==================

        // BV01: empty list
        assertNull(new Ray(new Point(2.1,0,0),v100).findClosestPoint(null),"empty list" );


        // BV02:  first point in the list
        assertEquals(p100,new Ray(new Point(1.1,0,0),v100).findClosestPoint(list),"first point in the list" );


        // BV03: last point in the list
        assertEquals(p300,new Ray(new Point(3.1,0,0),v100).findClosestPoint(list),"last point in the list" );

    }
}

