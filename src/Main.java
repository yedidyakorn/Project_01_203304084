/*
  @author Eliezer Horowitz, 305739807, Eli191191@gmail.com
 * @author Yedidya Korn, 203304084, yedidyas5@gmail.com
 */

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import geometries.Geometries;
import geometries.Intersectable;
import geometries.Polygon;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import static java.lang.System.out;

public final class Main {

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
                new Point3D(x, y, z), new Point3D(x - size, y, z), new Point3D(x - size, y + size, z), new Point3D(x, y + size, z)));
        list.add(new Polygon(wallColor, material,
                new Point3D(x, y, z), new Point3D(x, y + size, z), new Point3D(x, y + size, z + size), new Point3D(x, y, z + size)));
        list.add(new Polygon(wallColor, material,
                new Point3D(x - size, y + size, z + size), new Point3D(x, y + size, z + size), new Point3D(x, y + size, z), new Point3D(x - size, y + size, z)));
        list.add(new Polygon(wallColor, material,
                new Point3D(x - size, y + size, z), new Point3D(x - size, y + size, z + size), new Point3D(x - size, y, z + size), new Point3D(x - size, y, z)));
        list.add(new Polygon(wallColor, material,
                new Point3D(x, y, z), new Point3D(x, y, z + size), new Point3D(x - size, y, z + size), new Point3D(x - size, y, z)));
        list.add(new Triangle(roofColor, material,
                new Point3D(x - size, y, z + size), new Point3D(x, y, z + size), new Point3D(x - size / 2, y + size / 2, z + size + size / 2)));
        list.add(new Triangle(roofColor, material,
                new Point3D(x, y + size, z + size), new Point3D(x, y, z + size), new Point3D(x - size / 2, y + size / 2, z + size + size / 2)));
        list.add(new Triangle(roofColor, material,
                new Point3D(x - size, y + size, z + size), new Point3D(x, y + size, z + size), new Point3D(x - size / 2, y + size / 2, z + size + size / 2)));
        list.add(new Triangle(roofColor, material,
                new Point3D(x - size, y + size, z + size), new Point3D(x - size, y, z + size), new Point3D(x - size / 2, y + size / 2, z + size + size / 2)));
        return list;
    }

    /**
     * function that builds a road
     *
     * @param x - coordinate
     * @param y - coordinate
     * @param z - coordinate
     * @return
     */
    private static Intersectable road(double x, double y, double z, double houseSize, double streetWidth, double housesDistance) {
        double roadWidth = streetWidth - houseSize;
        Geometries list = new Geometries();
        list.add(new Polygon(new Color(java.awt.Color.GRAY),
                new Point3D(x, y, z), new Point3D(x - housesDistance, y, z), new Point3D(x - housesDistance, y + roadWidth, z), new Point3D(x, y + roadWidth, z)));
        list.add(new Polygon(new Color(java.awt.Color.WHITE),
                new Point3D(x, y + roadWidth / 2 - 2, z + 0.1), new Point3D(x - housesDistance / 4, y + roadWidth / 2 - 2, z + 0.1),
                new Point3D(x - housesDistance / 4, y + roadWidth / 2 + 2, z + 0.1), new Point3D(x, y + roadWidth / 2 + 2, z + 0.1)));
        return list;
    }

    /**
     * function that builds a road with houses on the sides
     *
     * @param args
     */
    public static void main(String[] args) {

        Scene scene = new Scene("road1");
        scene.setCamera(new Camera(new Point3D(1000, 0, 200), new Vector(-10, 0, -2), new Vector(-0.1, 0, 0.50)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
        double x = 450, y = -41, z = 15, houseSize = 20, streetWidth = 70, housesDistance = 80;

        for (int i = 1; i <= 10; i++) {
            scene.addGeometries(buildHouse(x - i * housesDistance, y, z, houseSize, new Color(20, 15, 150), new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100)));
            scene.addGeometries(buildHouse(x - i * housesDistance, y + streetWidth, z, houseSize, new Color(20, 15, 150), new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100)));
            scene.addGeometries(road(x - i * housesDistance, y + houseSize, z, houseSize, streetWidth, housesDistance));
        }


        scene.addLights(new DirectionalLight(new Color(500, 200, 300), new Vector(1, -100, -500)));

        ImageWriter imageWriter = new ImageWriter("road1", 150, 150, 500, 500);
        imageWriter.setAperture(10);
        imageWriter.setFocalDistance(200);
        imageWriter.setNumOfRays(5);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
        out.println("mission accomplished");

    }

}
