package primitives;

import java.util.Objects;

public class Point {
    Double3 xyz;
    public static final Double3 ZERO = Double3.ZERO;
    /**
     * constructor get 3 coordinate
     * @param x coordinate 1
     * @param y coordinate 2
     * @param z coordinate 3
     */
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x,y,z);
    }

    /**
     * simple constructor (gets argument of Double3 that contain 3 coordinates)
     * @param xyz contain 3 coordinates
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * calculate the subtracting between two points
     * @param point
     * @return new vector
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     *calculate the adding between point and vector
     * @param vec
     * @return new point
     */
    public Point add(Vector vec) {
        return new Point(this.xyz.add(vec.xyz));
    }

    /**
     * calculate the square length between two points
     * @return square length between two points
     */
    public double distanceSquared(Point p) {
        Point newPoint = new Point(xyz.subtract(p.xyz));
        newPoint.xyz = newPoint.xyz.product(newPoint.xyz);
        return newPoint.xyz.d1 + newPoint.xyz.d2 + newPoint.xyz.d3;

    }

    /**
     * calculate the length between two points
     * @return length between two points
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    /**
     * check if 2 arguments are equals
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point point = (Point) obj;
        return xyz.equals(point.xyz);
    }

    /**
     * print the point object
     * @return
     */
    @Override
    public String toString() {
        return this.xyz.toString();
    }
}