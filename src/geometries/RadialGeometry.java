package geometries;

import primitives.Point;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry {

	
	/**
	 * 
	 */
	protected final double radius;
	
	
	/**
     * constructor (using super constructor)
	 * @param radius
	 */
	public RadialGeometry(double radius) {
		super();
		this.radius = radius;
	}

}
