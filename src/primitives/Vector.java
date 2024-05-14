package primitives;

public class Vector extends Point {

	/**
	 * constructor get 3 double check if that points are not create the ZERO vector (using super constructor) 
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector(double x, double y, double z) {
		super(x, y, z);
		if (xyz.equals(Double3.ZERO)) {
			throw new IllegalArgumentException("ZERO vector");
		}
	}

	/**
	 * constructor get Double3 object check if that points are not create the ZERO vector (using super constructor)
	 * 
	 * @param xyz
	 */
	Vector(Double3 xyz) {
		super(xyz);
		if (xyz.equals(Double3.ZERO)) {
			throw new IllegalArgumentException("ZERO vector");
		}
	}

	/**
	 * check if 2 arguments are equals
	 * 
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Vector) && super.equals(obj);
	}

	/**
	 * print the vector object
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return "Vector{}" + super.toString();
	}

	/**
	 * add vector to other vector
	 * 
	 * @param vector2
	 * @return new vector
	 */
	public Vector add(Vector vector) {
		return new Vector(this.xyz.add(vector.xyz));

	}

	/**
	 * scale coordinate white coordinate
	 * 
	 * @param scalar
	 * @return new vector
	 */
	public Vector scale(double scalar) {
		return new Vector(this.xyz.scale(scalar));
	}

	/**
	 * Scalar product function
	 * 
	 * @param vector
	 * @return Scalar product result
	 */
	public double dotProduct(Vector vector) {
		return this.xyz.d1 * vector.xyz.d1 + this.xyz.d2 * vector.xyz.d2 + this.xyz.d3 * vector.xyz.d3;
	}

	/**
	 * vector product
	 * 
	 * @param vector
	 * @return new vertical vector
	 */
	public Vector crossProduct(Vector vector) {
		return new Vector(xyz.d2 * vector.xyz.d3 - xyz.d3 * vector.xyz.d2,
				xyz.d3 * vector.xyz.d1 - xyz.d1 * vector.xyz.d3, xyz.d1 * vector.xyz.d2 - xyz.d2 * vector.xyz.d1);
	}

	/**
	 * calculate the square length
	 * 
	 * @return sqr length
	 */
	public double lengthSquared() {
		return dotProduct(this);
	}

	/**
	 * calculate the vector length
	 * 
	 * @return legth
	 */
	public double length() {
		return Math.sqrt(this.lengthSquared());
	}

	/**
	 * Builds a new unit vector in the same direction
	 * 
	 * @return normalized vector
	 */
	public Vector normalize() {
		return new Vector(this.xyz.reduce(this.length()));
	}
}