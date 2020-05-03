package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test rendering abasic image
 *
 * @author Dan
 */
public class RenderTests {

    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(100);
        scene.setBackground(new Color(75, 127, 90));
        scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1));

        scene.addGeometries(new Sphere(new Point3D(0, 0, 100), 50));

        scene.addGeometries(
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

        ImageWriter imageWriter = new ImageWriter("base render test", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(50, java.awt.Color.YELLOW);
        render.writeToImage();
    }

    /**
     * tests if the function works. gets a list of points on a ray and needs to find closest point to camera
     *
     * test Requires changing the function to public
     */
    @Test
    public void getClosestPointTest() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera (new Point3D(4, 3.5, 9), new Vector(0, 0, -1),new Vector(0,-1,0)));
        Geometries geometries = new Geometries();
        geometries.add(new Triangle(new Point3D(3, 3, 8), new Point3D(4, 4, 8), new Point3D(5, 3, 8)));
        geometries.add(new Sphere(new Point3D(3, 3, 4), 2));
        geometries.add(new Plane(new Point3D(3, 3, 1), new Vector(new Point3D(0, 0, 1))));
        ImageWriter imageWriter = new ImageWriter("base render test", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        List<Point3D> result;


        // ============ Equivalence Partitions Tests ==============

        // TC01: Closest Point out of list of 4 points
        result = geometries.findIntersections(new Ray(new Point3D(4, 3.5, 9), new Vector(0, 0, -1)));
        assertEquals("Wrong point", new Point3D(4, 3.5, 8), render.getClosestPoint(result));
    }


}
