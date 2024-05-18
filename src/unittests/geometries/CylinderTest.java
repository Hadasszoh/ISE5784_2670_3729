package unittests.geometries;

import geometries.*;

import primitives.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to check all functions of the Cylinder class.
 * This class contains unit tests for the getNormal(Point) method of the Cylinder class.
 * 
 * @authord Hadass & Hodaya
 */
class CylinderTest {

    /**
     * Test method for {@link Cylinder#getNormal(Point)}.ד
     * This method tests the getNormal(Point) functionality of the Cylinder class.
     */
    @Test
    void testGetNormal() {

        Cylinder cy = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1, 2);
        // ============ Equivalence Partitions Tests ==============

        // Test when the point is at the top of the cylinder
        assertEquals(new Vector(0, 0, 1), cy.getNormal(new Point(0.1, 0.1, 2)),
                "ERROR: getNormal() at the top of the cylinder returned the wrong result");

        // Test when the point is at the base of the cylinder
        assertEquals(new Vector(0, 0, -1), cy.getNormal(new Point(0.1, 0.1, 0)),
                "ERROR: getNormal() at the base of the cylinder returned the wrong result");

        // Test when the point is on the cylinder shell
        assertEquals(new Vector(0, 1, 0), cy.getNormal(new Point(0, 1, 0.5)),
                "ERROR: getNormal() on the cylinder shell returned the wrong result");

        // =============== Boundary Values Tests ==================

        // Test when the point is equal to the top point of the cylinder
        assertEquals(new Vector(0, 0, 1), cy.getNormal(new Point(0, 0, 2)),
                "ERROR: getNormal() at the top of the cylinder returned the wrong result");

        // Test when the point is equal to the base point of the cylinder
        assertEquals(new Vector(0, 0, -1), cy.getNormal(new Point(0, 0, 0)),
                "ERROR: getNormal() at the base of the cylinder returned the wrong result");
    }
}
