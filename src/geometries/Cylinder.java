package geometries;

import primitives.*;

/**
 * The Cylinder class represents a cylinder in 3D space, defined by a Ray (axis), a radius, and a height.
 * It extends the Tube class and adds the height attribute to represent the finite length of the cylinder.
 */
public class Cylinder extends Tube {

    /**
     * The height of the cylinder.
     */
    final double height;

    /**
     * Constructs a new Cylinder with the specified axis ray, radius, and height.
     *
     * @param axisRay the Ray representing the axis of the cylinder
     * @param radius the radius of the cylinder
     * @param height the height of the cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(radius, axisRay);
        this.height = height;
    }

    /**
     * Returns the height of the cylinder.
     *
     * @return the height of the cylinder
     */
    public double getHeight() {
        return height;
    }

    /**
     * Calculates the normal vector to the cylinder at a given point.
     *
     * @param point the Point at which the normal is to be calculated
     * @return the normal Vector to the cylinder at the specified point
     */
    @Override
    public Vector getNormal(Point point) {

        Point p0 = getAxisRay().getPoint();
        Vector dir = getAxisRay().getVector();
        Point pTop = p0.add(dir.scale(getHeight()));

        // if the point is at the top of the cylinder
        if (point.equals(pTop) || Util.isZero(dir.dotProduct(point.subtract(pTop)))) {
            return dir;
        }

        // if the point is at the base of the cylinder
        if (point.equals(p0) || Util.isZero(dir.dotProduct(point.subtract(p0)))) {
            return dir.scale(-1);
        }

        return super.getNormal(point);
    }

    /**
     * Returns a string representation of the Cylinder object.
     *
     * @return a string representation of the Cylinder object
     */
    @Override
    public String toString() {
        return "Cylinder{" + super.toString() + "height=" + height + '}';
    }
}
