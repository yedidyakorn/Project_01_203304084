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

    @org.junit.Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single normal test..
        Tube t=new Tube(new Ray(new Point3D(5,5,5),new Vector(3,0,0)),4);
        assertEquals("ERROR- the function does not return a true normal vector", new Vector(1, 0, 0).normalize(), t.getNormal(new Point3D(2, 5, 5)));


    }
}