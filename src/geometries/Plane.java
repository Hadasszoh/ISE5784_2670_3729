package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

/**
 * The Plane class represents a plane in 3D space, defined by a point on the plane and a normal vector.
 * It implements the Geometry interface.
 */
public class Plane implements Geometry {
    /**
     * A point on the plane.
     */
    Point point;

    /**
     * The normal vector of the plane.
     */
    Vector normal;

    /**
     * Constructs a Plane using three points that lie on the plane.
     * The normal vector is calculated using the cross product of the vectors formed by these points.
     *
     * @param point1 the first point on the plane
     * @param point2 the second point on the plane
     * @param point3 the third point on the plane
     * @throws IllegalArgumentException if the points are collinear or any two points are the same
     */
    public Plane(Point point1, Point point2, Point point3) {
        point = point1;
        try {
            normal = point1.subtract(point2).crossProduct(point1.subtract(point3)).normalize();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The points are collinear or any two points are the same");
        }
    }

    /**
     * Constructs a Plane using a point on the plane and a normal vector.
     *
     * @param point the point on the plane
     * @param normal the normal vector to the plane
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    /**
     * Returns the point on the plane.
     *
     * @return the point on the plane
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @return the normal vector of the plane
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Returns the normal vector of the plane at a given point.
     * Since the normal vector is constant for a plane, the input point is not used.
     *
     * @param point the point at which the normal is to be calculated (not used)
     * @return the normal vector of the plane
     */
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    /**
     * Returns a string representation of the Plane object.
     *
     * @return a string representation of the Plane object
     */
    @Override
    public String toString() {
        return "Plane{" + "point=" + point + ", normal=" + normal + '}';
    }
    
    @Override
	public List<Point> findIntersections(Ray ray) {
		Point rayP0 = ray.getPoint();
		Vector rayDirection = ray.getVector();

		// Ray starts begins in the same point which appears as reference point in the
		// plane (0 points)
		if (rayP0 == this.point)
			return null;

		/**
		 * calculate the dotProduct between the ray direction and normal plane
		 */
		double dotProduct = this.normal.dotProduct(rayDirection);

		// Checking whether the plane and the ray intersect each other or are parallel
		// to each other
		if (Util.isZero(dotProduct)) {
			return null;
		}

		// direction to plane p0 from ray p0
		Vector p0direction = point.subtract(rayP0);

		/**
		 * checking if direction of ray is to plane if directionRayScale < 0 the ray
		 * direction of the beam is not to the surface of plane if directionRayScale = 0
		 * the ray is on surface of plane if directionRayScale > 0 the ray direction of
		 * the beam is cut the surface of plane
		 */
		double directionRayScale = Util.alignZero(this.normal.dotProduct(p0direction) / dotProduct);

		if (directionRayScale > 0) {
			// find the intersection by dot product between the direction to plane from the
			// po ray and
			// directionRayScale (which calculates the distance between the point and the
			// surface in the given direction)
			return List.of(rayP0.add(rayDirection.scale(directionRayScale)));
		}

		return null;
	}
}
