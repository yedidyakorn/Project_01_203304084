package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;


/**
 * Sphere class represents a sphere in 3D Cartesian coordinate
 * system
 * <p>
 * extends the RadialGeometry class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Sphere extends RadialGeometry {

    /**
     * represents the sphere center point
     */
    private Point3D center;

    /**
     * Sphere ctor that gets a point and a radius
     *
     * @param p-  point
     * @param rad -number
     */
    public Sphere(Point3D p, double rad) {
        super(rad);
        center = p;
    }

    /**
     * getter for the center point
     */
    public Point3D getCenter() {
        return center;
    }

    @Override
    public double getRadius() {
        return super.getRadius();
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                '}' + super.toString();
    }

    @Override
    public Vector getNormal(Point3D point) {
        return point.subtract(this.center).normalize();
    }

   public List<Point3D> findIntersections(Ray ray){
        Vector u = center.subtract(ray.p);
        double tM = u.dotProduct(ray.v);

        double d = Math.sqrt(u.length()*u.length()- tM*tM);
        if(d>radius){
            return null;
        }
        double tH = Math.sqrt(ray.radius*ray.radius - d*d);

        double t1 = tM + tH;
        double t2 = tM - tH;

        List<Point3D> list = new List<Point3D>( ray.p.add(ray.v.scale(t1)), ray.p.add(ray.v.scale(t2)) );

        return list;
}
