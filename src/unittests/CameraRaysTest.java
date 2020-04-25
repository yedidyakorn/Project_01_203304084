package unittests;
import static org.junit.Assert.*;

import geometries.Triangle;
import org.junit.Test;

import elements.Camera;
import primitives.*;

/**
 * tests for integration of rays from Camera and previous calculations
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class CameraRaysTest {

    /**
     * tests for integration of rays from Camera and rays from geometries.*****#findIntersections(primitives.Ray)
     * tests for {@link geometries.Sphere#findIntersections(primitives.Ray)}
     * tests for {@link geometries.Plane#findIntersections(primitives.Ray)}
     * tests for {@link geometries.Triangle#findIntersections(primitives.Ray)}
     */
    @Test
    public void integrationTest(){
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));

        // TC01: Sphere
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, -2, 10)),
                camera.constructRayThroughPixel(3, 3, 0, 0, 10, 3, 3));

        //TC02: Plane

        //TC03: Triangle
        Triangle triangle = new Triangle(new Point3D(1, 1, 0), new Point3D(2, 2, 0), new Point3D(3, 1, 0));


    }


}
