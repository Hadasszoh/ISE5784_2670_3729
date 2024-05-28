package renderer;

import java.util.MissingResourceException;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Camera implements Cloneable{
	private Point location;
	
	private Vector vTo;
	private Vector vUp;
	private Vector vRight;
	
	private double width = 0.0;
	private double height = 0.0;
	private double distance = 0.0;
	
	
	private Camera() {
		
	}
	
	public static Builder getBuilder() {
		return new Builder();
	}
	
	public static class Builder{
		 private final Camera camera = new Camera();
		 
		 /**
	         * Set the location of the camera
	         * @param location
	         * @return this
	         */
	        public Builder setLocation(Point location){// מקבל נקודה ומציב במיקום של המצלמה
	            camera.location = location;
	            return this;
	        }

	        /**
	         * Set the direction of the camera
	         * @param Vto
	         * @param Vup
	         * @return this
	         */
	        public Builder setDirection(Vector Vto,Vector Vup){ //  מקבלת שני וקטורים, במידה ומקבילים זורק חריגה, אחרת מחשבים את הוקטור האנכי ומציבים
	            if (Vto.crossProduct(Vup).length() == 0)
	                throw new IllegalArgumentException("Vto and Vup are parallel");
	            camera.vTo = Vto.normalize();
	            camera.vUp = Vup.normalize();
	            camera.vRight = Vto.crossProduct(Vup).normalize();
	            return this;
	        }

	        /**
	         * Set the size of the view plane
	         * @param height
	         * @param width
	         * @return this
	         */
	        public Builder setVpSize(double height, double width){ // מקבלת גובה ורוחב של ה VP
	            if (height < 0 || width < 0)
	                throw new IllegalArgumentException("The height and width of the view plane must be positive");
	            camera.height = height;
	            camera.width = width;
	            return this;
	        }

	        /**
	         * Set the distance of the view plane
	         * @param distance distance from the view plane
	         * @return this
	         */
	        public Builder setVpDistance(double distance){// מקבלת את המרחק מהVP
	            if (distance < 0)
	                throw new IllegalArgumentException("The distance from the view plane must be positive");
	            camera.distance = distance;
	            return this;
	        }

	        /**
	         * Build the camera
	         * @return Camera
	         * @throws CloneNotSupportedException
	         */
	        public Camera build() throws CloneNotSupportedException  {
	            String missingResource = "Missing Resource";
	            if(camera.location == null)
	                throw new MissingResourceException(missingResource,Camera.class.getSimpleName(),"location");
	            if(camera.vTo == null || camera.vUp == null)
	                throw new MissingResourceException(missingResource,Camera.class.getSimpleName(),"direction");
	            if(camera.height == 0.0 || camera.width == 0.0)
	                throw new MissingResourceException(missingResource,Camera.class.getSimpleName(),"vpSize");
	            if(camera.distance == 0.0)
	                throw new MissingResourceException(missingResource,Camera.class.getSimpleName(),"vpDistance");

	            if(camera.vTo.crossProduct(camera.vUp).length() == 0)
	                throw new IllegalArgumentException("Vto and Vup are parallel");
	            if(camera.height < 0.0 || camera.width < 0.0)
	                throw new IllegalArgumentException("Negative size");
	            if(camera.distance < 0.0)
	                throw new IllegalArgumentException("Negative distance");

	            return (Camera)camera.clone();
	        }	 
	}
	
	public Ray constructRay(int nX, int nY, int j, int i) {
		return null;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public Vector getvTo() {
		return vTo;
	}
	
	public Vector getvRight() {
		return vRight;
	}
	
	public Vector getvUp() {
		return vUp;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getDistance() {
		return distance;
	}
	
	
	
}

