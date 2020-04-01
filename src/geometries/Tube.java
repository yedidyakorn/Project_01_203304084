package geometries;

import primitives.*;

import static primitives.Util.isZero;

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
    private Ray ray;

    /**
     * tube ctor that gets a number and a ray
     *
     * @param r-   ray
     * @param rad- number
     */
    public Tube(Ray r, double rad) {
        super(rad);
        ray = new Ray(r);
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

        // t is the angle between the tubes ray direction and the vector between the point and the tubes rays base point
        Vector temp = new Vector(point.subtract(this.ray.getPoint()));
        double t = this.ray.getDirection().dotProduct(temp);
        //if t=0 the vectors are orthogonal
        if (isZero(t))
            return temp.normalize();
        Point3D o = (this.getRay().getPoint().add(this.ray.getDirection().scale(t)));
        return new Vector(point.subtract(o)).normalize();
    }
}
