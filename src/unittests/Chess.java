package unittests;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import geometries.Geometries;
import geometries.Intersectable;
import geometries.Polygon;
import geometries.Sphere;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class Chess {

    @Test
    public void cubics2() {
        Scene scene = new Scene("chess sphere");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        Material material = new Material(1, 0.1, 10, 0, 1);

        scene.addGeometries(
                cubic(new Point3D(0, 0, 30), 2, new Color(java.awt.Color.red), new Material(0.4, 0.3, 30, 0.8, 0)),
                cubic(new Point3D(0, 8, 30), 2, new Color(java.awt.Color.blue), new Material(0.4, 0.3, 100, 0.8, 0)),
                cubic(new Point3D(8, 0, 30), 2, new Color(java.awt.Color.blue), new Material(0.4, 0.3, 100, 0.8, 0)),
                cubic(new Point3D(0, -8, 30), 2, new Color(java.awt.Color.blue), new Material(0.4, 0.3, 100, 0.8, 0)),
                cubic(new Point3D(-8, 0, 30), 2, new Color(java.awt.Color.blue), new Material(0.4, 0.3, 100, 0.8, 0)),
                cubic(new Point3D(-8, -8, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(8, -8, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(-8, 8, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(8, 8, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(0, -16, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(0, 16, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(16, 0, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(-16, 0, 30), 2, new Color(java.awt.Color.yellow), material),
                new Polygon(new Color(java.awt.Color.black), new Material(0.4, 0.3, 100, 0, 1), new Point3D(-50, 25, -10), new Point3D(50, 25, -10), new Point3D(50, 20, 100), new Point3D(-50, 20, 100)),
                new Sphere(new Color(java.awt.Color.CYAN), material, new Point3D(-16, -16, 5), 3),
                new Sphere(new Color(java.awt.Color.CYAN), material, new Point3D(16, 16, 5), 3),
                new Sphere(new Color(java.awt.Color.CYAN), material, new Point3D(-16, 16, 60), 3),
                new Sphere(new Color(java.awt.Color.CYAN), material, new Point3D(16, -16, 60), 3)
        );


        scene.addLights(new PointLight(new Color(100, 300, 12), new Point3D(50, -50, -50), 1,
                0.0004, 0.0000006));

        scene.getCamera().setDepthOfFiled(30, 1, 100);
        ImageWriter imageWriter = new ImageWriter("cubic2", 50, 50, 300, 300);
        Render render = new Render(imageWriter, scene);
        render.setMultithreading(3);
        render.setDebugPrint();
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void cubics3() {
        Scene scene = new Scene("chess sphere");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        Material material = new Material(1, 0.1, 10, 0, 1);

        scene.addGeometries(
                cubic(new Point3D(0, 0, 30), 2, new Color(java.awt.Color.red), new Material(0.4, 0.3, 30, 0.8, 0)),
                cubic(new Point3D(0, 8, 30), 2, new Color(java.awt.Color.blue), new Material(0.4, 0.3, 100, 0.8, 0)),
                cubic(new Point3D(8, 0, 30), 2, new Color(java.awt.Color.blue), new Material(0.4, 0.3, 100, 0.8, 0)),
                cubic(new Point3D(0, -8, 30), 2, new Color(java.awt.Color.blue), new Material(0.4, 0.3, 100, 0.8, 0)),
                cubic(new Point3D(-8, 0, 30), 2, new Color(java.awt.Color.blue), new Material(0.4, 0.3, 100, 0.8, 0)),
                cubic(new Point3D(-8, -8, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(8, -8, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(-8, 8, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(8, 8, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(0, -16, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(0, 16, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(16, 0, 30), 2, new Color(java.awt.Color.yellow), material),
                cubic(new Point3D(-16, 0, 30), 2, new Color(java.awt.Color.yellow), material),
                new Polygon(new Color(java.awt.Color.black), new Material(0.4, 0.3, 100, 0, 1), new Point3D(-50, 25, -10), new Point3D(50, 25, -10), new Point3D(50, 20, 100), new Point3D(-50, 20, 100)),
                new Sphere(new Color(java.awt.Color.CYAN), material, new Point3D(-16, -16, 5), 3),
                new Sphere(new Color(java.awt.Color.CYAN), material, new Point3D(16, 16, 5), 3),
                new Sphere(new Color(java.awt.Color.CYAN), material, new Point3D(-16, 16, 60), 3),
                new Sphere(new Color(java.awt.Color.CYAN), material, new Point3D(16, -16, 60), 3)
        );


        scene.addLights(new PointLight(new Color(100, 300, 12), new Point3D(50, -50, -50), 1,
                0.0004, 0.0000006));

        scene.getCamera().setDepthOfFiled(30, 1, 100);
        ImageWriter imageWriter = new ImageWriter("cubic3", 50, 50, 300, 300);
        Render render = new Render(imageWriter, scene);
        render.setMultithreading(3);
        render.setDebugPrint();
        render.oldrenderImage();
        render.writeToImage();
    }

    private Intersectable cubic(Point3D point, double size, Color color, Material material) {
        Geometries list = new Geometries();
        double x = point.getX().get();
        double y = point.getY().get();
        double z = point.getZ().get();

        list.add(new Polygon(color, material, new Point3D(x - size, y - (size * 2), z), new Point3D(x, y - (size * 2), z - size), new Point3D(x + size, y - (size * 2), z), new Point3D(x, y - (size * 2), z + size)),
                new Polygon(color, material, new Point3D(x, y - size, z - (size * 2)), new Point3D(x + size, y, z - (size * 2)), new Point3D(x, y + size, z - (size * 2)), new Point3D(x - size, y, z - (size * 2))),
                new Polygon(color, material, new Point3D(x, y + (size * 2), z - size), new Point3D(x + size, y + (size * 2), z), new Point3D(x, y + (size * 2), z + size), new Point3D(x - size, y + (size * 2), z)),
                new Polygon(color, material, new Point3D(x - (size * 2), y - size, z), new Point3D(x - (size * 2), y, z - size), new Point3D(x - (size * 2), y + size, z), new Point3D(x - (size * 2), y, z + size)),
                new Polygon(color, material, new Point3D(x + (size * 2), y - size, z), new Point3D(x + (size * 2), y, z - size), new Point3D(x + (size * 2), y + size, z), new Point3D(x + (size * 2), y, z + size)),
                new Polygon(color, material, new Point3D(x, y - size, z + (size * 2)), new Point3D(x - size, y, z + (size * 2)), new Point3D(x, y + size, z + (size * 2)), new Point3D(x + size, y, z + (size * 2))),
                new Polygon(color, material, new Point3D(x, y - (size * 2), z - size), new Point3D(x + size, y - (size * 2), z), new Point3D(x + (size * 2), y - size, z), new Point3D(x + (size * 2), y, z - size), new Point3D(x + size, y, z - (size * 2)), new Point3D(x, y - size, z - (size * 2))),
                new Polygon(color, material, new Point3D(x + size, y, z - (size * 2)), new Point3D(x + (size * 2), y, z - size), new Point3D(x + (size * 2), y + size, z), new Point3D(x + size, y + (size * 2), z), new Point3D(x, y + (size * 2), z - size), new Point3D(x, y + size, z - (size * 2))),
                new Polygon(color, material, new Point3D(x, y - (size * 2), z - size), new Point3D(x, y - size, z - (size * 2)), new Point3D(x - size, y, z - (size * 2)), new Point3D(x - (size * 2), y, z - size), new Point3D(x - (size * 2), y - size, z), new Point3D(x - size, y - (size * 2), z)),
                new Polygon(color, material, new Point3D(x - (size * 2), y, z - size), new Point3D(x - size, y, z - (size * 2)), new Point3D(x, y + size, z - (size * 2)), new Point3D(x, y + (size * 2), z - size), new Point3D(x - size, y + (size * 2), z), new Point3D(x - (size * 2), y + size, z)),
                new Polygon(color, material, new Point3D(x - size, y - (size * 2), z), new Point3D(x, y - (size * 2), z + size), new Point3D(x, y - size, z + (size * 2)), new Point3D(x - size, y, z + (size * 2)), new Point3D(x - (size * 2), y, z + size), new Point3D(x - (size * 2), y - size, z)),
                new Polygon(color, material, new Point3D(x + size, y - (size * 2), z), new Point3D(x + (size * 2), y - size, z), new Point3D(x + (size * 2), y, z - size), new Point3D(x + size, y, z - (size * 2)), new Point3D(x, y - size, z - (size * 2)), new Point3D(x, y - (size * 2), z - size)),
                new Polygon(color, material, new Point3D(x + size, y, z + (size * 2)), new Point3D(x + (size * 2), y, z + size), new Point3D(x + (size * 2), y + size, z), new Point3D(x + size, y + (size * 2), z), new Point3D(x, y + (size * 2), z + size), new Point3D(x, y + size, z + (size * 2))),
                new Polygon(color, material, new Point3D(x - size, y, z + (size * 2)), new Point3D(x, y + size, z + (size * 2)), new Point3D(x, y + (size * 2), z + size), new Point3D(x - size, y + (size * 2), z), new Point3D(x - (size * 2), y + size, z), new Point3D(x - (size * 2), y, z + size))
        );
        return list;
    }


}