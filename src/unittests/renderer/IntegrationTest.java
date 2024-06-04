package unittests.renderer;

import geometries.Geometry;

import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.SimpleRayTracer;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
    		.setRayTracer(new SimpleRayTracer(new Scene("Test")))
    	      .setImageWriter(new ImageWriter("Test", 1, 1))
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVpDistance(1).setVpSize(3, 3);

    List<Point> pointsIntersections;

    @Test
    void testConstructRayWithSphere() throws CloneNotSupportedException {
        //TC01: First test case
        cameraBuilder.setLocation(new Point(0,0,0));
        cameraBuilder.build();
        assertEquals(2,getIntersections(new Sphere(1, new Point(0, 0, -3))).size(),"Wrong number of intersections in case 1");

        //TC02: Second test case
        cameraBuilder.setLocation(new Point(0,0,0.5));
        cameraBuilder.build();
        assertEquals(18, getIntersections(new Sphere(2.5, new Point(0, 0, -2.5))).size(),"Wrong number of intersections in case 2");

        //TC03: Third test case
        cameraBuilder.setLocation(new Point(0,0,0.5));
        cameraBuilder.build();
        assertEquals(10, getIntersections(new Sphere(2, new Point(0, 0, -2))).size(),"Wrong number of intersections in case 3");

        //TC04: Fourth test case
        cameraBuilder.setLocation(new Point(0,0,0.5));
        cameraBuilder.build();
        assertEquals(9, getIntersections(new Sphere(4, new Point(0, 0, 0))).size(),"Wrong number of intersections in case 4");

        //TC05: Fifth test case
        cameraBuilder.setLocation(new Point(0,0,0));
        cameraBuilder.build();
        assertEquals(0, getIntersections(new Sphere(0.5, new Point(0, 0, 1))).size(),"Wrong number of intersections in case 5");
    }

    @Test
    void testConstructRayWithPlane() throws CloneNotSupportedException {
        //TC01: First test case
        cameraBuilder.setLocation(new Point(0,0,1));
        cameraBuilder.build();
        assertEquals(9, getIntersections(new Plane(new Point(0, 0, -1), new Point(1, 0, -1), new Point(0, 1, -1))).size(),"Wrong number of intersections in case 1");

        //TC02: Second test case
        cameraBuilder.setLocation(new Point(0,0,1));
        cameraBuilder.build();
        assertEquals(9, getIntersections(new Plane(new Point(0, 0, -2), new Point(-3,0,0), new Point(-3,2,0))).size(), "Wrong number of intersections in case 2");

        //TC03: Third test case
        cameraBuilder.setLocation(new Point(0,0,1));
        cameraBuilder.build();
        assertEquals(6, getIntersections(new Plane(new Point(0, 0, -4), new Point(-3,0,0), new Point(-3,2,0))).size(), "Wrong number of intersections in case 3");
    }
    @Test
    void testConstructRayWithTriangle() throws CloneNotSupportedException {
        //TC01: First test case
        cameraBuilder.setLocation(new Point(0,0,0.5));
        cameraBuilder.build();
        assertEquals(1, getIntersections(new Triangle(new Point(0, 1, -2), new Point(-1,-1,-2), new Point(1,-1,-2))).size(), "Wrong number of intersections in case 1");

        //TC02: Second test case
        cameraBuilder.setLocation(new Point(0,0,1));
        cameraBuilder.build();
        assertEquals(2, getIntersections(new Triangle(new Point(0,20, -2), new Point(-1,-1,-2), new Point(1,-1,-2))).size(), "Wrong number of intersections in case 2");
    }

    /**
     * @param geometry
     * @return List<Point> the list of intersections
     * @throws CloneNotSupportedException
     */
    private List<Point> getIntersections(Geometry geometry) throws CloneNotSupportedException {
        pointsIntersections = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray = cameraBuilder.build().constructRay(3,3, j, i);
                List<Point> intersections = geometry.findIntersections(ray);
                if (intersections != null) {
                    pointsIntersections.addAll(intersections);
                }
            }
        }
        return pointsIntersections;
    }
}