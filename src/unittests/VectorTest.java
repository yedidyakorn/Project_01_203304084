package unittests;

import org.junit.*;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 * * @author Yedidya Korn & Eliezer Horowitz
 */
public class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);


    @Test
    public void testCtor(){
        try { // test zero vector
            new Vector(0, 0, 0);
            fail("constructing a Vector with 0 values");
        } catch (Exception e) { }

    }

    /**
     * Test method for {@link primitives.Vector#add (primitives.Vector)}.
     */
    @org.junit.Test
    public void add() {

    }

    /**
     * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
     */
    @org.junit.Test
    public void subtract() {
    }

    /**
     * Test method for {@link primitives.Vector#scale (primitives.Vector)}.
     */
    @org.junit.Test
    public void scale() {
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @org.junit.Test
    public void dotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: dotProduct() for orthogonal vectors test
        assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero",isZero(v1.dotProduct(v3)));

        // TC02: There is a simple dotProduct test
        assertTrue("ERROR: dotProduct() wrong value",isZero(v1.dotProduct(v2) + 28));
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @org.junit.Test
    public void crossProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: crossProduct() for parallel vectors test
        try { // test zero vector
            v1.crossProduct(v2);
            fail("ERROR: crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {  }

        // TC02: There is a simple crossProduct test
        Vector vr = v1.crossProduct(v3);
        assertTrue("ERROR: crossProduct() wrong result length",isZero(vr.length() - v1.length() * v3.length()));

        // TC03: crossProduct() orthogonal to its operands test
        assertTrue("ERROR: crossProduct() result is not orthogonal to its operands",isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)));
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared (primitives.Vector)}.
     */
    @org.junit.Test
    public void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single length test..
        assertTrue("ERROR: lengthSquared() wrong value",isZero(v1.lengthSquared()-14));
    }

    /**
     * Test method for {@link primitives.Vector#length (primitives.Vector)}.
     */
    @org.junit.Test
    public void length() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single length test..
        assertTrue("ERROR: length() wrong value",isZero(new Vector(0, 3, 4).length()-5));
    }

    /**
     * Test method for {@link primitives.Vector#normalize (primitives.Vector)}.
     */
    @org.junit.Test
    public void normalize() {
    }


}
