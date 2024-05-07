package geometries;

import primitives.Point;

public class Triangle extends Polygon {
    /**
     * constructor using at father constructor
     * @param point1
     * @param point2
     * @param point3
     */
    Triangle (Point point1, Point point2, Point point3){
        super( point1, point2, point3);
    }
}