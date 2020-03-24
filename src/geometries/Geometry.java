package geometries;

import primitives.*;

/**
 * interface that will be used in all Geometry shapes
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
interface Geometry {

    /**
     * function that gets a point on a shape and returns the normal from that point
     *
     * @param point on a shape
     * @return the normal vector from that point
     */
    Vector getNormal(Point3D point);
}
