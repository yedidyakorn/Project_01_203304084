package geometries;

import primitives.*;

/**
 * Plane class represents a plane in 3D Cartesian coordinate system
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Plane {

    /**
     * point represents a point on the plane
     * normal represents the normal vector to the plane
     */
    Point3D point;
    Vector normal;

    /**
     * plane ctor that gets three points
     */
    public Plane(Point3D a, Point3D b, Point3D c) {
        point = a;
        Vector v1 = new Vector(b.getX().get()-a.getX().get(), b.getY().get()-a.getY().get(), b.getZ().get()-a.getZ().get());
        Vector v2 = new Vector(c.getX().get()-a.getX().get(), c.getY().get()-a.getY().get(), c.getZ().get()-a.getZ().get());
        normal= v1.crossProduct(v2).normalize();
    }

    /**
     * plane ctor that gets a point and normal vector
     */
    public Plane(Point3D p, Vector vec) {
        point = p;
        normal = vec;
    }

    /**
     * getter for a point on the plane
     */
    public Point3D getPoint() {
        return point;
    }

    /**
     * getter for the normal vector of the plane
     */
    public Vector getNormal() {
        return normal;
    }


    @Override
    public String toString() {
        return "Plane{" +
                "point=" + point +
                ", normal=" + normal +
                '}';
    }
}
