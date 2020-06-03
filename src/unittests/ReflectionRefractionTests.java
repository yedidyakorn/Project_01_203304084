
package unittests;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.*;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0),
                        new Point3D(0, 0, 50), 50),
                new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), new Point3D(0, 0, 50), 25));

        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
                0.0004, 0.0000006));

        ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.addGeometries(
                new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.5, 0), new Point3D(-950, 900, 1000), 400),
                new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), new Point3D(-950, 900, 1000), 200),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

        scene.addLights(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, 750, 150),
                new Vector(-1, 1, 4), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
     * producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        new Point3D(60, -50, 50), 30));

        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere as a sun and and a beach
     */
    @Test
    public void sunnyDay() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Polygon(Color.BLACK, new Material(0.5, 0.5, 60, 0, 0.2), new Point3D(200, 200, 100), new Point3D(-200, 200, 100), new Point3D(-200, 200, 5000), new Point3D(200, 200, 5000)),
                new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 0.2, 30, 1, 0), //
                        new Point3D(0, -70, 300), 30),
                new Sphere(new Color(255, 165, 0), new Material(0.2, 0.2, 30, 1, 0), //
                        new Point3D(0, -70, 300), 10),
                new Sphere(new Color(255, 165, 0), new Material(0.2, 0.2, 30, 0, 1), //
                        new Point3D(-25, 100, 2500), 10),
                new Sphere(new Color(java.awt.Color.WHITE), new Material(0.2, 0.2, 30, 0, 1), //
                        new Point3D(0, 100, 2500), 10),
                new Polygon(new Color(java.awt.Color.RED), new Material(0.2, 0.2, 30, 0.3, 0), new Point3D(50, 25, 100), new Point3D(-50, 25, 100), new Point3D(-100, 45, 5000), new Point3D(100, 45, 5000)),
                buildHouse(100, 190, 2000, 60, new Color(20, 15, 150), new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100)));

        scene.addLights(new PointLight(new Color(700, 400, 400), //
                new Point3D(0, -70, 300), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("sunnyDay", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere as a sun and and a beach
     * a different angle
     */
    @Test
    public void sunnyDay2() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(300, 0, -1800), new Vector(-0.1, 0, 0.9), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Polygon(Color.BLACK, new Material(0.5, 0.5, 60, 0, 0.2),
                        new Point3D(200, 200, 100), new Point3D(-200, 200, 100), new Point3D(-200, 200, 5000), new Point3D(200, 200, 5000)),
                new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 0.2, 30, 1, 0), //
                        new Point3D(0, -70, 300), 30),
                new Sphere(new Color(255, 165, 0), new Material(0.2, 0.2, 30, 1, 0), //
                        new Point3D(0, -70, 300), 10),
                new Sphere(new Color(255, 165, 0), new Material(0.2, 0.2, 30, 1, 0), //
                        new Point3D(-25, 100, 2500), 10),
                new Sphere(new Color(java.awt.Color.WHITE), new Material(0.2, 0.2, 30, 1, 0), //
                        new Point3D(0, 100, 2500), 10),
                new Polygon(new Color(java.awt.Color.RED), new Material(0.2, 0.2, 30, 0.3, 0),
                        new Point3D(50, 25, 100), new Point3D(-50, 25, 100), new Point3D(-100, 45, 5000), new Point3D(100, 45, 5000)),
                buildHouse(100, 190, 2000, 60, new Color(20, 15, 150), new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100)));

        scene.addLights(new PointLight(new Color(700, 400, 400), //
                new Point3D(0, -70, 300), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("sunnyDay2", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * function that builds a squared house
     *
     * @param x         - coordinate
     * @param y         - coordinate
     * @param z         - coordinate
     * @param size      - size of the each wall
     * @param wallColor - wall Color
     * @param roofColor - roof Color
     * @param material  - house material
     * @return a list of Geometries
     */
    public static Intersectable buildHouse(double x, double y, double z, double size, Color wallColor, Color roofColor, Material material) {
        Geometries list = new Geometries();
        list.add(new Polygon(wallColor, material,
                new Point3D(x, y, z), new Point3D(x + size, y, z), new Point3D(x + size, y - size, z), new Point3D(x, y - size, z)));
        list.add(new Polygon(wallColor, material,
                new Point3D(x, y, z), new Point3D(x, y - size, z), new Point3D(x, y - size, z + size), new Point3D(x, y, z + size)));
        list.add(new Polygon(wallColor, material,
                new Point3D(x + size, y - size, z + size), new Point3D(x, y - size, z + size), new Point3D(x, y - size, z), new Point3D(x + size, y - size, z)));
        list.add(new Polygon(wallColor, material,
                new Point3D(x + size, y - size, z), new Point3D(x + size, y - size, z + size), new Point3D(x + size, y, z + size), new Point3D(x + size, y, z)));
        list.add(new Polygon(wallColor, material,
                new Point3D(x, y, z), new Point3D(x, y, z + size), new Point3D(x + size, y, z + size), new Point3D(x + size, y, z)));
        list.add(new Triangle(roofColor, material,
                new Point3D(x + size, y, z + size), new Point3D(x, y, z + size), new Point3D(x + size / 2, y - size - size / 2, z + size / 2)));
        list.add(new Triangle(roofColor, material,
                new Point3D(x, y - size, z + size), new Point3D(x, y, z + size), new Point3D(x + size / 2, y - size - size / 2, z + size / 2)));
        list.add(new Triangle(roofColor, material,
                new Point3D(x + size, y - size, z + size), new Point3D(x, y - size, z + size), new Point3D(x + size / 2, y - size - size / 2, z + size / 2)));
        list.add(new Triangle(roofColor, material,
                new Point3D(x + size, y - size, z + size), new Point3D(x + size, y, z + size), new Point3D(x + size / 2, y - size - size / 2, z + size / 2)));
        return list;
    }
}
