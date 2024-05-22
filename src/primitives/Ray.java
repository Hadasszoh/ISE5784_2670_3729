package primitives;

public class Ray {

	final Point point;
	final Vector vector;

	/**
	 * simple constructor
	 * 
	 * @param p
	 * @param v
	 */
	public Ray(Point point, Vector vector) {
		this.point = point;
		this.vector = vector.normalize();
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public Point getPoint() {
		return point;
	}
	
	/**
	 * get point on the ray
	 * 
	 * @param length distance from the start of the ray
	 * @return new Point3D
	 */
	public Point getPoint(double length) {
		return Util.isZero(length) ? point : point.add(vector.scale(length));
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public Vector getVector() {
		return vector;
	}

	/**
	 * print ray argument
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return point.toString() + vector.toString(); // is that correct?
	}

	/**
	 * check if 2 arguments are equals
	 * 
	 * @param o
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Ray ray) && point.equals(ray.point) && vector.equals(ray.vector);
	}
}