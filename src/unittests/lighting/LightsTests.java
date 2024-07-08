package unittests.lighting;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * @author Dan
 */
public class LightsTests {
   /** First scene for some of tests */
   private final Scene          scene1                  = new Scene("Test scene");
   /** Second scene for some of tests */
   private final Scene          scene2                  = new Scene("Test scene")
      .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

   /** First camera builder for some of tests */
   private final Camera.Builder camera1                 = Camera.getBuilder()
      .setRayTracer(new SimpleRayTracer(scene1))
      .setLocation(new Point(0, 0, 1000))
      .setDirection( new Vector(0,0,-1), new Vector(0,1,0))
      .setVpSize(150, 150).setVpDistance(1000);
   /** Second camera builder for some of tests */
   private final Camera.Builder camera2                 = Camera.getBuilder()
      .setRayTracer(new SimpleRayTracer(scene2))
      .setLocation(new Point(0, 0, 1000))
      .setDirection( new Vector(0,0,-1), new Vector(0,1,0))
      .setVpSize(200, 200).setVpDistance(1000);

   /** Shininess value for most of the geometries in the tests */
   private static final int     SHININESS               = 301;
   /** Diffusion attenuation factor for some of the geometries in the tests */
   private static final double  KD                      = 0.5;
   /** Diffusion attenuation factor for some of the geometries in the tests */
   private static final Double3 KD3                     = new Double3(0.2, 0.6, 0.4);

   /** Specular attenuation factor for some of the geometries in the tests */
   private static final double  KS                      = 0.5;
   /** Specular attenuation factor for some of the geometries in the tests */
   private static final Double3 KS3                     = new Double3(0.2, 0.4, 0.3);

   /** Material for some of the geometries in the tests */
   private final Material       material                = new Material().setKd(KD3).setKs(KS3).setShininess(SHININESS);
   /** Light color for tests with triangles */
   private final Color          trianglesLightColor     = new Color(800, 500, 250);
   /** Light color for tests with sphere */
   private final Color          sphereLightColor        = new Color(800, 500, 0);
   /** Color of the sphere */
   private final Color          sphereColor             = new Color(BLUE).reduce(2);

   /** Center of the sphere */
   private final Point          sphereCenter            = new Point(0, 0, -50);
   /** Radius of the sphere */
   private static final double  SPHERE_RADIUS           = 50d;

   /** The triangles' vertices for the tests with triangles */
   private final Point[]        vertices                =
      {
        // the shared left-bottom:
        new Point(-110, -110, -150),
        // the shared right-top:
        new Point(95, 100, -150),
        // the right-bottom
        new Point(110, -110, -150),
        // the left-top
        new Point(-75, 78, 100)
      };
   /** Position of the light in tests with sphere */
   private final Point          sphereLightPosition     = new Point(-50, -50, 25);
   /** Light direction (directional and spot) in tests with sphere */
   private final Vector         sphereLightDirection    = new Vector(1, 1, -0.5);
   /** Position of the light in tests with triangles */
   private final Point          trianglesLightPosition  = new Point(30, 10, -100);
   /** Light direction (directional and spot) in tests with triangles */
   private final Vector         trianglesLightDirection = new Vector(-2, -2, -2);

   /** The sphere in appropriate tests */
   private final Geometry       sphere                  = new Sphere( SPHERE_RADIUS,sphereCenter)
      .setEmission(sphereColor).setMaterial(new Material().setKd(KD).setKs(KS).setShininess(SHININESS));
   /** The first triangle in appropriate tests */
   private final Geometry       triangle1               = new Triangle(vertices[0], vertices[1], vertices[2])
      .setMaterial(material);
   /** The first triangle in appropriate tests */
   private final Geometry       triangle2               = new Triangle(vertices[0], vertices[1], vertices[3])
      .setMaterial(material);

   /** Produce a picture of a sphere lighted by a directional light */
   @Test
   public void sphereDirectional() {
      scene1.geometries.add(sphere);
      scene1.lights.add(new DirectionalLight(sphereLightColor, sphereLightDirection ));

      camera1.setImageWriter(new ImageWriter("lightSphereDirectional", 500, 500))
         .build()
         .renderImage()
         .writeToImage();
   }

   /** Produce a picture of a sphere lighted by a  point light */
   @Test
   public void spherePoint() {
      scene1.geometries.add(sphere);
      scene1.lights.add(new PointLight(sphereLightColor, sphereLightPosition)
         .setKl(0.001).setKq(0.0002));

      camera1.setImageWriter(new ImageWriter("lightSpherePoint", 500, 500))
         .build()
         .renderImage()
         .writeToImage();
   }

   /** Produce a picture of a sphere lighted by a spotlight */
   @Test
   public void sphereSpot() {
      scene1.geometries.add(sphere);
      scene1.lights.add(new SpotLight(sphereLightColor, sphereLightPosition, sphereLightDirection)
         .setKl(0.001).setKq(0.0001));
      camera1.setImageWriter(new ImageWriter("lightSphereSpot", 500, 500))
         .build()
         .renderImage()
         .writeToImage();
   }

   /** Produce a picture of two triangles lighted by a directional light */
   @Test
   public void trianglesDirectional() {
      scene2.geometries.add(triangle1, triangle2);
      scene2.lights.add(new DirectionalLight(trianglesLightColor, trianglesLightDirection));

      camera2.setImageWriter(new ImageWriter("lightTrianglesDirectional", 500, 500)) //
         .build()
         .renderImage()
         .writeToImage();
   }

   /** Produce a picture of two triangles lighted by a point light */
   @Test
   public void trianglesPoint() {
      scene2.geometries.add(triangle1, triangle2);
      scene2.lights.add(new PointLight(trianglesLightColor, trianglesLightPosition)
         .setKl(0.001).setKq(0.0002));

      camera2.setImageWriter(new ImageWriter("lightTrianglesPoint", 500, 500)) //
         .build() //
         .renderImage() //
         .writeToImage(); //
   }

   /** Produce a picture of two triangles lighted by a spotlight */
   @Test
   public void trianglesSpot() {
      scene2.geometries.add(triangle1, triangle2);
      scene2.lights.add(new SpotLight(trianglesLightColor, trianglesLightPosition, trianglesLightDirection)
         .setKl(0.001).setKq(0.0001));

      camera2.setImageWriter(new ImageWriter("lightTrianglesSpot", 500, 500))
         .build()
         .renderImage()
         .writeToImage();
   }

   /** Produce a picture of a sphere lighted by a narrow spotlight */
   @Test
   public void sphereSpotSharp() {
      scene1.geometries.add(sphere);
      scene1.lights
         .add(new SpotLight(sphereLightColor, sphereLightPosition, new Vector(1, 1, -0.5))
            .setKl(0.001).setKq(0.00004).setNarrowBeam(10));

      camera1.setImageWriter(new ImageWriter("lightSphereSpotSharp", 500, 500))
         .build()
         .renderImage()
         .writeToImage();
   }

   /** Produce a picture of two triangles lighted by a narrow spotlight */
   @Test
   public void trianglesSpotSharp() {
      scene2.geometries.add(triangle1, triangle2);
      scene2.lights.add(new SpotLight(trianglesLightColor, trianglesLightPosition, trianglesLightDirection)
         .setKl(0.001).setKq(0.00004).setNarrowBeam(10));

      camera2.setImageWriter(new ImageWriter("lightTrianglesSpotSharp", 500, 500))
         .build()
         .renderImage()
         .writeToImage();
   }


@Test
public void multipleLightSourcesSphere() {
   Scene scene = new Scene("Test scene");
   scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.blue), new Double3(0.15,0,0)));

   scene.geometries.add(
           new Sphere(50,new Point(0, 0, -100))
                   .setEmission(new Color(java.awt.Color.BLUE))
                   .setMaterial(new Material().setKd(0.4).setKs(0.3).setnShininess(100))
   );

   scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(-50, -50, 50), new Vector(1, 1, -2))
           .setKl(0.0001).setKq(0.000005));
   scene.lights.add(new PointLight(new Color(500, 300, 0), new Point(50, 50, -50))
           .setKl(0.0005).setKq(0.0005));
   scene.lights.add(new DirectionalLight(new Color(400, 300, 300), new Vector(1, 0, -1)));

   ImageWriter imageWriter = new ImageWriter("sphereMultipleLights", 500, 500);
   Camera camera = Camera.getBuilder()
           .setLocation(new Point(0, 0, 0))
           .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
           .setVpSize(150, 150)
           .setVpDistance(100)
           .setImageWriter(imageWriter)
           .setRayTracer(new SimpleRayTracer(scene))
           .build();

   camera.renderImage();
   camera.writeToImage();
}


@Test
public void multipleLightSourcesTriangles() {
   Scene scene = new Scene("Test scene");
   scene.setAmbientLight(new AmbientLight(new Color(blue),new Double3(0.15,0,0)));

   scene.geometries.add(
           new Triangle(new Point(-150, 150, -150), new Point(150, 150, -150), new Point(75, -75, -150))
                   .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300)),
           new Triangle(new Point(-150, 150, -150), new Point(-70, -70, -50), new Point(75, -75, -150))
                   .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300))
   );

   scene.lights.add(new SpotLight(new Color(133, 534, 200), new Point(60, 60, 100), new Vector(-1,-1,-2))
           .setKl(0.0001).setKq(0.000005));
   scene.lights.add(new PointLight(new Color(890, 456, 675), new Point(-50, -50, -50))
           .setKl(0.0005).setKq(0.0005));
   scene.lights.add(new DirectionalLight(new Color(121, 123, 324), new Vector(0, 0, -1)));

   ImageWriter imageWriter = new ImageWriter("trianglesMultipleLights", 500, 500);
   Camera camera = Camera.getBuilder()
           .setLocation(new Point(0, 0, 100))
           .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
           .setVpSize(200, 200)
           .setVpDistance(100)
           .setImageWriter(imageWriter)
           .setRayTracer(new SimpleRayTracer(scene))
           .build();

   camera.renderImage();
   camera.writeToImage();
}
}
