package primitives;

/**
 * 
 */
public class Vector extends Point  {

    /**
     * constructor (using super constructor)
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals (Double3.ZERO)) {
            throw new IllegalArgumentException ("ZERO vector");
        }
    }

    /**
     * constructor (using super constructor)
     * @param xyz
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals (Double3.ZERO)) {
            throw new IllegalArgumentException ("ZERO vector");
        }
    }

    /**
     * check if 2 arguments are equals
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (this == null) return false;
        if (!(obj instanceof Vector)) return false;
        return super.equals(obj);
    }

    /**
     * print the vector object
     * @return
     */
    @Override
    public String toString() {
        return "Vector{}"+ super.toString();
    }

    /**
     * add vector to other vector
     * @param vector2
     * @return new vector
     */
    public Vector add ( Vector vector){
        return new Vector ( this.xyz.add(vector.xyz) ) ;

    }

    /**
     * scale coordinate white coordinate
     * @param scalar
     * @return new vector
     */
    public Vector scale(double scalar){
        return new Vector ( this.xyz.scale(scalar) );
    }

    /**
     * Scalar product function
     * @param vector
     * @return double
     */
    public double dotProduct (Vector vector){
        Point point = new Point ( this.xyz.product (vector.xyz) );
        return point.xyz.d1 + point.xyz.d2 + point.xyz.d3;
    }

    /**
     * vector product
     * @param vector
     * @return new vertical vector
     */
    public Vector crossProduct (Vector vector){
         Vector newvector = new Vector (this.xyz.d3 * vector.xyz.d2 - this.xyz.d2 * vector.xyz.d3,
                 this.xyz.d1 * vector.xyz.d3 -this.xyz.d3 * vector.xyz.d1 ,
                 this.xyz.d2 * vector.xyz.d1 -this.xyz.d1 * vector.xyz.d2);
        if (newvector.xyz.equals (Double3.ZERO)) {
            throw new IllegalArgumentException ("ZERO vector");
        }
        return newvector;
    }

    /**
     * calculate the square length
     * @return sqr length
     */
    public double lengthSquared () {
        return  super.distanceSquared (new Point(0,0,0));
    }

    /**
     * calculate the vector length
     * @return legth
     */
    public double length(){
        return Math.sqrt ( this.lengthSquared() );
    }

    /**
     * Normalizes the vector
     * @return norma vector
     */
    public Vector normalize (){
        return  new Vector (this.xyz.reduce ( this.length() ) );
    }
}