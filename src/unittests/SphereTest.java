package unittests;

import geometries.Sphere;
import primitives.*;

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

}