package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * interface that will be used in all Geometry shapes
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */

public interface Intersectable {
    List<Point3D> findIntersections(Ray ray);
}
