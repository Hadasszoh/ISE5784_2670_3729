package geometries;

import primitives.*;

public interface Geometry {
	/**
	 * calculate the object normal vector at a point on the surfaces of the geometric body
	 * 
	 * @param point at the surface of the body
	 * @return normal vector
	 */
	Vector getNormal(Point point);
}