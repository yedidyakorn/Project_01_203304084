package geometries;

import primitives.*;

/**
 * Cylinder class represents a cylinder in 3D Cartesian coordinate
 * system
 * <p>
 * extends the Tube class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Cylinder extends Tube {

    /**
     * represents the cylinder height
     */
    double height;

    /**
     * Cylinder ctor that gets two numbers and a ray
     *
     * @param r-   ray
     * @param h-   height
     * @param rad- radius
     */
    public Cylinder(double h, Ray r, double rad) {
        super(r,rad);
        height = h;
    }

    /**
     * getter for the height
     */
    public double getHeight() {
        return height;
    }

    @Override
    public double getRadius() {
        return super.getRadius();
    }


    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "hight=" + height +
                '}' + super.toString();
    }
}
