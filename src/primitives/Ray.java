package primitives;

import java.util.List;

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
	/**
	 * get point on the ray
	 * 
	 * @param length distance from the start of the ray
	 * @return new Point3D
	 */
	
	public Point getPoint() {
	return point;
}
	public Vector getVector() {
	return vector;
}
	
	

	public Point getPoint(double length) {
		return Util.isZero(length) ? point : point.add(vector.scale(length));
	}
	/**
	 * getter
	 * 
	 * @return
	 */
	

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


	/**
	 * find the closest point to ray's head
	 *
	 * @return the closest Point
	 */
	public Point findClosestPoint(List<Point> list) {	//מקבלת רשימה של נקודות ומחזירה את הנקודה שהכי קרובה לראש הקרן
		if (list == null)	//אם אין נקודות ברשימה, זה מחזיר null
			return null;

		double distance = Double.POSITIVE_INFINITY;
		Point closest = null;
//עובר על כל הנקודות ברשימה ובודק לכל אחת אם היא יותר קרובה מהנקודה הכי קרובה עד עכשיו לראש הקרן
		for (Point p : list)    //find the closest point
			if (p.distance(point) < distance) {
				distance = p.distance(point);
				closest = p;
			}
		return closest;
	}
}