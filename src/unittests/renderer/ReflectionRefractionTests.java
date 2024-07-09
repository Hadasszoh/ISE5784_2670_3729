/**
 * 
 */
package unittests.renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class ReflectionRefractionTests {
   /** Scene for the tests */
   private final Scene          scene         = new Scene("Test scene");
   /** Camera builder for the tests with triangles */
   private final Camera.Builder cameraBuilder = Camera.getBuilder()
      .setDirection(new Vector(0, 0, -1), new Vector(0,1,0))
      .setRayTracer(new SimpleRayTracer(scene));

   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheres() {
      scene.geometries.add(
                           new Sphere(50d,new Point(0, 0, -50)).setEmission(new Color(BLUE))
                              .setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
                           new Sphere(25d,new Point(0, 0, -50)).setEmission(new Color(RED))
                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
      scene.lights.add(
                       new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
                          .setkL(0.0004).setkQ(0.0000006));

      cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
         .setVpSize(150, 150)
         .setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500))
         .build()
         .renderImage()
         .writeToImage();
   }

   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheresOnMirrors() {
      scene.geometries.add(
                           new Sphere(400d,new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100))
                              .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)
                                 .setkT(new Double3(0.5, 0, 0))),
                           new Sphere(200d,new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20))
                              .setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                                        new Point(670, 670, 3000))
                              .setEmission(new Color(20, 20, 20))
                              .setMaterial(new Material().setkR(1)),
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                                        new Point(-1500, -1500, -2000))
                              .setEmission(new Color(20, 20, 20))
                              .setMaterial(new Material().setkR(new Double3(0.5, 0, 0.4))));
      scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
      scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4))
         .setkL(0.00001).setkQ(0.000005));

      cameraBuilder.setLocation(new Point(0, 0, 10000)).setVpDistance(10000)
         .setVpSize(2500, 2500)
         .setImageWriter(new ImageWriter("reflectionTwoSpheresMirrored", 500, 500))
         .build()
         .renderImage()
         .writeToImage();
   }

   /** Produce a picture of a two triangles lighted by a spot light with a
    * partially
    * transparent Sphere producing partial shadow */
   @Test
   public void trianglesTransparentSphere() {
      scene.geometries.add(
                           new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                                        new Point(75, 75, -150))
                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)),
                           new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150))
                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)),
                           new Sphere( 30d,new Point(60, 50, -50)).setEmission(new Color(BLUE))
                              .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.6)));
      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
      scene.lights.add(
                       new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1))
                          .setkL(4E-5).setkQ(2E-7));

      cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
         .setVpSize(200, 200)
         .setImageWriter(new ImageWriter("refractionShadow", 600, 600))
         .build()
         .renderImage()
         .writeToImage();
   }

/**
 * Produce a picture of five objects lighted by a spot light and point light
 * to show all the effects in one picture
 */
@Test
public void reflectionRefractionFiveObjectsTest() {

   this.scene.setAmbientLight(new AmbientLight(new Color(YELLOW), new Double3(0.15)));

   this.scene.geometries.add( //
           new Triangle(new Point(-150, -150, -115),
                   new Point(150, -150, -135),
                   new Point(75, 75, -150)) //
                   .setMaterial(new Material().setkD(0.5).setkS(0.5)
                           .setnShininess(60)), //

           new Triangle(new Point(-150, -150, -115),
                   new Point(-70, 70, -140),
                   new Point(75, 75, -150)) //
                   .setMaterial(new Material().setkD(0.5).setkS(0.5)
                           .setnShininess(60)), //

           new Sphere(30,new Point(60, 50, -50)) //
                   .setEmission(new Color(java.awt.Color.BLUE)) //
                   .setMaterial(new Material().setkD(0.2).setkS(0.2)
                           .setnShininess(30).setkT(0.6)),

           new Sphere(25.7,new Point(-50, -100, 100)) //
                   .setEmission(new Color(green)) //
                   .setMaterial(new Material().setkD(0.002).setkS(0.2)
                           .setnShininess(30).setkT(0.9)),

           new Sphere(17,new Point(-50, -80, 100)) //
                   .setEmission(new Color(green)) //
                   .setMaterial(new Material().setkD(0.002).setkS(0.2)
                           .setnShininess(30).setkT(0.9)),

           new Sphere( 22,new Point(30, -60, 100)) //
                   .setEmission(new Color(cyan)) //
                   .setMaterial(new Material().setkD(0.002).setkS(0.2)
                           .setnShininess(30).setkT(0.9)),

           new Sphere(10,new Point(-60, 50, 100)) //
                   .setEmission(new Color(yellow)) //
                   .setMaterial(new Material().setkD(0.2).setkS(0.2)
                           .setnShininess(2).setkT(0.8)));

   this.scene.lights.add(new SpotLight(new Color(700, 400, 400),
           new Point(30, 25, 0),
           new Vector(0, 0, -1)) //
           .setkL(4E-5).setkQ(2E-7));

   this.scene.lights.add(new PointLight(new Color(160, 80, 240),
           new Point(-100, -100, 100))//
           .setkL(0.00000000001).setkQ(0.0000000001));

   cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
           .setVpSize(200, 200)
           .setImageWriter(new ImageWriter("reflectionRefractionFiveObjectsTest", 600, 600))
           .build()
           .renderImage() //
           .writeToImage();
}
}
