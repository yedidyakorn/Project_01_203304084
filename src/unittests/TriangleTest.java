package unittests;

import geometries.Triangle;
import primitives.*;

import static org.junit.Assert.assertEquals;

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

}