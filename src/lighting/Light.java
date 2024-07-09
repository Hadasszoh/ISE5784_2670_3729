package lighting;

import primitives.Color;

/**
 * Light class represents a light source in the scene.
 */
abstract class Light {
    protected Color intensity;

    /**
     * @param intensity the intensity color
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * A method to retrieve the intensity color.
     *
     * @return         the intensity color
     */
    public Color getIntensity() { //בלי פרמטרים 
        return intensity;
    }
}