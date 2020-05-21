package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * a class that represents a Flash Light lighting the objects
 * the intensity is influenced by distance and direction
 * extends SpotLight class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class FlashLight extends PointLight {

    /**
     * angle- the angle in degrees that the flash light lights to
     */
    private double angle;
    private Vector direction;

    /**
     * ctor for FlashLight
     *
     * @param color-    intensity light
     * @param position- position of light
     * @param direction - direction of spot light
     * @param angle     - number of degrees
     * @param c         - number
     * @param l         - number
     * @param q         - number
     */
    public FlashLight(Color color, Point3D position, Vector direction, double angle, double c, double l, double q) {
        super(color, position, c, l, q);
        this.direction = direction.normalized();
        this.angle = angle / 180;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double projection = direction.dotProduct(getL(p));
        if ((projection < (1 - angle)))
            return Color.BLACK;
        Color pointIntensity = super.getIntensity(p);
        return (pointIntensity.scale(Math.abs(projection)));
    }

    @Override
    public Vector getL(Point3D p) {
        return super.getL(p);
    }
}
