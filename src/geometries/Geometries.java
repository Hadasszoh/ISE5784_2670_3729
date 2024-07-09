/**
 * 
 */
package geometries;

import java.util.ArrayList;
import geometries.Intersectable.GeoPoint;

import java.util.LinkedList;
import java.util.List;
import primitives.Point;
import primitives.Ray;

/**
 * The department implements operations for several geometric bodies
 * 
 * @author 
 *
 */
public class Geometries extends Intersectable {

	private final List<Intersectable> geometList =  new LinkedList<Intersectable>();

	/**
	 * a constructor that Initializes the list to 0
	 */
	public Geometries() {}

	/**
	 * a constructor that receives a list of geometric objects and adds them to the list
	 * 
	 * @param geometries
	 */
	public Geometries(Intersectable... geometries) {
		add(geometries);
	}

	/**
	 * add object to list
	 * 
	 * @param geometries objects to add
	 */
	public void add(Intersectable... geometries) {
		for (Intersectable geo : geometries) {
			this.geometList.add(geo);
		}
	}

	/**
     * Finds intersections of a ray with the geometries in the list.
     *
     * @param  ray  the ray to find intersections with
     * @return      a list of intersection points
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = null;
        for (Intersectable geometry : geometList) {
            List<GeoPoint> intersections = geometry.findGeoIntersectionsHelper(ray);
            if (intersections != null) {
                if (result == null)
                    result = new LinkedList<>();
                result.addAll(intersections);
            }
        }
        return result;
    }
}