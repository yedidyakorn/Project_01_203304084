package geometries;

import primitives.*;

/**
 * interface that will be used in all Geometry shapes
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public abstract class Geometry implements Intersectable{

    /**
     * function that gets a point on a shape surface and returns the normal from that point
     * assumes the point is on the shape surface (no input check)
     *
     * @param point on a shape
     * @return the normal vector from that point
     */
    protected  Color _emmission;
    public abstract Vector getNormal(Point3D point);

    /**
     * punction that gets a
     * @return
     */
    Color get_emmission(){
        return _emmission;
    }

    Geometry(Color c){
        _emmission = c;
    }

    Geometry(){
        _emmission = Color.BLACK;
    }
}
