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
	
	@Override
	public List<Point> findIntersections(Ray ray) {

		Point p0 = ray.getPoint();
		Vector v = ray.getVector();
		Vector u;

		try {
			u = center.subtract(p0); // p0 == center the ray start from the center of the sphere
		} catch (IllegalArgumentException e) {
			return List.of(Util.isZero(this.radius) ? p0 : p0.add(ray.getVector().scale(this.radius)));
		}

		double tm = Util.alignZero(v.dotProduct(u));
		double dSquared = u.lengthSquared() - tm * tm;
		double thSquared = Util.alignZero(this.radius * this.radius - dSquared);

		if (thSquared <= 0)
			return null;// no intersections

		double th = Util.alignZero(Math.sqrt(thSquared));
		if (th == 0)
			return null;// ray tangent to sphere

		double t1 = Util.alignZero(tm - th);
		double t2 = Util.alignZero(tm + th);

		// ray starts after sphere
		if (Util.alignZero(t1) <= 0 && Util.alignZero(t2) <= 0)
			return null;

		// 2 intersections
		if (Util.alignZero(t1) > 0 && Util.alignZero(t2) > 0) {
			// P1 , P2
			return List.of(Util.isZero(t1) ? p0 : p0.add(ray.getVector().scale(t1)),
					Util.isZero(t2) ? p0 : p0.add(ray.getVector().scale(t2)));
		}

		// 1 intersection
		if (Util.alignZero(t1) > 0)
			return List.of(Util.isZero(t1) ? p0 : p0.add(ray.getVector().scale(t1)));
		else
			return List.of(Util.isZero(t2) ? p0 : p0.add(ray.getVector().scale(t2)));
	}


}
