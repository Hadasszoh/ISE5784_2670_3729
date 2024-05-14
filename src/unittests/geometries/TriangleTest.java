package unittests.geometries;

import org.junit.jupiter.api.Test;
import java.util.List;
import geometries.Triangle;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class to check all function of triangle class
 * 
 * @author David
 *
 */
class TriangleTest {

	/**
	 * get normal
	 */
	@Test
	void getNormal() {
		// =============== Equivalence Partitions Tests ==============
		// TC01: constructor acting well
		assertThrows(IllegalArgumentException.class,
				() -> new Triangle(new Point(0, 1, 0), new Point(0, 1, 0), new Point(1, 1, 0)), "ERROR: TC01)");

		// TC02: simple check
		Triangle t = new Triangle(new Point(0, 1, 0), new Point(1, 0, 0), new Point(1, 1, 0));
		assertEquals(new Vector(0, 0, 1), t.getNormal(new Point(0, 1, 0)), "ERROR: TC02");
	}
}