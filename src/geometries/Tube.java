package geometries;

import primitives.*;

/**
 * Tube class represents a tube in 3D Cartesian coordinate
 * system
 * <p>
 * extends the RadialGeometry class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Tube extends RadialGeometry {

    /**
     * represents the Tube height
     */
    Ray ray;

    /**
     * tube ctor that gets a number and a ray
     *
     * @param r-   ray
     * @param rad- number
     */
    public Tube(Ray r, double rad) {
        super(rad);
        ray = r;
    }

    /**
     * getter for the ray
     */
    public Ray getRay() {
        return ray;
    }

    @Override
    public double getRadius() {
        return super.getRadius();
    }

    @Override
    public String toString() {
        return "Tube{" +
                "ray=" + ray +
                '}' + super.toString();
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
