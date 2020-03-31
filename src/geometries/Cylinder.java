package geometries;

import primitives.*;

/**
 * Cylinder class represents a cylinder in 3D Cartesian coordinate
 * system
 * <p>
 * extends the Tube class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Cylinder extends Tube {

    /**
     * represents the cylinder height
     */
    double height;

    /**
     * Cylinder ctor that gets two numbers and a ray
     *
     * @param r-   ray
     * @param h-   height
     * @param rad- radius
     */
    public Cylinder(Ray r, double rad, double h) {
        super(r, rad);
        height = h;
    }

    /**
     * getter for the height
     */
    public double getHeight() {
        return height;
    }

    @Override
    public double getRadius() {
        return super.getRadius();
    }


    @Override
    public Vector getNormal(Point3D point) {
        if(point.equals(this.ray.getPoint()))
            return this.ray.getDirection().scale(-1).normalize();
        // t is the angle between the tubes ray direction and the vector between the point and the tubes rays base point
        Vector temp = new Vector(point.subtract(this.ray.getPoint()));
        double t = this.ray.getDirection().dotProduct(temp);
        //if t=0 the vectors are orthogonal
        if (t == 0){
            if(radius==(temp.length()))
                return temp.normalize();
            return this.ray.getDirection().scale(-1).normalize();
        }
        Point3D o = (this.getRay().getPoint().add(this.ray.getDirection().scale(t)));
        if(point.equals(o))
            return this.ray.getDirection().normalize();
        Vector temp2 = new Vector(point.subtract(o));
        if (temp2.length() == this.radius)
            return temp2.normalize();
        return this.ray.getDirection().normalize();
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "hight=" + height +
                '}' + super.toString();
    }
}
