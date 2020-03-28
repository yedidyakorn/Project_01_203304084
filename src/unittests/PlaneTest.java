package unittests;

import geometries.Plane;
import primitives.*;

import static org.junit.Assert.assertTrue;
import static primitives.Util.isZero;

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
        assertTrue("ERROR- the function does not return a true normal vector", isZero(p.getNormal().length() - new Vector(-5, 57, 37).length()));
    }
}
