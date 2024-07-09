package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class represents a light source in the scene.
 */
public class AmbientLight extends Light {

    /**
     * Constructor of the class
     *
     * @param iP - Color of the ambient light
     * @param kA - Double3 of the ambient light
     */
    public AmbientLight(Color iP, Double3 kA) {
        super(iP.scale(kA));
    }
    public AmbientLight(Color iP, double kA) {
        super(iP.scale(kA));
    }
    /**
     * Constructor of the class
     *
     * @param iP - Color of the ambient light
     * @param kA - double of the ambient light
     */
  //  public AmbientLight(Color iP, double kA) {
    //   super(iP.scale(kA));
   // }
    /**
     * The default constructor
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK,new Double3(0,0,0));
}
