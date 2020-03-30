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
        
        Vector temp = new Vector(point.getX().get() - this.ray.p.getX().get(), point.getY().get() - this.ray.p.getY().get(), point.getZ().get() - this.ray.p.getZ().get()); 
        double t = this.ray.v.dotProduct(temp);
        
        Vector temp2 = this.v.scala(t);
        Vector o = new Vector(this.ray.p.getX().get() + temp2.endPoint.getX().get(), this.ray.p.getY().get() + temp2.endPoint.getY().get(), this.ray.p.getZ().get() + temp2.endPoint.getZ().get()); 
        
        Vector n = nwe Vector(this.ray.p.getX().get() - o.endPoint.getX().get(), this.ray.p.getY().get() - o.endPoint.getY().get(), this.ray.p.getZ().get() - o.endPoint.getZ().get());
        
        return n.normalized();
    }
}
