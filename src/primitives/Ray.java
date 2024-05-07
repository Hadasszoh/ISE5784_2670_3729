package primitives;

public class Ray {
	
	
    final Point point;
    final Vector vector;

    /**
     * simple constructor
     * @param p
     * @param v
     */
    public Ray(Point point, Vector vector) {
        this.point = point;
        this.vector = vector.normalize();
    }

    /**
     * getter
     * @return
     */
    public Point getPoint() {
        return point;
    }

    /**
     * getter
     * @return
     */
    public Vector getVector() {
        return vector;
    }

    /**
     * print  ray argument
     * @return
     */
    @Override
    public String toString() {
        return point.toString() + vector.toString(); //is that correct?
    }

    /**
     * check if 2 arguments are equals
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null);
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return point.equals(ray.point) && vector.equals(ray.vector);
    }
}