package unittests;

import geometries.*;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit tests for Geometries class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class GeometriesTests {

    @org.junit.Test
    public void testFindIntersections() {
        Geometries geometries = new Geometries();
        geometries.add(new Triangle(new Point3D(3, 3, 8), new Point3D(4, 4, 8), new Point3D(5, 3, 8)));
        geometries.add(new Sphere(new Point3D(3, 3, 4), 2));
        geometries.add(new Plane(new Point3D(3, 3, 1), new Vector(new Point3D(0, 0, 1))));
        List<Point3D> result;


        // ============ Equivalence Partitions Tests ==============

        // TC01: some shapes but nat all have an intersection (3 points)
        result = geometries.findIntersections(new Ray(new Point3D(3, 3, 9), new Vector(0, 0, -1)));
        assertEquals("Wrong number of points", 3, result.size());

        // TC02: all shapes have an intersection (4 points)
        result = geometries.findIntersections(new Ray(new Point3D(4, 3.5, 9), new Vector(0, 0, -1)));
        assertEquals("Wrong number of points", 4, result.size());

        // =============== Boundary Values Tests ==================

        // TC11: Geometries list is empty (0 points)
        Geometries temp = new Geometries();
        assertNull("Wrong number of points",temp.findIntersections(new Ray(new Point3D(4, 3.5, 9), new Vector(0, 0, -1))));

        // TC12: no shapes have an intersection (0 points)
        assertNull("Wrong number of points",geometries.findIntersections(new Ray(new Point3D(4, 3.5, 9), new Vector(1, 1, 1))));

        // TC13: only one shape has an intersection (1 point)
        result = geometries.findIntersections(new Ray(new Point3D(8, 8, 9), new Vector(0, 0, -1)));
        assertEquals("Wrong number of points", 1, result.size());


    }

}
