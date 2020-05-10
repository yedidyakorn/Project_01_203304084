package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane class represents a plane in 3D Cartesian coordinate system
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Plane extends Geometry {

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
     * plane ctor that gets a point and normal vector and color
     *
     * @param color - color
     * @param p     point
     * @param vec-  some vector
     */
    public Plane(Color color, Point3D p, Vector vec) {
        this(p, vec);
        this.emmission = color;
    }

    /**
     * plane ctor that gets a point and normal vector and color and material
     *
     * @param color    - color
     * @param material - material
     * @param p        - point
     * @param vec-     some vector
     */
    public Plane(Color color, Material material, Point3D p, Vector vec) {
        this(color, p, vec);
        this.material = material;
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
    public List<GeoPoint> findIntersections(Ray ray) {
        Vector n;
        try {//Ray begins at the plane's point
            n = point.subtract(ray.getPoint());
        } catch (IllegalArgumentException e) {
            return null;
        }

        double nv = normal.dotProduct(ray.getDirection());
        if (isZero(nv))//Ray is parallel to the plane
            return null;

        double t = alignZero(normal.dotProduct(n) / nv);

        if (t <= 0)
            return null;
        return List.of(new GeoPoint(this, ray.getPoint(t)));

    }
}
