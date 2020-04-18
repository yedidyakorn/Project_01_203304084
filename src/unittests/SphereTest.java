package unittests;

import geometries.Sphere;
import primitives.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for Sphere class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point3D)}  (Geometry.Sphere) }.
     */
    @org.junit.Test
    public void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single normal test..
        Sphere s1 = new Sphere(new Point3D(0, 0, 0), 3);
        Sphere s2 = new Sphere(new Point3D(5, 4, -3.5), 4.1);

        assertEquals("ERROR- the function does not return a true normal vector", new Vector(0, 0, 1).normalize(), s1.getNormal(new Point3D(0, 0, 6)));
        assertEquals("ERROR- the function does not return a true normal vector", new Vector(1, 0, 0).normalize(), s2.getNormal(new Point3D(9.1, 4, -3.5)));
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @org.junit.Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere( new Point3D(1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getX().get() > result.get(1).getX().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        Point3D p3 = new Point3D(1, 1, 0);
        result = sphere.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 2, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p3), result);

        // TC04: Ray starts after the sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(1.7, 2, 0), new Vector(1, 1, 0))));

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        Point3D p4 = new Point3D(2, 0, 0);
        result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, -1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p4), result);

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 1, 0))));

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        Point3D p5 = new Point3D(1, -1, 0);
        Point3D p6 = new Point3D(1, 1, 0);
        result = sphere.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, -1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getX().get() > result.get(1).getX().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p5, p6), result);

        // TC14: Ray starts at sphere and goes inside (1 points)
        Point3D p7 = new Point3D(1, -1, 0);
        result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, -1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p7), result);

        // TC15: Ray starts inside (1 points)
        Point3D p8 = new Point3D(1, -1, 0);
        result = sphere.findIntersections(new Ray(new Point3D(1, -0.5, 0), new Vector(0, -1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p8), result);

        // TC16: Ray starts at the center (1 points)
        Point3D p9 = new Point3D(1, 1, 0);
        result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p9), result);

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 1, 0))));

        // TC18: Ray starts after sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 1, 0))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 0, 0))));

        // TC20: Ray starts at the tangent point
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))));

        // TC21: Ray starts after the tangent point
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(1.5, 1, 0), new Vector(1, 0, 0))));

        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(1, 1.5, 0), new Vector(1, 0, 0))));

    }

}