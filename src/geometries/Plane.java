package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;
import static primitives.Util.*;
/**
 * The Plane class represents a plane in 3D space, defined by a point on the plane and a normal vector.
 * It implements the Geometry interface.
 */
public class Plane extends Geometry {
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
    /**
     * Finds the intersections of a given ray with the geometry represented by the class.
     *
     * @param  ray  the ray to find intersections with
     * @return      a list of GeoPoint objects representing the intersections, or null if no intersections found
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        double numerator = normal.dotProduct(point.subtract(ray.getPoint()));
        double denominator = normal.dotProduct(ray.getVector());
        if (isZero(denominator))
        {
            return null;
        }
        double t = alignZero(numerator / denominator);
        if (t > 0)
        {
            return List.of(new GeoPoint(this,ray.getPoint(t)));
        }
        return null;
    }
}
