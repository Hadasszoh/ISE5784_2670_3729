package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
    Point point;
    Vector normal;

    /**
     * constructor calculate the normal
     * @param point1 get 3 coordinate
     * @param point2
     * @param point3
     */
    Plane (Point point1, Point point2, Point point3) {
        point = point1;
        try {
        	normal = point1.subtract(point2).crossProduct(point1.subtract(point3)).normalize();
        	}catch(IllegalArgumentException e) {
        		throw new IllegalArgumentException("your points are not on the same vector");
        	}
    }

    /**
     * simple constructor
      * @param newPoint
     * @param newVerticalVvector
     */
    Plane ( Point point, Vector normal) {
        this.point = point;
        this.normal = normal;
    }

    /**
     * getter
     * @return point
     */
    public Point getPoint() { return point; } 
    
    
    /**
     * return normal vector
     * @return
     */
    public Vector getNormal(){
        return normal;
    }

    /**
     * getter
     * @param point
     * @return normal
     */
    @Override
    public Vector getNormal(Point point) { return normal; }

    /**
     * print the Plan objects
     * @return
     */
    @Override
    public String toString() {
        return "Plane{" + "point=" + point + ", normal=" + normal + '}';
    }
}