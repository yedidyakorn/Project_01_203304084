package unittests;

import geometries.Plane;
import primitives.Point3D;
import primitives.Vector;

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
}
