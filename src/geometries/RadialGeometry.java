package geometries;

public abstract class RadialGeometry implements Geometry {

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

    @Override
    public String toString() {
        return "RadialGeometry{" +
                "radius=" + radius +
                '}';
    }
}
