package renderer;


import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * This class represents a simple ray tracer
 *
 * @author Hodaya
 * @author Hadass
 */

public class SimpleRayTracer extends RayTracerBase {//  יורשת מ-ray trace

    /**
     * constructor for scene
     *
     * @param scene the scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    //עוקבת אחרי הקרן ומחזירה את הצבע של הגוף הכי קרוב שהקרן פוגעת בו
    public Color traceRay(Ray ray) {
        Point point = ray.findClosestPoint(scene.geometries.findIntersections(ray));
        //אם הקרן לא חותכת שום גוף, מחזירים את צבע הרקע
        if (point == null)
            return scene.background;
        return calcColor(point);
    }

    /**
     * this function calculates color of a point
     *
     * @param point the point
     * @return the color
     */
    //מחזירה את הצבע של התאורה
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }
}
