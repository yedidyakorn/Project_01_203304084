package geometries;

import primitives.*;

import java.util.List;

/**
 * Plane class represents a plane in 3D Cartesian coordinate system
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Plane implements Geometry {

    /**
     * point represents a point on the plane
     * normal represents the normal vector to the plane
     */
    private Point3D point;
    private Vector normal;

    /**
     * plane ctor that gets three points
     * can throw @IllegalArgumentException if the points are in one line
     *
     * @param a-point on plane
     * @param b-point on plane
     * @param c-point on plane
     */
    public Plane(Point3D a, Point3D b, Point3D c) {
        point = a;
        Vector v1 = b.subtract(a);
        Vector v2 = c.subtract(a);
        normal = v1.crossProduct(v2).normalize();
    }


    /**
     * plane ctor that gets a point and normal vector
     *
     * @param p    point
     * @param vec- some vector
     */
    public Plane(Point3D p, Vector vec) {
        point = new Point3D(p);
        normal = new Vector(vec).normalize();
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

    @Override
    public Vector getNormal(Point3D point) {
        return this.normal;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray){
        return null;
    }
}
