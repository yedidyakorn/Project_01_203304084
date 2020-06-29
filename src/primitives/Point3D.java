package primitives;

import java.util.Objects;

/**
 * class Point3D is the basic class representing a point for Cartesian
 * coordinate system.
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Point3D {

    private Coordinate x;
    private Coordinate y;
    private Coordinate z;

    /**
     * static filed to get the "zero point"
     */
    public static Point3D ZERO = new Point3D(0, 0, 0);


    /**
     * Point ctor recving three Coordinates
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Point ctor recving three numbers and buliding a Point using Coordinate class
     */
    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    /**
     * copy ctor recving a Point
     *
     * @param other is a Point3D
     */
    public Point3D(Point3D other) {
        x = other.x;
        y = other.y;
        z = other.z;
    }

    /**
     * Point getters by parameter
     *
     * @return parmeter value
     */
    public Coordinate getX() {
        return x;
    }

    /**
     * Point getters by parameter
     *
     * @return parmeter value
     */
    public Coordinate getY() {
        return y;
    }

    /**
     * Point getters by parameter
     *
     * @return parmeter value
     */
    public Coordinate getZ() {
        return z;
    }

    /**
     * Subtraction from a given point to this point
     *
     * @param other- Point3D
     * @return a vector that starts from the given point to this point
     */
    public Vector subtract(Point3D other) {
        double a = x.get() - other.x.get();
        double b = y.get() - other.y.get();
        double c = z.get() - other.z.get();
        return new Vector(a, b, c);
    }

    /**
     * add a vector to the point
     *
     * @param vec-Vector
     * @return a new point after adding the vector
     */
    public Point3D add(Vector vec) {
        double a = vec.getEndPoint().x.get() + x.get();
        double b = vec.getEndPoint().y.get() + y.get();
        double c = vec.getEndPoint().z.get() + z.get();
        return new Point3D(a, b, c);
    }

    /**
     * calculates the distance between two points squared
     *
     * @param other- point
     * @return the calculation
     */
    public double distanceSquared(Point3D other) {
        double deltaX = other.x.get() - x.get();
        double deltaY = other.y.get() - y.get();
        double deltaZ = other.z.get() - z.get();
        return deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
    }

    /**
     * calculates the distance between two points
     *
     * @param other- point
     * @return the calculation
     */
    public double distance(Point3D other) {
        return Math.sqrt(this.distanceSquared(other));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;
        if (!(o instanceof Point3D)) return false;
        Point3D point3D = (Point3D) o;
        return Objects.equals(x, point3D.x) &&
                Objects.equals(y, point3D.y) &&
                Objects.equals(z, point3D.z);
    }

    @Override
    public String toString() {
        return ("(" + x + ", " + y + ", " + z + ")");
    }

}
