package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

public class FlashLight extends SpotLight {

    private double angle;

    public FlashLight(Color color, Point3D position, Vector direction, double angle, double c, double l, double q ){
        super(color,position,direction,c,l,q);
        this.angle=angle/180;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double projection = direction.dotProduct(getL(p));
        if (Util.isZero(projection)||projection<0||projection>angle)
            return Color.BLACK;
        Color pointIntensity = super.getIntensity(p);
        return (pointIntensity.scale(projection));
    }

    @Override
    public Vector getL(Point3D p) {
        return super.getL(p);
    }
}
