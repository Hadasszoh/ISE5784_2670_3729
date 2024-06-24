package lighting;

import primitives.*;

public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * Constructs a DirectionalLight with the specified intensity and direction.
     *
     * @param intensity the intensity of the light
     * @param direction the direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }
}