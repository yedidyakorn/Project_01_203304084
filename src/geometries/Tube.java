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

        Vector temp = new Vector(point.getX().get() - this.ray.getPoint().getX().get(), point.getY().get() - this.ray.getPoint().getY().get(), point.getZ().get() - this.ray.getPoint().getZ().get());
        double t = this.ray.getDirection().dotProduct(temp);

        Vector temp2 = this.ray.getDirection().scale(t);
        Vector o = new Vector(this.ray.getPoint().getX().get() + temp2.getEndPoint().getX().get(), this.ray.getPoint().getY().get() + temp2.getEndPoint().getY().get(), this.ray.getPoint().getZ().get() + temp2.getEndPoint().getZ().get());

        Vector n = new Vector(this.ray.getPoint().getX().get() - o.getEndPoint().getX().get(), this.ray.getPoint().getY().get() - o.getEndPoint().getY().get(), this.ray.getPoint().getZ().get() - o.getEndPoint().getZ().get());

        return n.normalized();
    }
}
