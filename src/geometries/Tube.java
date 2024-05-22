package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

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
	
	
	// Method to calculate intersection points between the cylinder and a given ray
		@Override
		public List<Point> findIntersections(Ray ray) {

			// Extract the origin and direction of the ray
			Point rayOrigin = ray.getPoint();
			Vector rayDirection = ray.getVector();

			// Calculate the discriminant of the quadratic equation
			double[] abc = Util.discriminantParam(rayDirection, rayOrigin, ray, radius);

			double discriminant = abc[1] * abc[1] - 4 * abc[0] * abc[2];

			// If the discriminant is negative, the ray does not intersect the cylinder
			if (discriminant < 0) {
				return null;
			}

			// Calculate the roots of the quadratic equation
			double t1 = (-abc[1] - Math.sqrt(discriminant)) / (2 * abc[0]);
			double t2 = (-abc[1] + Math.sqrt(discriminant)) / (2 * abc[0]);

			// Calculate the intersection points
			Point intersectionPoint1 = ray.getPoint().add(ray.getVector().scale(t1));
			Point intersectionPoint2 = ray.getPoint().add(ray.getVector().scale(t2));

			// Add the intersection points to the list
			return List.of(intersectionPoint1, intersectionPoint2);
		}

}
