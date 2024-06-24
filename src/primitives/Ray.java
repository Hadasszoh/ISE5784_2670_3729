package primitives;

import java.util.List;
import geometries.Intersectable.GeoPoint;
public class Ray {

	final Point point;
	final Vector vector;

	/**
	 * simple constructor
	 * 
	 * @param p
	 * @param v
	 */
	public Ray(Point point, Vector vector) {
		this.point = point;
		this.vector = vector.normalize();
	}
	public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

	/**
	 * getter
	 * 
	 * @return
	 */	
	/**
	 * get point on the ray
	 * 
	 * @param length distance from the start of the ray
	 * @return new Point3D
	 */
	
	public Point getPoint() {
	return point;
}
	public Vector getVector() {
	return vector;
}
	
	

	public Point getPoint(double length) {
		return Util.isZero(length) ? point : point.add(vector.scale(length));
	}
	/**
	 * getter
	 * 
	 * @return
	 */
	

	/**
	 * print ray argument
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return point.toString() + vector.toString(); // is that correct?
	}

	/**
	 * check if 2 arguments are equals
	 * 
	 * @param o
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Ray ray) && point.equals(ray.point) && vector.equals(ray.vector);
	}
	public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {

        GeoPoint closestPoint = null;
        double minDistance = Double.MAX_VALUE;
        double geoPointDistance; // the distance between the "this.p0" to each point in the list

        if (!geoPointList.isEmpty()) {
            for (var geoPoint : geoPointList) {
                geoPointDistance = this.getPoint().distance(geoPoint.point);
                if (geoPointDistance < minDistance) {
                    minDistance = geoPointDistance;
                    closestPoint = geoPoint;
                }
            }
        }
        return closestPoint;
    }
}
