package geometries;

import java.util.List;
import primitives.Point;
import primitives.Ray;

/**
 * interface of Intersectable by ray
 */
public interface Intersectable {

	/**
	 * get ray for intersect
	 * 
	 * @param ray
	 * @return intersections list between the ray and geometry object 
	 */
	List<Point> findIntersections(Ray ray);

}
