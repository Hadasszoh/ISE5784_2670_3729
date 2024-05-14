package geometries;

import primitives.Point;
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

}
