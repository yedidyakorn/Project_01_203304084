package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

import static primitives.Util.alignZero;


/**
 * Sphere class represents a sphere in 3D Cartesian coordinate
 * system
 * <p>
 * extends the RadialGeometry class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Sphere extends RadialGeometry {

    /**
     * represents the sphere center point
     */
    private Point3D center;

    /**
     * Sphere ctor that gets a point and a radius
     *
     * @param p-  point
     * @param rad -number
     */
    public Sphere(Point3D p, double rad) {
        super(rad);
        center = p;
    }

    /**
     * getter for the center point
     */
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
                '}' + super.toString();
    }

    @Override
    public Vector getNormal(Point3D point) {
        return point.subtract(this.center).normalize();
    }

    public List<Point3D> findIntersections(Ray ray) {
        Vector u;
        double rad = this.getRadius();
        try {
            u = center.subtract(ray.getPoint());//Ray starts at the center
        } catch (IllegalArgumentException e) {
            return List.of((ray.getPoint(rad)));
        }
        double tM = alignZero(u.dotProduct(ray.getDirection()));

        double d = alignZero(Math.sqrt(u.length() * u.length() - tM * tM));
        if (d > rad) {//Ray's line is outside the sphere
            return null;
        }
        double tH = alignZero(Math.sqrt(rad * rad - d * d));

        double t1 = alignZero(tM + tH);
        double t2 = alignZero(tM - tH);

        if (u.length() < rad) {//Ray starts inside the sphere
            return List.of(ray.getPoint(t1));
        }

        if (t1 <= 0 && t2 <= 0)//Ray's line is outside the sphere
            return null;

        List<Point3D> list = new ArrayList<Point3D>();
        if (t1 > 0)
            list.add(ray.getPoint(t1));
        if (t2 > 0)
            list.add(ray.getPoint(t2));
        return list;


    }
}
