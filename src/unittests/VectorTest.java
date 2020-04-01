package unittests;

import primitives.*;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    Vector v = new Vector(1, 2, 3);
    Vector vCopy = new Vector(v);
    Vector vCopyNormalize = vCopy.normalize();
    Point3D p1 = new Point3D(1, 2, 3);


    /**
     * Test method for {@link primitives.Vector#Vector(double, double, double)}  (primitives.Vector)}.
     */
    @org.junit.Test
    public void testCtor() {
        try { // test zero vector
            new Vector(0, 0, 0);
            fail("constructing a Vector with 0 values");
        } catch (Exception e) {
        }

    }

    /**
     * Test method for {@link primitives.Vector#add(Vector)}  (primitives.Vector)}.
     */
    @org.junit.Test
    public void add() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single add test..
        assertEquals("ERROR: Point + Vector does not work correctly", Point3D.ZERO, p1.add(new Vector(-1, -2, -3)));
    }

    /**
     * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
     */
    @org.junit.Test
    public void subtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single subtract test..
        assertEquals("ERROR: Point - Point does not work correctly", new Vector(1, 1, 1), new Point3D(2, 3, 4).subtract(p1));
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}  (primitives.Vector)}.
     */
    @org.junit.Test
    public void scale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single scale test..
        assertTrue("ERROR: vector * number does not work correctly", isZero(v1.scale(2.0).length() - new Vector(2, 4, 6).length()));
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)} (primitives.Vector)}.
     */
    @org.junit.Test
    public void dotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: dotProduct() for orthogonal vectors test
        assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));

        // TC02: There is a simple dotProduct test
        assertTrue("ERROR: dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)} (primitives.Vector)}.
     */
    @org.junit.Test
    public void crossProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: crossProduct() for parallel vectors test
        try { // test zero vector
            v1.crossProduct(v2);
            fail("ERROR: crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        }

        // TC02: There is a simple crossProduct test
        Vector vr = v1.crossProduct(v3);
        assertTrue("ERROR: crossProduct() wrong result length", isZero(vr.length() - v1.length() * v3.length()));

        // =============== Boundary Values Tests ==================
        // TC03: crossProduct() orthogonal to its operands test
        assertTrue("ERROR: crossProduct() result is not orthogonal to its operands", isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)));
    }

    /**
     * Test method for {@link Vector#lengthSquared()}  (primitives.Vector)}.
     */
    @org.junit.Test
    public void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single length test..
        assertTrue("ERROR: lengthSquared() wrong value", isZero(v1.lengthSquared() - 14));
    }

    /**
     * Test method for {@link primitives.Vector#length} (primitives.Vector)}.
     */
    @org.junit.Test
    public void length() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single length test..
        assertTrue("ERROR: length() wrong value", isZero(new Vector(0, 3, 4).length() - 5));
    }

    /**
     * Test method for {@link Vector#normalize()}  (primitives.Vector)}.
     */
    @org.junit.Test
    public void normalize() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple to check if the function creates a new vector or normalizes the same one
        assertEquals("ERROR: normalize() function creates a new vector", vCopy, vCopyNormalize);

        // TC02: There is a simple test for normalizing vector..
        assertTrue("ERROR: normalize() result is not a unit vector", isZero(vCopyNormalize.length() - 1));


    }

    /**
     * Test method for {@link Vector#normalized()}  (primitives.Vector)}.
     */
    @org.junit.Test
    public void normalized() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple to check if the function creates a new vector or normalizes the same one
        Vector u = v.normalized();
        assertNotSame("ERROR: normalized() function does not create a new vector", u, v);
    }


}
