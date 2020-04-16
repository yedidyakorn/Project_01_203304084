package geometries;

import primitives.*;

/**
 * interface that will be used in all Geometry shapes
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public interface Geometry extends Intersectable {

    /**
     * function that gets a point on a shape surface and returns the normal from that point
     * assumes the point is on the shape surface (no input check)
     *
     * @param point on a shape
     * @return the normal vector from that point
     */
    Vector getNormal(Point3D point);
}
