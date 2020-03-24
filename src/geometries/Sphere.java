package geometries;

import primitives.*;
import static primitives.Util.*;

public class Sphere extends RadialGeometry {
    Point3D center;

    public Sphere(Point3D p, double rad){
        super(rad);
        center=p;
    }

    public Point3D getCenter() {
        return center;
    }

    @Override
    public double getRadius() {
        return super.getRadius();
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                '}'+super.toString();
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
