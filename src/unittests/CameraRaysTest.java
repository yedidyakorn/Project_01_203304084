package unittests;

import static org.junit.Assert.*;

import geometries.*;
import org.junit.Test;
import geometries.Intersectable.GeoPoint;

import elements.Camera;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * tests for integration of rays from Camera and previous calculations
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class CameraRaysTest {

    Camera camera = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
    List<GeoPoint> temp;
    List<GeoPoint> result = new ArrayList<>();

    /**
     * function that sums the number of intersections points of a Geometry shape and a camera from all pixels
     *
     * @param obj a Geometry 3D shape
     * @return number of intersections points
     */
    private int checkAllPixels(Geometry obj) {
        result.clear();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                temp = obj.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (temp != null)
                    result.addAll(temp);
            }
        }
        return result.size();
    }

    /**
     * tests for integration of rays from Camera and rays {@link geometries.Sphere#findIntersections(primitives.Ray)}
     */
    @Test
    public void sphereIntegrationTest() {

        // TC01: small sphere in front of view plane (2 points)
        Sphere sphere1 = new Sphere(new Point3D(0, 0, 3), 1);
        assertEquals("number of intersections isn't equal", checkAllPixels(sphere1), 2);

        // TC02: big Sphere that covers the all pixels but does not get to camera (18 points)
        Sphere sphere2 = new Sphere(new Point3D(0, 0, 2.5), 2.5);
        assertEquals("number of intersections isn't equal", checkAllPixels(sphere2), 18);

        // TC03: big Sphere that covers the center pixels but does cover the corner pixels (10 points)
        Sphere sphere3 = new Sphere(new Point3D(0, 0, 2), 2);
        assertEquals("number of intersections isn't equal", checkAllPixels(sphere3), 10);

        // TC04: big Sphere that covers the view plane and camera (9 points)
        Sphere sphere4 = new Sphere(Point3D.ZERO, 4);
        assertEquals("number of intersections isn't equal", checkAllPixels(sphere4), 9);

        // TC05: small Sphere in back of camera (0 points)
        Sphere sphere5 = new Sphere(new Point3D(0, 0, -1), 0.5);
        assertEquals("number of intersections isn't equal", checkAllPixels(sphere5), 0);

    }

    /**
     * tests for integration of rays from Camera and rays {@link geometries.Plane#findIntersections(primitives.Ray)}
     */
    @Test
    public void planeIntegrationTest() {
        //TC01: Plane parallel to view plane (9 points)
        Plane plane1 = new Plane(new Point3D(0, 0, 2), new Vector(0, 0, 1));
        assertEquals("number of intersections isn't equal", checkAllPixels(plane1), 9);

        //TC02: Plane in a small angle to view plane (9 points)
        Plane plane2 = new Plane(new Point3D(0, 0, 1), new Vector(0, 0.2, 1));
        assertEquals("number of intersections isn't equal", checkAllPixels(plane2), 9);

        //TC03: Plane in a big angle to view plane (6 points)
        Plane plane3 = new Plane(new Point3D(0, 0, 1), new Vector(0, 2, 1));
        assertEquals("number of intersections isn't equal", checkAllPixels(plane3), 6);
    }


    /**
     * tests for integration of rays from Camera and rays {@link geometries.Triangle#findIntersections(primitives.Ray)}
     */
    @Test
    public void triangleIntegrationTest() {
        //TC01: small Triangle (1 point)
        Triangle triangle1 = new Triangle(new Point3D(-1, 1, 2), new Point3D(1, 1, 2), new Point3D(0, -1, 2));
        assertEquals("number of intersections isn't equal", checkAllPixels(triangle1), 1);

        //TC02: small Triangle (2 points)
        Triangle triangle2 = new Triangle(new Point3D(-1, 1, 2), new Point3D(1, 1, 2), new Point3D(0, -20, 2));
        assertEquals("number of intersections isn't equal", checkAllPixels(triangle2), 2);
    }
}



