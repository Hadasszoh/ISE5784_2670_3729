package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry{

	final Ray axis;

	
	/**
	 * constructor (using super constructor)
	 * @param radius
	 * @param axisRay
	 */
	public Tube(double radius, Ray axisRay) {
		super(radius);
		this.axis = axisRay;
	}


    /**
     * getter
     * @return
     */
    public Ray getAxisRay() {
        return axis;
    }

    /**
     * getter
     * @return
     */
    public double getRadius() {
        return radius;
    }


	@Override
	public Vector getNormal(Point point) {
		return null;
	}
	
	 /**
     * print the tube objects
     * @return
     */
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axis +
                ", radius=" + radius +
                '}';
    }

}
