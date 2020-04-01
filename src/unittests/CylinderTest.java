package unittests;

import geometries.Cylinder;
import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

public class CylinderTest {

    @Test
    public void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single normal test..
        Cylinder c1 = new Cylinder(new Ray(new Point3D(5, 5, 5), new Vector(1, 0, 0)), 4, 10);
        assertEquals("ERROR- the function does not return a true normal vector", new Vector(0, 0, 5).normalize(), c1.getNormal(new Point3D(1, 5, 9)));

        // TC02: There is a simple single normal test for a point that is on the orthogonal to the tubes rays point..
        Cylinder c2 = new Cylinder(new Ray(new Point3D(0, 0, 0), new Vector(0, 2, 0)), 3, 10);
        assertEquals("ERROR- the function does not return a true normal vector", new Vector(3, 0, 0).normalize(), c2.getNormal(new Point3D(3, 0, 0)));

        // TC03: Vertex on a side of a Cylinder
        Cylinder c3 = new Cylinder(new Ray(new Point3D(2, 2, 2), new Vector(0, 2, 0)), 3, 3);
        assertEquals("ERROR- the function does not return a true normal vector", new Vector(0, 2, 0).normalize(), c3.getNormal(new Point3D(2, 5, 2)));

        // TC04: Vertex on a side of a Cylinder
        Cylinder c4 = new Cylinder(new Ray(new Point3D(2, 2, 2), new Vector(0, 2, 0)), 3, 3);
        assertEquals("ERROR- the function does not return a true normal vector", new Vector(0, -2, 0).normalize(), c4.getNormal(new Point3D(3, 2, 4)));


        // =============== Boundary Values Tests ==================

        // TC05: Vertex on a side of a quadrangular
        Cylinder c5 = new Cylinder(new Ray(new Point3D(1, 1, 1), new Vector(0, 0, 5)), 1, 2);
        assertEquals("ERROR- the function does not return a true normal vector", new Vector(4, 0, 0).normalize(), c5.getNormal(new Point3D(2, 1, 3)));


    }
}