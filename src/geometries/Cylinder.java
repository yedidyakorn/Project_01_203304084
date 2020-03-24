package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Cylinder extends RadialGeometry {

    double hight;

    public Cylinder (double h,double rad){
        super(rad);
        hight=h;
    }

    @Override
    public double getRadius() {
        return super.getRadius();
    }

    public double getHight() {
        return hight;
    }


    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "hight=" + hight +
                '}'+super.toString();
    }
}
