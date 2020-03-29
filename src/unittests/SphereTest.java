package unittests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

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

        assertTrue("ERROR- the function does not return a true normal vector", isZero(s1.getNormal(new Point3D(0, 0, 6)).length() - new Vector(0, 0, 6).length()));
        assertTrue("ERROR- the function does not return a true normal vector", isZero(s2.getNormal(new Point3D(9.1, 4, -3.5)).length() - new Vector(9.1 - 5, 4 - 4, -3.5 + 3.5).length()));


    }

}