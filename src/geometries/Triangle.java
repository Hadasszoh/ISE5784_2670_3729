package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Triangle extends Polygon {
	/**
	 * constructor using at father constructor
	 * 
	 * @param point1
	 * @param point2
	 * @param point3
	 */
	public Triangle(Point point1, Point point2, Point point3) {
		super(point1, point2, point3);
	}
	 /**
     * @param ray the ray to find intersections with Triangle
     * @return the list of intersections
     */
    //Barycentric Coordinates
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List <Point> intersectionList = plane.findIntersections(ray);
        if(intersectionList == null)
            return null;
        Point intersectionPoint = intersectionList.getFirst();
        Vector v0;
        Vector v1;
        Vector v2;
        try {
            v0 = vertices.get(1).subtract(vertices.get(0));
            v1 = vertices.get(2).subtract(vertices.get(0));
            v2 = intersectionPoint.subtract(vertices.get(0));
        }
        catch (IllegalArgumentException e) {
            return null;
        }

        double d00 = v0.dotProduct(v0);
        double d01 = v0.dotProduct(v1);
        double d11 = v1.dotProduct(v1);
        double d20 = v2.dotProduct(v0);
        double d21 = v2.dotProduct(v1);

        double denom = d00 * d11 - d01 * d01;

        double v = (d11 * d20 - d01 * d21) / denom;
        double w = (d00 * d21 - d01 * d20) / denom;
        double u = 1.0 - v - w;

        if (v > 0 && w > 0 && u > 0 && v < 1 && w < 1 && u < 1) {
            return List.of(new GeoPoint(this,intersectionPoint));
        } else {
            return null;            // Point is outside the triangle

        }
    }
}