package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

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
    public List<Point3D> findIntersections(Ray ray){
        return null;
    }
}
