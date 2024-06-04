package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * This class represents a scene
 *
 * @author Hodaya
 * @author Hadass
 */
public class Scene {
    /**
     * scene name
     */
    public String name;

    /**
     * background color, initialized to black
     */
    public Color background = Color.BLACK;

    /**
     * scene's ambient light, initiated to NONE (black)
     */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /**
     * scene geometries, initialized to none geometries
     */
    public Geometries geometries = new Geometries();

    /**
     * constructor for scene's name
     *
     * @param name the name
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * setter for scene's background color
     *
     * @param background the color
     * @return this
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * setter for scene's ambient lighting
     *
     * @param ambientLight the light
     * @return this
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * setter for scene's geometries set
     *
     * @param geometries the geometries
     * @return this
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
