package unittests.geometries;

import geometries.*;

import primitives.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class to check all function of cylinder class
 * 
 * @author David
 *
 */
class CylinderTest {

	/**
	 * Test method for getNormal(Point)
	 */
	@Test
	void testGetNormal() {

		Cylinder cy = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1, 2);
		// ============ Equivalence Partitions Tests ==============

		// test when the point is at the top of the cylinder
		assertEquals(new Vector(0, 0, 1), cy.getNormal(new Point(0.1, 0.1, 2)),
				"ERROR: getNormal() at the top of the cylinder wrong result");

		// Test when the point is at the base of the cylinder
		assertEquals(new Vector(0, 0, -1), cy.getNormal(new Point(0.1, 0.1, 0)),
				"ERROR: getNormal() at the base of the cylinder wrong result");

		// Test when the point is on the cylinder shell
		assertEquals(new Vector(0, 1, 0), cy.getNormal(new Point(0, 1, 0.5)),
				"ERROR: getNormal() on the cylinder shell wrong result");

		// =============== Boundary Values Tests ==================

		// test when the point is equals to the top point cylinder
		assertEquals(new Vector(0, 0, 1), cy.getNormal(new Point(0, 0, 2)),
				"ERROR: getNormal() at the top of the cylinder wrong result");

		// Test when the point is equals to the base point the cylinder
		assertEquals(new Vector(0, 0, -1), cy.getNormal(new Point(0, 0, 0)),
				"ERROR: getNormal() at the base of the cylinder wrong result");
	}

}