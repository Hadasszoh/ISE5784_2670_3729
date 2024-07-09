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
     * Find intersections of a ray with geo points.
     *
     * @param  ray   the ray to find intersections with
     * @return      a list of geo points representing the intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}
