package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;


/**
 * a class that represents the DirectionalLight on the objects
 * No attenuation with distance
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class DirectionalLight extends Light implements LightSource {

    /**
     * direction- a vector that represents the direction of the light
     */
    private Vector direction;

    /**
     * ctor for DirectionalLight
     *
     * @param color- intensity light
     * @param v      - direction of spot light
     */
    public DirectionalLight(Color color, Vector v) {
        super(color);
        direction = v.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return direction;
    }

    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}
