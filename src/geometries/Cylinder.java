package geometries;

import primitives.*;

import java.util.List;

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
    private double height;

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
        //Vertex on the base point of Cylinder
        if (point.equals(this.getRay().getPoint()))
            return this.getRay().getDirection().scale(-1);
        // t is the angle between the tubes ray direction and the vector between the point and the tubes rays base point
        Vector temp = new Vector(point.subtract(this.getRay().getPoint()));
        double t = this.getRay().getDirection().dotProduct(temp);
        //if t=0 the vectors are orthogonal
        if (t == 0) {
            //Vertex on the Corner of the Cylinder base and tube
            if (getRadius() == (temp.length()))
                return temp.normalize();
            //Vertex on the Cylinder base
            return this.getRay().getDirection().scale(-1);
        }
        Point3D o = (this.getRay().getPoint().add(this.getRay().getDirection().scale(t)));
        //Vertex on the  Cylinder ray on the opposite base
        if (point.equals(o))
            return this.getRay().getDirection();
        Vector temp2 = new Vector(point.subtract(o));
        //Vertex on the Cylinder tube or on the Corner of the Cylinder opposite base with tube
        if (temp2.length() == this.getRadius())
            return temp2.normalize();
        //Vertex on the Cylinder opposite base
        return this.getRay().getDirection();
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "hight=" + height +
                '}' + super.toString();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray){
        return null;
    }
}
