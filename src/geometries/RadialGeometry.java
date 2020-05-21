package geometries;

import primitives.Color;
import primitives.Material;

/**
 * RadialGeometry class is a abstract class for difrint radial shapes in 3D Cartesian coordinate system
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public abstract class RadialGeometry extends Geometry {

    /**
     * represents the radius of the shape
     */
    private double radius;

    /**
     * RadialGeometry ctor sets the radius
     *
     * @param rad - number
     */
    public RadialGeometry(double rad) {
        this(Color.BLACK, new Material(0, 0, 0), rad);
    }

    /**
     * RadialGeometry ctor sets the radius and color
     *
     * @param c-   color
     * @param rad- number
     */
    public RadialGeometry(Color c, double rad) {
        this(c, new Material(0, 0, 0), rad);
    }

    /**
     * RadialGeometry ctor sets the radius, material and color
     *
     * @param color    - color
     * @param material - material
     * @param rad-     radius
     */
    public RadialGeometry(Color color, Material material, double rad) {
        super(color, material);
        radius = rad;
    }

    /**
     * getter for radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "RadialGeometry{" +
                "radius=" + radius +
                '}';
    }

}
