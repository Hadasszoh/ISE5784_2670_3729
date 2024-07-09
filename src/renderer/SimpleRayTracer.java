package renderer;

import geometries.Intersectable.*;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class implements the simple version of the ray tracer.
 */
public class SimpleRayTracer extends RayTracerBase {

	private static final double EPS = 0.1;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final Double3 INITIAL_K = Double3.ONE;

	/**
	 * @param scene the scene to render
	 */
	public SimpleRayTracer(Scene scene) {
		super(scene);
	}

	/**
	 * Calculate the color of the closest intersection point
	 * with the given ray.
	 *
	 * @param  ray  the ray for which to calculate the color
	 * @return      the color of the closest intersection point
	 */
	@Override
	public Color traceRay(Ray ray) {
		GeoPoint closestPoint = findClosestIntersection(ray);
		if (closestPoint == null) {
			return scene.background;
		}
		return calcColor(closestPoint, ray);

	}

	/**
	 * Calculates the color for a given GeoPoint and Ray.
	 *
	 * @param  geoPoint    the GeoPoint to calculate color for
	 * @param  ray         the Ray to calculate color for
	 * @return             the calculated color
	 */
	private Color calcColor(GeoPoint geoPoint, Ray ray) {
		return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
				.add(scene.ambientLight.getIntensity());
	}

	/**
	 * Calculate the color for a given GeoPoint, Ray, level, and k.
	 *
	 * @param  geoPoint  the GeoPoint to calculate color for
	 * @param  ray       the Ray representing the direction
	 * @param  level     the level of recursion
	 * @param  k         the k value
	 * @return           the calculated color
	 */
	private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
		Color color = geoPoint.geometry.getEmission();

		Vector v = ray.getVector();
		Vector n = geoPoint.geometry.getNormal(geoPoint.point);

		// check that ray is not parallel to geometry
		double nv = alignZero(n.dotProduct(v));

		if (isZero(nv)) {
			return color;
		}
		Material material = geoPoint.geometry.getMaterial();
		color = color.add(calcLocalEffects(geoPoint, material, n, v, nv, k));
		return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, material,n,v, level, k));
	}

	/**
	 * //add here the lights effects
	 *
	 * @param gp  geoPoint of the intersection
	 * @param v ray direction
	 * @return resulting color with diffuse and specular
	 */
	private Color calcLocalEffects(GeoPoint gp, Material material, Vector n, Vector v, double nv, Double3 k) {
		Color color = Color.BLACK;

		Point point = gp.point;

		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sign(nv)
				Double3 ktr = transparency(lightSource, l, n, gp);
				if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
//                if (unshaded(gp, lightSource, l, n)) {
					Color iL = lightSource.getIntensity(point).scale(ktr);
					Color color1 = calcDiffusive(material.kD, nl, iL);
					Color color2 = calcSpecular(material.kS, n, l, nl, v, material.nShininess, iL);
					color = color.add(
							color1,
							color2);
				}
			}
		}
		return color;
	}

	/**
	 * Calculates the specular reflection of a light source on a surface.
	 *
	 * @param  kS        the specular reflection coefficient
	 * @param  n         the normal vector of the surface
	 * @param  l         the light vector
	 * @param  nl        the dot product of n and l
	 * @param  v         the view vector
	 * @param  shininess the shininess of the surface
	 * @param  intensity the intensity of the light source
	 * @return           the color of the specular reflection
	 */
	private Color calcSpecular(Double3 kS, Vector n, Vector l, double nl,Vector v,int shininess,Color intensity) {
		Vector r = l.add(n.scale(-2 * nl)); // nl must not be zero!
		double minusVR = -alignZero(r.dotProduct(v));
		if (minusVR <= 0)
			return Color.BLACK; // view from direction opposite to r vector
		Double3 amount =kS.scale(Math.pow(minusVR, shininess));
		return intensity.scale(amount);
	}

	/**
	 * Calculates the diffusive color.
	 *
	 * @param  kD       the diffuse reflection coefficient
	 * @param  nl       the dot product of the normal and light direction vectors
	 * @param  intensity the color intensity of the light source
	 * @return          the diffusive color
	 */
	private Color calcDiffusive(Double3 kD, double nl,  Color intensity) {
		double abs_nl = Math.abs(nl);
		Double3 amount =kD.scale(abs_nl);
		return intensity.scale(amount);
	}

	/**
	 * Calculate the global effects for the given parameters.
	 *
	 * @param  gp       the GeoPoint
	 * @param  material the material
	 * @param  n        the normal vector
	 * @param  v        the view vector
	 * @param  level    the level of recursion
	 * @param  k        the Double3 parameter
	 * @return          the calculated Color
	 */
	private Color calcGlobalEffects(GeoPoint gp,Material material, Vector n, Vector v, int level, Double3 k) {
		Color color = Color.BLACK;
		Double3 kkr = material.kR.product(k);
		if (!kkr.lowerThan(MIN_CALC_COLOR_K))
			color = color.add(calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr));
		Double3 kkt = material.kT.product(k);
		if (!kkt.lowerThan(MIN_CALC_COLOR_K))
			color = color.add(
					calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
		return color;
	}

	/**
	 * Calculates the global effect for the given Ray, level, kx, and kkx.
	 *
	 * @param  ray     the Ray for the calculation
	 * @param  level   the level of the effect
	 * @param  kx      the kx parameter for the calculation
	 * @param  kkx     the kkx parameter for the calculation
	 * @return         the calculated Color value
	 */
	private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
		GeoPoint gp = findClosestIntersection(ray);
		return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
	}

	/**
	 * Constructs a refracted ray.
	 *
	 * @param  point  the point of origin
	 * @param  v      the vector
	 * @param  n      the normal vector
	 * @return       a new Ray object
	 */
	private Ray constructRefractedRay(Point point, Vector v, Vector n) {
		return new Ray(point, n, v);
	}

	/**
	 * Constructs a reflected ray based on the given parameters.
	 *
	 * @param  pointGeo  the point in 3D space
	 * @param  v         the incident vector
	 * @param  n         the normal vector
	 * @return           the reflected ray
	 */
	private Ray constructReflectedRay(Point pointGeo, Vector v, Vector n) {
		//r = v - 2.(v.n).n
		double vn = v.dotProduct(n);

		if (vn == 0) {
			return null;
		}

		Vector r = v.subtract(n.scale(2 * vn));
		return new Ray(pointGeo, n, r);
	}

	/**
	 * The method checks whether there is any object shading the light source from a
	 * point
	 *
	 * @param gp the point with its geometry
	 * @param lightSource the light source
	 * @param l  direction from light to the point
	 * @param n normal vector to the surface of gp
	 * @return accumulated transparency attenuation factor
	 */
	private boolean unshaded(GeoPoint gp,LightSource lightSource ,Vector l, Vector n) {

		Vector lightDirection = l.scale(-1); // from point to light source
		double nl = n.dotProduct(lightDirection);

		Vector delta = n.scale(nl > 0 ? EPS : -EPS);
		Point pointRay = gp.point.add(delta);
		Ray lightRay = new Ray(pointRay, lightDirection);

		double maxdistance = lightSource.getDistance(gp.point);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay ,maxdistance);

		if (intersections == null){
			return true;
		}

		for (var item : intersections){
			if (item.geometry.getMaterial().kT.lowerThan(MIN_CALC_COLOR_K)){
				return false;
			}
		}

		return true;
	}

	/**
	 * The method checks whether there is any object shading the light source from a
	 * point
	 *
	 * @param gp the point with its geometry
	 * @param ls light source
	 * @param l  direction from light to the point
	 * @return accumulated transparency attenuation factor
	 */
//	private boolean unshaded(GeoPoint gp,LightSource ls, Vector l) {
//		Vector n = gp.geometry.getNormal(gp.point);
//
//		Vector lightDirection = l.scale(-1); // from point to light source
//		Ray lightRay = new Ray(gp.point, lightDirection, n);
//
//		double lightDistance = ls.getDistance(gp.point);
//		var intersections = scene.geometries.findGeoIntersections(lightRay, lightDistance);
//		if (intersections == null)
//			return true;
//
//		Double3 tr = Double3.ONE;
//		for (var geo : intersections) {
//			tr = tr.product(geo.geometry.getMaterial().kT);
//			if (tr.lowerThan(MIN_CALC_COLOR_K))
//				return false;
//		}
//
//		return true;
//	}


	/**
	 * The method checks whether there is any object shading the light source from a
	 * point
	 *
	 * @param gp the point with its geometry
	 * @param lightSource light source
	 * @param l  direction from light to the point
	 * @param n normal vector from the surface towards the geometry
	 *
	 * @return accumulated transparency attenuation factor
	 */

	private Double3 transparency(LightSource lightSource, Vector l, Vector n, GeoPoint gp) {
		// Pay attention to your method of distance screening
		Vector lightDirection = l.scale(-1); // from point to light source
		Point point = gp.point;
		Ray lightRay = new Ray(point, n, lightDirection);

		double maxdistance = lightSource.getDistance(point);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

		if (intersections == null)
			return Double3.ONE;

		Double3 ktr = Double3.ONE;
		for (var geo : intersections) {
			ktr = ktr.product(geo.geometry.getMaterial().kT);
			if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
				return Double3.ZERO;
			}
		}
		return ktr;
	}

	/**
	 * Finds the closest intersection point of a ray with the scene geometries.
	 *
	 * @param  ray  the ray for which to find the closest intersection
	 * @return      the closest intersection point, or null if no intersection is found
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null) {
			return null;
		}
		return ray.findClosestGeoPoint(intersections);
	}

}

