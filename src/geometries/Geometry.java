package geometries;

import primitives.*;

/**
 * interface that will be used in all Geometry shapes
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public abstract class Geometry implements Intersectable {

    /**
     * function that gets a point on a shape surface and returns the normal from that point
     * assumes the point is on the shape surface (no input check)
     *
     * @param point on a shape
     * @return the normal vector from that point
     */
    protected Color emmission;

    /**
     * ctor taht sets a color
     * @param c- color
     */
    public Geometry(Color c) {
        emmission = c;
    }

    /**
     * default ctor. sets color as black
     */
    public Geometry() {
        emmission = Color.BLACK;
    }

    public abstract Vector getNormal(Point3D point);

    /**
     * getter for the shapes color
     *
     * @return emmission color
     */
    public Color getEmmission() {
        return emmission;
    }


}