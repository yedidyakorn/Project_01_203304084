package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface that will be used in all Geometry shapes
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public abstract class Geometry implements Intersectable {

    /*
     * Emission - Geometry Color
     * Material - Geometry material
     */
    protected Color emission;
    protected Material material;

    /**
     * default ctor. sets color as black
     */
    public Geometry() {
        this(Color.BLACK, new Material(0, 0, 0));
    }

    /**
     * ctor taht sets a color
     *
     * @param color- color
     */
    public Geometry(Color color) {
        this(color, new Material(0, 0, 0));
    }

    /**
     * ctor that sets a color and material
     *
     * @param color    - shapes color
     * @param material - shapes material
     */
    public Geometry(Color color, Material material) {
        this.emission = color;
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
    public Color getEmission() {
        return emission;
    }

    /**
     * getter for the shapes Material
     *
     */
    public Material getMaterial() {
        return material;
    }
}
