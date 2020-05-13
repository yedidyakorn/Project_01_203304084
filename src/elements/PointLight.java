package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * a class that represents a PointLight lighting the objects
 * the intensity is influenced by distance
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class PointLight extends Light implements LightSource {

    /**
     * position- the position of the light source
     *kC,kL,kQ - Factors for attenuation with distance
     */
    protected Point3D position;
    protected double kC, kL, kQ;

    /**
     * ctor for PointLight
     *
     * @param color-    intensity light
     * @param position- position of light
     * @param c - number
     * @param l - number
     * @param q - number
     */
    public PointLight(Color color, Point3D position, double c, double l, double q) {
        super(color);
        this.position = position;
        kC = c;
        kL = l;
        kQ = q;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double dsquared = p.distanceSquared(position);
        double d = p.distance(position);
        return (intensity.reduce(kC + kL * d + kQ * dsquared));
    }

    @Override
    public Vector getL(Point3D p) {
        if(p.equals(position))
            return null;
        return p.subtract(position).normalize();
    }
}
