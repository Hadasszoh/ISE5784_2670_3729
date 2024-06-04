package renderer;

import java.util.MissingResourceException;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;
import static primitives.Util.alignZero;


public class Camera implements Cloneable{// שיבוט של אובייקט קיים מבלי לשנות את האובייקט המקורי
	private Point location;
	
	private Vector vTo;
	private Vector vUp;
	private Vector vRight;
	
	private double width = 0.0;
	private double height = 0.0;
	private double distance = 0.0;

	private ImageWriter imageWriter;

	private RayTracerBase rayTracer;
	
	
	private Camera() {}
	
	public static Builder getBuilder() {
		return new Builder();
	}


	/**
	 * goes over all of the pixels and color them according to the scene
	 */
	public void renderImage() {
		for(int i=0;i< imageWriter.getNy();i++)
			for(int j=0;j< imageWriter.getNx();j++)
				castRay(imageWriter.getNx(),imageWriter.getNy(),j,i);
		//throw new UnsupportedOperationException();
	}

	private void castRay(int nX, int nY, int j, int i) {
		Ray ray=constructRay(nX, nY, j, i);	//יוצרת קרן
		Color color = rayTracer.traceRay(ray);	//מוצאת את הצבע שבו פוגעת הקרן
		imageWriter.writePixel(j,i,color);	//צובעת את הפיקסל בתמונה עצמה
	}

	/**
	 * write the image to file
	 */
	public void writeToImage() {
		imageWriter.writeToImage();
	}


	/**
	 * print a grid of interval*interval pixels squares
	 *
	 * @param interval square edges size
	 * @param color grid's color
	 */
	public void printGrid(int interval, Color color) {
		for (int j = 0; j < imageWriter.getNx(); j++)
			for (int i = 0; i < imageWriter.getNy(); i++)
				if (isZero(j % interval) || isZero(i % interval))
					imageWriter.writePixel(j, i, color);
	}

public Ray constructRay(int nX, int nY, int j, int i) {
        if (nY == 0 || nX == 0) {
            throw new IllegalArgumentException("It is impossible to divide by 0");
        }
        Point Pc = location.add(vTo.scale(distance));
        double Ry = height / nY;
        double Rx = width / nX;

        double Yi = -1 * (i - (nY - 1) / 2.0) * Ry;
        double Xj = (j - (nX - 1) / 2.0) * Rx;

        Point Pij = Pc;
        if (!isZero(Xj)) {
            Pij = Pij.add(vRight.scale(Xj));
        }

        if (!isZero(Yi)) {
            Pij = Pij.add(vUp.scale(Yi));
        }

        return new Ray(location, Pij.subtract(location));
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
		 * setter for imageWriter
		 *
		 * @param imageWriter imageWriter
		 * @return this
		 */
		public Builder setImageWriter(ImageWriter imageWriter) {
			this.camera.imageWriter = imageWriter;
			return this;
		}

		/**
		 * setter for rayTracer
		 *
		 * @param rayTracer rayTracer
		 * @return this
		 */
		public Builder setRayTracer(RayTracerBase rayTracer) {
			this.camera.rayTracer = rayTracer;
			return this;
		}

	        /**
	         * Build the camera
	         * @return Camera
	         * @throws CloneNotSupportedException
	         
	        public Camera build() {   // אם זה לא חוקי- 
	            String missingResource = "Missing Resource";
	            if(camera.location == null)
	                throw new MissingResourceException(missingResource,Camera.class.getSimpleName(),"location");
	            if(camera.vTo == null || camera.vUp == null)
	                throw new MissingResourceException(missingResource,Camera.class.getSimpleName(),"direction");
	            if(camera.height == 0.0 || camera.width == 0.0)
	                throw new MissingResourceException(missingResource,Camera.class.getSimpleName(),"vpSize");// parameters== null מאותחל 
	            if(camera.distance == 0.0)
	                throw new MissingResourceException(missingResource,Camera.class.getSimpleName(),"vpDistance");

	            if(camera.vTo.crossProduct(camera.vUp).length() == 0)
	                throw new IllegalArgumentException("Vto and Vup are parallel");
	            if(camera.height < 0.0 || camera.width < 0.0)
	                throw new IllegalArgumentException("Negative size");// checking the parameters himself 
	            if(camera.distance < 0.0)
	                throw new IllegalArgumentException("Negative distance");

				if (camera.imageWriter == null)
					throw new MissingResourceException(missing, nameClass, "imageWriter");

				if (camera.rayTracer == null)
					throw new MissingResourceException(missing, nameClass, "rayTracer");


				try
	            {
		            return (Camera)camera.clone();
	            } catch (CloneNotSupportedException e) {
	            
	            	throw  new RuntimeException(e);
	            }
	        }*/
	     public Camera build() {   // אם זה לא חוקי- 
	            String missingResource = "Missing Resource";
	            if(camera.location == null)
	                throw new MissingResourceException(missingResource,Camera.class.getSimpleName(),"location");
	            if(camera.vTo == null || camera.vUp == null)
	                throw new MissingResourceException(missingResource,Camera.class.getSimpleName(),"direction");
	            if(camera.height == 0.0 || camera.width == 0.0)
	                throw new MissingResourceException(missingResource,Camera.class.getSimpleName(),"vpSize");// parameters== null מאותחל 
	            if(camera.distance == 0.0)
	                throw new MissingResourceException(missingResource,Camera.class.getSimpleName(),"vpDistance");

	            if(camera.vTo.crossProduct(camera.vUp).length() == 0)
	                throw new IllegalArgumentException("Vto and Vup are parallel");
	            if(camera.height < 0.0 || camera.width < 0.0)
	                throw new IllegalArgumentException("Negative size");// checking the parameters himself 
	            if(camera.distance < 0.0)
	                throw new IllegalArgumentException("Negative distance");

				if (camera.imageWriter == null)
					throw new MissingResourceException(missing, nameClass, "imageWriter");

				if (camera.rayTracer == null)
					throw new MissingResourceException(missing, nameClass, "rayTracer");


				try
	            {
		            return (Camera)camera.clone();
	            } catch (CloneNotSupportedException e) {
	            
	            	throw  new RuntimeException(e);
	            }
	        }
	}
}