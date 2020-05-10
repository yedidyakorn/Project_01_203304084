package geometries;

import primitives.*;

/**
 * interface that will be used in all Geometry shapes
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public abstract class Geometry implements Intersectable {

    /**
     * Emmission - Geometry Color
     * Material - Geometry material
     */
    protected Color emmission;
    protected Material material;

    /**
     * ctor taht sets a color
     *
     * @param c- color
     */
    public Geometry(Color c) {
        emmission = c;
        material= new Material(0,0,0);
    }

    /**
     * default ctor. sets color as black
     */
    public Geometry() {
        emmission = Color.BLACK;
        material= new Material(0,0,0);
    }

    /**
     * ctor taht sets a color and material
     *
     * @param color
     * @param material
     */
    public Geometry(Color color, Material material) {
        this.emmission = color;
        this.material = material;
    }

    /**
     * function that gets a point on a shape surface and returns the normal from that point
     * assumes the point is on the shape surface (no input check)
     *
     * @param point on a shape
     * @return the normal vector from that point
     */
    public abstract Vector getNormal(Point3D point);

    /**
     * getter for the shapes color
     *
     * @return emmission color
     */
    public Color getEmmission() {
        return emmission;
    }

    /**
     * getter for the shapes Material
     *
     * @return
     */
    public Material getMaterial() {
        return material;
    }
}