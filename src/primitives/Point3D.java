package primitives;

import primitives.Coordinate.*;

import java.util.Objects;

/**
 * class Point3D is the basic class representing a point for Cartesian
 * coordinate system.
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Point3D {

    Coordinate x;
    Coordinate y;
    Coordinate z;


    /**
     * Point ctor recving three Coordinates
     *
     */
    public Point3D(Coordinate x,Coordinate y,Coordinate z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    /**
     * Point ctor recving three numbers and buliding a Point using Coordinate class
     */
    public Point3D(double x, double y, double z){
        this.x= new Coordinate(x);
        this.y= new Coordinate(y);
        this.z= new Coordinate(z);
    }

    /**
     * copy ctor recving a Point
     * @param other is a Point3D
     */
    public Point3D (Point3D other){
        this.x=other.x;
        this.y=other.y;
        this.z=other.z;
    }

    /**
     * Point getters by parameter
     * @return parmeter value
     */
    public Coordinate getX() {
        return x;
    }

    public Coordinate getY() {
        return y;
    }

    public Coordinate getZ() {
        return z;
    }

    /**
     * function to get the "zero point"
     * @return a "zero point"
     */
    public static Point3D Zero(){
        return new Point3D(0, 0, 0);
    }

    /**
     * Subtraction from a given point to this point
     * @param other- Point3D
     * @return a vector that starts from the given point to this point
     */
    public Vector subtract (Point3D other){
        double a=this.x.get()-other.getX().get();
        double b=this.y.get()-other.getY().get();
        double c=this.z.get()-other.getZ().get();
        return new Vector(a,b,c);
    }

    /**
     * add a vector to the point
     * @param vec-Vector
     * @return a new point after adding the vector
     */
    public Point3D add(Vector vec){
        double a=vec.getEndPoint().getX().get()+this.x.get();
        double b=vec.getEndPoint().getY().get()+this.y.get();
        double c=vec.getEndPoint().getZ().get()+this.z.get();
        return new Point3D(a,b,c);
    }

    /**
     * calculates the distance between two points squared
     * @param other- point
     * @return the calculation
     */
    public double distanceSquared(Point3D other){
        double a=other.getX().get()-this.getX().get();
        double b=other.getY().get()-this.getY().get();
        double c=other.getZ().get()-this.getZ().get();
        return a*a+b*b+c*c;
    }

    /**
     * calculates the distance between two points
     * @param other- point
     * @return the calculation
     */
    public double distance (Point3D other){
        return Math.sqrt(this.distanceSquared(other));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return Objects.equals(x, point3D.x) &&
                Objects.equals(y, point3D.y) &&
                Objects.equals(z, point3D.z);
    }

    @Override
    public String toString(){
        return ("("+x+", "+y+", "+z+")");
    }

}
