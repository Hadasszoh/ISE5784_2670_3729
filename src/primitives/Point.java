package primitives;

public class Point {
	final Double3 xyz;

	static public final Point ZERO = new Point(Double3.ZERO);
	
	/**
	 * constructor with 3 coordinates
	 * 
	 * @param x coordinate 1
	 * @param y coordinate 2
	 * @param z coordinate 3
	 */
	public Point(double x, double y, double z) {
		this.xyz = new Double3(x, y, z);
	}

	/**
	 * simple constructor (gets argument of Double3 that contain 3 coordinates)
	 * 
	 * @param xyz contain 3 coordinates
	 */
	Point(Double3 xyz) {
		this.xyz = xyz;
	}

	/**
	 * calculate the subtracting between two points
	 * 
	 * @param point other point
	 * @return new vector
	 */
	public Vector subtract(Point point) {
		return new Vector(xyz.subtract(point.xyz));
	}

	/**
	 * calculate the adding between point and vector
	 * 
	 * @param vec the vector to add
	 * @return new point
	 */
	public Point add(Vector vector) {
		return new Point(this.xyz.add(vector.xyz));
	}

	/**
	 * calculate the square length between two points
	 * 
	 * @return square length between two points
	 */
	public double distanceSquared(Point point) {
		double dx = this.xyz.d1 - point.xyz.d1;
		double dy = this.xyz.d2 - point.xyz.d2;
		double dz = this.xyz.d3 - point.xyz.d3;
		return dx * dx + dy * dy + dz * dz;
	}

	/**
	 * calculate the length between two points
	 * 
	 * @return length between two points
	 */
	public double distance(Point point) {
		return Math.sqrt(distanceSquared(point));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Point point) && xyz.equals(point.xyz);
	}

	@Override
	public String toString() {
		return "" + this.xyz;
	}
}