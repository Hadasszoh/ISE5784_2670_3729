package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static java.lang.Math.max;
import static primitives.Util.isZero;
import static primitives.Util.alignZero;

/**
 * SpotLight class represents a light source in the scene.
 */
public class SpotLight extends PointLight{
	/**
	 * @param intensity the intensity color
	 */
	private Vector direction;

	private double narrowBeam = 1d;

	/**
	 * Constructor of the class
	 * @param intensity the intensity color
	 * @param position the position
	 * @param direction the direction
	 */
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();
	}
	/**
	 * Set the narrow beam value.
	 *
	 * @param  i  the value to set for the narrow beam
	 * @return    the updated LightSource object
	 */
	public SpotLight setNarrowBeam(double i) {
		this.narrowBeam = i;
		return this;
	}

	/**
	 * A method to retrieve the intensity color.
	 *
	 * @param p the point at which to calculate the intensity
	 * @return the intensity color
	 */
	@Override
    public Color getIntensity(Point point) {
        double cos = alignZero(direction.dotProduct(getL(point)));
        return narrowBeam != 1
                ? super.getIntensity(point).scale(Math.pow(Math.max(0, direction.dotProduct(getL(point))), narrowBeam))
                : super.getIntensity(point).scale(Math.max(0, direction.dotProduct(getL(point))));
    }

	@Override
    public SpotLight setKc(double kC) {
        return (SpotLight) super.setKc(kC);
    }

    @Override
    public SpotLight setKl(double kL) {
        return (SpotLight) super.setKl(kL);
    }

    @Override
    public SpotLight setKq(double kQ) {
        return (SpotLight) super.setKq(kQ);
    }
   
}
