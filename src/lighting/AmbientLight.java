package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * This class represents ambient light
 *
 * @author Alon
 * @author Meir
 */
public class AmbientLight {

    private final Color intensity;

    /**
     * black ambient light (no light)
     */
    public static AmbientLight NONE = new AmbientLight(Color.BLACK, 0d);

    /**
     * constructor for the intensity
     *
     * @param IA original intensity of the lighting
     * @param KA attenuation coefficient of the lighting
     */
    public AmbientLight(Color IA, Double3 KA) {
        intensity = IA.scale(KA);
    }

    /**
     * constructor for the intensity
     *
     * @param IA original intensity of the lighting
     * @param KA attenuation coefficient of the lighting
     */
    public AmbientLight(Color IA, double KA) {
        intensity = IA.scale(KA);
    }

    /**
     * getter for intensity
     *
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
