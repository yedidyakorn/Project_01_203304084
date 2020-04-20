package unittests;

import geometries.Triangle;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit tests for Triangle class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class TriangleTest {


    /**
     * Test method for {@link geometries.Triangle#getNormal(Point3D)}  (Geometry.Triangle) }.
     */
    @org.junit.Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single normal test..
        Triangle t = new Triangle(new Point3D(1, 1, 1), new Point3D(-5, 3, 2), new Point3D(4, -2, -1));
        assertEquals("ERROR- the function does not return a true normal vector", new Vector(-1, -9, 12).normalize(), t.getNormal(new Point3D(1, 1, 1)));
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @org.junit.Test
    public void testFindIntersections() {
        Triangle triangle = new Triangle(new Point3D(1, 1, 0), new Point3D(2, 2, 0), new Point3D(3, 1, 0));
        List<Point3D> result;

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the triangle (1 point)
        Point3D p1 = new Point3D(2, 1.5, 0);
        result = triangle.findIntersections(new Ray(new Point3D(2, 1.5, 3), new Vector(0, 0, -1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses plane", List.of(p1), result);

        // TC02: Ray outside against edge of triangle (0 point)
        assertNull("Ray's line out of triangle", triangle.findIntersections(new Ray(new Point3D(2, 1, 3), new Vector(0, 0, -1))));

        // TC03: Ray outside against vertex of triangle (0 point)
        assertNull("Ray's line out of triangle", triangle.findIntersections(new Ray(new Point3D(0.5, 0.75, 3), new Vector(0, 0, -1))));

        // =============== Boundary Values Tests ==================
        // TC011: Ray intersects the triangle on edge(0 point)
        assertNull("Ray's line out of triangle", triangle.findIntersections(new Ray(new Point3D(1.5, 1.5, 3), new Vector(0, 0, -1))));

        // TC012: Ray intersects the triangle on vertex(0 point)
        assertNull("Ray's line out of triangle", triangle.findIntersections(new Ray(new Point3D(2, 2, 3), new Vector(0, 0, -1))));

        // TC013: Ray intersects the triangle on  edge's continuation(0 point)
        assertNull("Ray's line out of triangle", triangle.findIntersections(new Ray(new Point3D(0.5, 0.5, 3), new Vector(0, 0, -1))));


    }

}