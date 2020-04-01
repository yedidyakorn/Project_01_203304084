package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * RadialGeometry class is a abstract class for difrint radial shapes in 3D Cartesian coordinate system
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public abstract class RadialGeometry implements Geometry {

    /**
     * represents the radius of the shape
     */
    private double radius;

    /**
     * RadialGeometry ctor sets the radius
     *
     * @param rad - number
     */
    public RadialGeometry(double rad) {
        radius = rad;
    }


    /**
     * getter for radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "RadialGeometry{" +
                "radius=" + radius +
                '}';
    }

}
