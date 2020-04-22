package unittests;

import geometries.Sphere;
import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;


/**
 * Unit tests for Tube class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(Point3D)}  (Geometry.Sphere) }.
     */

    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single normal test..
        Tube t1 = new Tube(new Ray(new Point3D(5, 5, 5), new Vector(1, 0, 0)), 4);
        assertEquals("ERROR- the function does not return a true normal vector", new Vector(0, 0, 5).normalize(), t1.getNormal(new Point3D(1, 5, 9)));

        // TC02: There is a simple single normal test for a point that is on the orthogonal to the tubes rays point..
        Tube t2 = new Tube(new Ray(new Point3D(0, 0, 0), new Vector(0, 2, 0)), 3);
        assertEquals("ERROR- the function does not return a true normal vector", new Vector(3, 0, 0).normalize(), t2.getNormal(new Point3D(3, 0, 0)));

    }
}