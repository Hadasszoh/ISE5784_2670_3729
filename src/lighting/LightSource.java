package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * LightSource class represents a light source in the scene.
 */
public interface LightSource {

    /**
     * A method to retrieve the intensity color.
     *
     * @param p        the point at which to calculate the intensity
     * @return         the intensity color
     */
    public Color getIntensity(Point p);//מחזיר את התאורה בנקודה ספציפית 

	/**
	 * Retrieves the vector from the specified point.
	 *
	 * @param  p  the point from which to retrieve the vector
	 * @return    the vector retrieved from the specified point
	 */
	public Vector getL(Point p);

}