/**
 *
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

import java.util.List;

/**
 * Testing Polygons
 * @author Dan
 *
 */
public class PolygonTests {

    /**
     * Test method for
     * {@link geometries.Polygon#Polygon (primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {
        }

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {
        }

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {
        }

        // =============== Boundary Values Tests ==================

        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {
        }

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {
        }

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {
        }

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }

    /**
     * Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Polygon polygon = new Polygon(new Point3D(1, 1, 0), new Point3D(2, 2, 0), new Point3D(3, 1, 0), new Point3D(2, 0, 0));
        List<Point3D> result;

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the Polygon (1 point)
        Point3D p1 = new Point3D(2, 1.5, 0);
        result = polygon.findIntersections(new Ray(new Point3D(2, 1.5, 3), new Vector(0, 0, -1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses plane", List.of(p1), result);

        // TC02: Ray outside against edge of Polygon (0 point)
        assertNull("Ray's line out of Polygon", polygon.findIntersections(new Ray(new Point3D(1.5, 0.5, 3), new Vector(0, 0, -1))));

        // TC03: Ray outside against vertex of Polygon (0 point)
        assertNull("Ray's line out of Polygon", polygon.findIntersections(new Ray(new Point3D(0.5, 0.75, 3), new Vector(0, 0, -1))));

        // =============== Boundary Values Tests ==================
        // TC011: Ray intersects the Polygon on edge(0 point)
        assertNull("Ray's line out of Polygon", polygon.findIntersections(new Ray(new Point3D(1.5, 1.5, 3), new Vector(0, 0, -1))));

        // TC012: Ray intersects the Polygon on vertex(0 point)
        assertNull("Ray's line out of Polygon", polygon.findIntersections(new Ray(new Point3D(2, 2, 3), new Vector(0, 0, -1))));

        // TC013: Ray intersects the Polygon on  edge's continuation(0 point)
        assertNull("Ray's line out of Polygon", polygon.findIntersections(new Ray(new Point3D(0.5, 0.5, 3), new Vector(0, 0, -1))));


    }

}
