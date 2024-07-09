package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

public class Sphere extends RadialGeometry {

	final Point center;

	/**
	 * constructor (using super constructor)
	 * 
	 * @param radius
	 * @param center
	 */
	public Sphere(double radius, Point center) {
		super(radius);
		this.center = center;
	}

	/**
	 * getter
	 * 
	 * @return Sphere center
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * getter
	 * 
	 * @return Sphere radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point point) {
		return point.subtract(center).normalize();
	}

	@Override
	public String toString() {
		return "Sphere{" + "center=" + center + ", radius=" + radius + '}';
	}
	
	/**
     * Finds the intersections of a given ray with the geometry of the object.
     *
     * @param  ray  the ray to find intersections with
     * @return      a list of GeoPoint objects representing the intersections, or null if there are no intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        double d;
        double Tm=0;
        if (center.equals(ray.getPoint())) {
            d = 0;
        }
        else {
            Vector u = center.subtract(ray.getPoint());
            Tm = ray.getVector().dotProduct(u);
            d = Math.sqrt(u.lengthSquared() - Tm * Tm);
            if (d >= radius) {
                return null;
            }
        }
        double Th = Math.sqrt(radius * radius - d * d);
        double t1 = Tm - Th;
        double t2 = Tm + Th;
        if (t1 > 0 && t2 > 0 && t1 != t2) {
            return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint(this,ray.getPoint(t2)));
        }
        if (t1 > 0) {
            return List.of(new GeoPoint(this,ray.getPoint(t1)));
        }
        if (t2 > 0) {
            return List.of(new GeoPoint(this,ray.getPoint(t2)));
        }
        return null;
    }
}

