package unittests.geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
import geometries.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class to check all function of Sphere class
 * 
 * @author David
 *
 */
class SphereTest {
	Sphere sphere1 = new Sphere(10, new Point(1, 2, 3));// positive coordinate
	Point p1 = new Point(-1, -2, -4);// negative coordinate

	/***
	 * The function checks the integrity of the getNormal function
	 */
	@Test
	void getNormal() {
		// positive coordinate sphere and point
		// assertEquals(new
		// Vector(0,3/Math.sqrt(36),5/Math.sqrt(36)),sphere1.getNormal(p1), " ")
		assertEquals(new Vector(-2 / Math.sqrt(69), -4 / Math.sqrt(69), -7 / Math.sqrt(69)), sphere1.getNormal(p1),
				"ERROR: TC 01");

	}

}