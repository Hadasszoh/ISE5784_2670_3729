package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {

	final Ray axisRay;

	/**
	 * constructor (using super constructor)
	 * 
	 * @param radius
	 * @param axisRay
	 */
	public Tube(double radius, Ray axisRay) {
		super(radius);
		this.axisRay = axisRay;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * calculate The normal of the Tube at the point sent
	 * 
	 * @param point
	 * @return
	 */
	@Override
	public Vector getNormal(Point point) {
		Point p0 = axisRay.getPoint();
		Vector vector = axisRay.getVector();
		double t = vector.dotProduct(point.subtract(p0));
		Point o = p0.add(vector.scale(t));
		return point.subtract(o);
	}

	/**
	 * print the tube objects
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return "Tube{" + "axisRay=" + axisRay + ", radius=" + radius + '}';
	}

}
