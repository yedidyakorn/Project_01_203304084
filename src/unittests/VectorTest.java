package unittests;

import org.junit.Assert;
import org.junit.Test;
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
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @org.junit.Test
    public void crossProduct() {
    }

    /**
     * Test method for {@link primitives.Vector#length (primitives.Vector)}.
     */
    @org.junit.Test
    public void length() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        assert("ERROR: length() wrong value",new Vector(0, 3, 4).length(),5);
        if (!isZero(new Vector(0, 3, 4).length() - 5))
            fail("ERROR: length() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#normalize (primitives.Vector)}.
     */
    @org.junit.Test
    public void normalize() {
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared (primitives.Vector)}.
     */
    @org.junit.Test
    public void lengthSquared() {
        Vector v1 = new Vector(1, 2, 3);
        assert("ERROR: lengthSquared() wrong value",v1.lengthSquared(),14);
        assertEquals("ERROR: lengthSquared() wrong value",!isZero(v1.lengthSquared()-14));
        ass
    }

}
