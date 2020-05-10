package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Plane Triangle represents a triangle in 3D Cartesian coordinate system
 * <p>
 * extends the Polygon class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Triangle extends Polygon {

    /**
     * Triangle ctor that gets three points
     */
    public Triangle(Point3D a, Point3D b, Point3D c) {
        super(a, b, c);
    }

    /**
     * Triangle ctor that gets three points and color
     *
     * @param color - color
     */
    public Triangle(Color color, Point3D a, Point3D b, Point3D c) {
        super(color, a, b, c);
    }

    /**
     * Triangle ctor that gets three points and color
     *
     * @param color    - color
     * @param material - materials
     */
    public Triangle(Color color, Material material, Point3D a, Point3D b, Point3D c) {
        super(color, material, a, b, c);
    }


    /**
     * getter for point 1
     */
    public Point3D getPointA() {
        return this._vertices.get(0);
    }

    /**
     * getter for point 2
     */
    public Point3D getPointB() {
        return this._vertices.get(1);
    }

    /**
     * getter for point 3
     */
    public Point3D getPointC() {
        return this._vertices.get(2);
    }

    @Override
    public String toString() {
        return this._vertices.toString();
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = _plane.findIntersections(ray);
        if (intersections == null)
            return null;

        Point3D p0 = ray.getPoint();
        Vector v = ray.getDirection();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double n1 = alignZero(v1.crossProduct(v2).normalize().dotProduct(v));
        double n2 = alignZero(v2.crossProduct(v3).normalize().dotProduct(v));
        double n3 = alignZero(v3.crossProduct(v1).normalize().dotProduct(v));
        if (n1 == 0 || n2 == 0 || n3 == 0)
            return null;
        if ((n1 > 0 && n2 > 0 && n3 > 0) || ((n1 < 0 && n2 < 0 && n3 < 0))) { // the ray meets the triangle
            intersections.get(0).geometry = this;
            return intersections;
        }

        return null;
    }
}
