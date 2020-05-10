package elements;


import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * a class that represents Internal and External Light Sources
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public interface LightSource {

    /**
     * getter for intensity from @Light
     * @param p- a lighted point
     * @return the Color
     */
    public Color getIntensity(Point3D p);

    /**
     * getter for light direction at a specific point
     * @param p
     * @return a normalized vector at direction
     */
    public Vector getL(Point3D p);


}
