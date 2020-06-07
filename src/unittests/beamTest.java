package unittests;

import elements.AmbientLight;
import elements.Camera;
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

public class beamTest {
    /**
     * Produce a picture of a sphere as a sun and and a beach
     */
    @Test
    public void depth10() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
        scene.getCamera().setDepthOfFiled(10, 1, 15);
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
        //scene.addGeometries(buildHouse(x, y, z- i * 500 ,20, new Color(20, 15, 150), new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100)));

        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
                0.0004, 0.0000006));


        ImageWriter imageWriter = new ImageWriter("depth10", 30, 30, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere as a sun and and a beach
     */
    @Test
    public void depth5() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
        scene.getCamera().setDepthOfFiled(5, 1, 15);
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
        //scene.addGeometries(buildHouse(x, y, z- i * 500 ,20, new Color(20, 15, 150), new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100)));

        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
                0.0004, 0.0000006));


        ImageWriter imageWriter = new ImageWriter("depth5", 30, 30, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere as a sun and and a beach
     */
    @Test
    public void depth20() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
        scene.getCamera().setDepthOfFiled(20, 1, 15);
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
        //scene.addGeometries(buildHouse(x, y, z- i * 500 ,20, new Color(20, 15, 150), new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100)));

        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
                0.0004, 0.0000006));


        ImageWriter imageWriter = new ImageWriter("depth20", 30, 30, 500, 500);
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
