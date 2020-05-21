package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * a class that represents a SpotLight lighting the objects
 * the intensity is influenced by distance and direction
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class SpotLight extends PointLight {

    /**
     * direction- direction of the spot light
     */
    private Vector direction;

    /**
     * ctor for SpotLight
     *
     * @param color-    intensity light
     * @param position- position of light
     * @param c - number
     * @param l - number
     * @param q - number
     * @param direction - direction of spot light
     */
    public SpotLight(Color color, Point3D position,Vector direction, double c, double l, double q ) {
        super(color, position, c, l, q);
        this.direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        Vector l = getL(p);
        if (l == null)
            return Color.BLACK;
        double projection = direction.dotProduct(l);
        if (Util.isZero(projection) || projection < 0)
            return Color.BLACK;
        Color pointIntensity = super.getIntensity(p);
        return (pointIntensity.scale(projection));
    }
}
