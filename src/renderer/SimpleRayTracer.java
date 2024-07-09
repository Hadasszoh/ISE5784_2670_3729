package renderer;

import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * This class is an extended class of a ray tracer.
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * The delta constant
     */
    private static final double DELTA = 0.1;

    /**
     * Construct
     * @param scene
     */

    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * @param ray
     * @return the color of the closest point
     */
    @Override
    public Color traceRay(Ray ray) {
        List <GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if(intersections == null)
            return scene.background;
        return calcColor(ray.findClosestGeoPoint(intersections),ray);
    }

    private boolean unshaded(GeoPoint gp , Vector l,LightSource lightSource,Vector n) {
        Vector lightDirection = l.scale(-1);
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point point = gp.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections != null)
            for (GeoPoint geoPoint : intersections)
                if (geoPoint.point.distance(gp.point) < lightSource.getDistance(gp.point))
                    return false;
        return true;
    }

    /**
     * Calculate the color for a given point and ray.
     *
     * @param  point  the geographic point
     * @param  ray    the ray
     * @return       the calculated color
     */
    private Color calcColor(GeoPoint point, Ray ray) {
        return calcLocalEffects(point, ray).add(super.scene.ambientLight.getIntensity());
    }

    /**
     * Calculate local effects for a given GeoPoint and Ray.
     *
     * @param  geoPoint  the geometric point
     * @param  ray       the ray
     * @return           the color representing the local effects
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Color color = geoPoint.geometry.getEmission();
        Vector v = ray.getVector();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = geoPoint.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (unshaded(geoPoint, l,lightSource,n) && (nl * nv > 0)) {
                    Color iL = lightSource.getIntensity(geoPoint.point);
                    Color calcDiffusive = calcDiffusive(material, nl, iL);
                    Color calcSpecular = calcSpecular(material, n, l, nl, v, iL);
                    color = color.add(calcDiffusive, calcSpecular);

            }
        }
        return color;
    }

    /**
     * Calculation of specular light component
     *
     * @param material Attenuation coefficient for specular light component
     * @param n        normal to point
     * @param l        direction vector from light to point
     * @param v        direction of ray shot to point
     * @param intensity
     * @return Color of specular light component
     */
    private Color calcSpecular(Material material, Vector n, Vector l, double nl, Vector v, Color intensity) {
        Vector r = l.add(n.scale(-2 * nl));
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Color.BLACK; // view from direction opposite to r vector
        double pow = Math.pow(minusVR, material.nShininess);
        Double3 amount = material.kS.scale(pow);
        return intensity.scale(amount);
    }

    /**
     * Calculation of diffusion light component
     *
     * @param material normal to point
     * @param nl       dot product between n-normal to point and l-direction vector from light to point
     * @param intensity
     * @return Color of diffusion light component
     */
    private Color calcDiffusive(Material material, double nl, Color intensity) {
        double abs = Math.abs(nl);
        Double3 scale = material.kD.scale(abs);
        return intensity.scale(scale);
    }
}
    