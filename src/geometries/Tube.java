package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {

    Ray ray;

    public Tube(Ray r,double rad){
        super(rad);
        ray=r;
    }

    @Override
    public double getRadius() {
        return super.getRadius();
    }

    public Ray getRay() {
        return ray;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "ray=" + ray +
                '}'+super.toString();
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
