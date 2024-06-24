package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * PointLight class represents a light source in the scene.
 */
public class PointLight extends Light implements LightSource {
	protected Point position;
	private double kC=1;
	private double kL=0;
	private double kQ=0;

	/**
	 * Constructor of the class
	 * @param intensity the intensity color
	 * @param position the position
	 */
	public PointLight(Color intensity, Point position) {
		super(intensity);
		this.position = position;
	}
	/**
	 * Sets the constant attenuation factor for the point light.
	 *
	 * @param  kC  the constant attenuation factor to set
	 * @return     the updated PointLight object
	 */
	public PointLight setKc(double kC) {
		this.kC = kC;
		return this;
	}
	/**
	 * Set the attenuation factor for light intensity.
	 *
	 * @param  kL  the attenuation factor to set
	 * @return     the updated PointLight object
	 */
	public PointLight setKl(double kL) {
		this.kL = kL;
		return this;
	}
	/**
	 * Set the quadratic attenuation factor for the point light.
	 *
	 * @param  kQ  the new quadratic attenuation factor
	 * @return     the updated PointLight object
	 */
	public PointLight setKq(double kQ) {
		this.kQ = kQ;
		return this;
	}
	/**
	 * A method to retrieve the intensity color.
	 *
	 * @param p the point at which to calculate the intensity
	 * @return the intensity color
	 */
	@Override
	public Color getIntensity(Point p) {
		double distance = position.distance(p);
		return intensity.scale(1/(kC  + kL * distance + kQ * distance * distance));
	}

	/**
	 * Retrieves the vector from the specified point.
	 *
	 * @param p the point from which to retrieve the vector
	 * @return the vector retrieved from the specified point
	 */
	@Override
	public Vector getL(Point p) {
		return p.subtract(this.position).normalize();
	}
}
