package geometries;

public abstract class RadialGeometry {

    double radius;

    public RadialGeometry(double rad){
        radius=rad;
    }

    public RadialGeometry(RadialGeometry other){
        radius=other.getRadius();
    }

    public double getRadius() {
        return radius;
    }


}
