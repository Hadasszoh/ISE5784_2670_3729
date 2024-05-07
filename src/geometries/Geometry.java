package geometries;
import primitives.*;

public interface  Geometry {
    /**
     * calculate the object normal vector
     * @param point
     * @return normal vector
     */
    Vector getNormal(Point point);
}