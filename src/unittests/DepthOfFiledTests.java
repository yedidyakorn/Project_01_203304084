package unittests;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Sphere;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * tests for Depth of filed
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class DepthOfFiledTests {
    /**
     * Produce a picture of a row of spheres lighted by a spot light.
     * with Depth of filed with focal distance of 10, aperture 0f 1 and than 0.5, and 15 rays in the beam
     */
    @Test
    public void depth10() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
        int x = 0, y = 10, z = 0;
        for (int i = 0; i < 7; i++) {
            scene.addGeometries(
                    new Sphere(new Color(java.awt.Color.MAGENTA), new Material(0.4, 0.3, 100, 0.3, 0),
                            new Point3D(x, y - 2 * i, z + i * 5), 2),
                    new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0),
                            new Point3D(x + 3, y - 2 * i, z + i * 5), 2),
                    new Sphere(new Color(java.awt.Color.GREEN), new Material(0.4, 0.3, 100, 0.3, 0),
                            new Point3D(x - 3, y - 2 * i, z + i * 5), 2));
        }

        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
                0.0004, 0.0000006));

        scene.getCamera().setDepthOfFiled(10, 1, 75);
        ImageWriter imageWriter = new ImageWriter("depth10_1", 30, 30, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.setMultithreading(3);
        render.setDebugPrint();
        render.oldrenderImage();
        render.writeToImage();

//        scene.getCamera().setDepthOfFiled(10, 0.5, 15);
//        ImageWriter imageWriter1 = new ImageWriter("depth10_0.5", 30, 30, 500, 500);
//        render = new Render(imageWriter1, scene);
//        render.setMultithreading(3);
//        render.setDebugPrint();
//        render.oldrenderImage();
//        render.writeToImage();
    }

//    /**
//     * Produce a picture of a row of spheres lighted by a spot light.
//     * with Depth of filed with focal distance of 5, aperture 0f 1 and than 0.5, and 15 rays in the beam
//     */
//    @Test
//    public void depth5() {
//        Scene scene = new Scene("Test scene");
//        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
//        scene.setDistance(1000);
//        scene.setBackground(new Color(java.awt.Color.BLACK));
//        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
//        int x = 0, y = 10, z = 0;
//        for (int i = 0; i < 7; i++) {
//            scene.addGeometries(
//                    new Sphere(new Color(java.awt.Color.MAGENTA), new Material(0.4, 0.3, 100, 0.3, 0),
//                            new Point3D(x, y - 2 * i, z + i * 5), 2),
//                    new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0),
//                            new Point3D(x + 3, y - 2 * i, z + i * 5), 2),
//                    new Sphere(new Color(java.awt.Color.GREEN), new Material(0.4, 0.3, 100, 0.3, 0),
//                            new Point3D(x - 3, y - 2 * i, z + i * 5), 2));
//        }
//
//        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
//                0.0004, 0.0000006));
//
//        scene.getCamera().setDepthOfFiled(5, 1, 15);
//        ImageWriter imageWriter = new ImageWriter("depth5_1", 30, 30, 500, 500);
//        Render render = new Render(imageWriter, scene);
//        render.setMultithreading(3);
//        render.setDebugPrint();
//        render.renderImage();
//        render.writeToImage();
//
//        scene.getCamera().setDepthOfFiled(5, 0.5, 15);
//        ImageWriter imageWriter1 = new ImageWriter("depth5_0.5", 30, 30, 500, 500);
//        render = new Render(imageWriter1, scene);
//        render.setMultithreading(3);
//        render.setDebugPrint();
//        render.renderImage();
//        render.writeToImage();
//    }
//
//    /**
//     * Produce a picture of a row of spheres lighted by a spot light.
//     * with Depth of filed with focal distance of 20, aperture 0f 1 and than 0.5, and 15 rays in the beam
//     */
//    @Test
//    public void depth20() {
//        Scene scene = new Scene("Test scene");
//        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
//        scene.setDistance(1000);
//        scene.setBackground(new Color(java.awt.Color.BLACK));
//        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
//        int x = 0, y = 10, z = 0;
//        for (int i = 0; i < 7; i++) {
//            scene.addGeometries(
//                    new Sphere(new Color(java.awt.Color.MAGENTA), new Material(0.4, 0.3, 100, 0.3, 0),
//                            new Point3D(x, y - 2 * i, z + i * 5), 2),
//                    new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0),
//                            new Point3D(x + 3, y - 2 * i, z + i * 5), 2),
//                    new Sphere(new Color(java.awt.Color.GREEN), new Material(0.4, 0.3, 100, 0.3, 0),
//                            new Point3D(x - 3, y - 2 * i, z + i * 5), 2));
//        }
//
//        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
//                0.0004, 0.0000006));
//
//        scene.getCamera().setDepthOfFiled(20, 1, 15);
//        ImageWriter imageWriter = new ImageWriter("depth20_1", 30, 30, 500, 500);
//        Render render = new Render(imageWriter, scene);
//        render.setMultithreading(3);
//        render.setDebugPrint();
//        render.renderImage();
//        render.writeToImage();
//
//        scene.getCamera().setDepthOfFiled(20, 0.5, 15);
//        ImageWriter imageWriter1 = new ImageWriter("depth20_0.5", 30, 30, 500, 500);
//        render = new Render(imageWriter1, scene);
//        render.setMultithreading(3);
//        render.setDebugPrint();
//        render.renderImage();
//        render.writeToImage();
//    }
//
//    /**
//     * checks if all the rays in the beam of a specific pixel start from the size of the aperture.
//     * builds a beam from one pixel with 150 rays, aperture radius size is 1.
//     * No  picture produced
//     */
//    @Test
//    public void testBeamRays() {
//        Scene scene = new Scene("Test scene");
//        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
//        scene.setDistance(1000);
//        scene.getCamera().setDepthOfFiled(10, 1, 150);
//        List<Ray> rays = scene.getCamera().constructRaysThroughPixel(1000, 1000, 16, 16, 1000, 30, 30);
//        Ray ray = scene.getCamera().constructRayThroughPixel(1000, 1000, 16, 16, 1000, 30, 30);
//        Point3D pij = ray.getPoint(1000 / (scene.getCamera().getvTo().dotProduct(ray.getDirection())));
//        for (Ray temp : rays)
//            assertTrue(temp.getPoint().distance(pij) <= 1);
//    }

}
