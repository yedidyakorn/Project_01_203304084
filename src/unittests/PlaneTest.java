package unittests;

import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for Plane class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class PlaneTest {


    /**
     * Test method for {@link geometries.Plane#getNormal()}  (Geometry.Plane) }.
     */
    @org.junit.Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single normal test..
        Plane p = new Plane(new Point3D(2, 5, -8), new Point3D(-2, 4, -7), new Point3D(7, -3, 5));
        assertEquals("ERROR- the function does not return a true normal vector", new Vector(-5, 57, 37).normalize(), p.getNormal());
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @org.junit.Test
    public void testFindIntersections() {
        Plane plane=new Plane(new Point3D(0,1,0),new Vector(new Point3D(0,1,0)));
        List<Point3D> result=new ArrayList<Point3D>();

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the plane (1 point)
        Point3D p1 = new Point3D(2, 1, 0);
        result=plane.findIntersections(new Ray(new Point3D(1,0,0),new Vector(1,1,0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses plane", List.of(p1), result);

        // TC02: Ray does not intersect the plane (0 points)
        assertEquals("Ray's line out of plane", null,
                plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, -1, 0))));

        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane
        // TC11: the ray is included in the plane (0 points)
        assertEquals("Ray is included in the plane", null,
                plane.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 1))));

        // TC12: the ray is not included in the plane (0 points)
        assertEquals("Ray is parallel to the plane", null,
                plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 1))));

        // **** Group: Ray is orthogonal to the plane
        // TC13: the ray is before the plane (1 point)
        Point3D p2 = new Point3D(1, 1, 0);
        result=plane.findIntersections(new Ray(new Point3D(1,0,0),new Vector(0,1,0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses plane", List.of(p2), result);

        // TC14: the ray starts is in the plane (0 points)
        assertEquals("Ray is orthogonal to the plane", null,
                plane.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 1, 0))));

        // TC15: the ray is after the plane (0 points)
        assertEquals("Ray is orthogonal to the plane", null,
                plane.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 1, 0))));

        // **** Group: Special cases
        // TC16: Ray is neither orthogonal nor parallel to plane but begins at the plane (0 points)
        assertEquals("Ray begins at the plane", null,
                plane.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 1, 0))));

        //TC17: Ray is neither orthogonal nor parallel to the plane and begins in
        //the same point which appears as reference point in the plane (0 points)
        assertEquals("Ray begins at the plane's point", null,
                plane.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(0, 1, 0))));


    }
}
