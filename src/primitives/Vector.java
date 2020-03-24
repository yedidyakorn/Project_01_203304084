package primitives;

import java.util.Objects;

/**
 * class Vector is the basic class representing a vector for Cartesian
 * coordinate system.
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Vector {

    /**
     * the vector starts from First the hinges and ends at the "endOfPoint" point
     */
    Point3D endPoint;

    /**
     * Vector ctor receives three Coordinates
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        Point3D temp = new Point3D(x, y, z);
        if (temp.equals(Point3D.Zero())) {
            throw new IllegalArgumentException("a vector from zero point to zero point is illegal");
        }
        endPoint = temp;
    }

    /**
     * Vector ctor receives three numbers
     */
    public Vector(double x, double y, double z) {
        Point3D temp = new Point3D(x, y, z);
        if (temp.equals(Point3D.Zero())) {
            throw new IllegalArgumentException("a vector from zero point to zero point is illegal");
        }
        endPoint = temp;
    }

    /**
     * Vector ctor a endig point
     */
    public Vector(Point3D point) {
        if (point.equals(Point3D.Zero())) {
            throw new IllegalArgumentException("a vector from zero point to zero point is illegal");
        }
        endPoint = new Point3D(point);
    }

    /**
     * Vector copy ctor
     */
    public Vector(Vector vec) {
        endPoint = new Point3D(vec.getEndPoint());
    }

    /**
     * add two vectors
     *
     * @param other-Vector
     * @return a new vector
     */
    public Vector add(Vector other) {
        return new Vector(other.getEndPoint().add(this));
    }

    /**
     * subs two vectors
     *
     * @param other-Vector
     * @return a new vector
     */
    public Vector subtract(Vector other) {
        return new Vector(this.getEndPoint().subtract(other.getEndPoint()));
    }

    public Point3D getEndPoint() {
        return endPoint;
    }

    /**
     * multiples a vector by a number
     *
     * @param num number
     * @return a new vector
     */
    public Vector scale(double num) {
        double x = this.getEndPoint().getX().get() * num;
        double y = this.getEndPoint().getY().get() * num;
        double z = this.getEndPoint().getZ().get() * num;
        return new Vector(x, y, z);
    }

    /**
     * dot Product between two vectors
     *
     * @param other-Vector
     * @return a number
     */
    public double dotProduct(Vector other) {
        double x = this.getEndPoint().getX().get() * other.getEndPoint().getX().get();
        double y = this.getEndPoint().getY().get() * other.getEndPoint().getY().get();
        double z = this.getEndPoint().getZ().get() * other.getEndPoint().getZ().get();
        return x + y + z;
    }

    /**
     * cross Product between two vectors
     *
     * @param other-Vector
     * @return a Vector
     */
    public Vector crossProduct(Vector other) {
        double x = this.getEndPoint().getY().get() * other.getEndPoint().getZ().get() - this.getEndPoint().getZ().get() * other.getEndPoint().getY().get();
        double y = this.getEndPoint().getZ().get() * other.getEndPoint().getX().get() - this.getEndPoint().getX().get() * other.getEndPoint().getZ().get();
        double z = this.getEndPoint().getX().get() * other.getEndPoint().getY().get() - this.getEndPoint().getY().get() * other.getEndPoint().getX().get();
        return new Vector(x, y, z);
    }

    /**
     * calculates the vector length squared
     *
     * @return the calculation
     */
    public double lengthSquared() {
        return this.getEndPoint().distanceSquared(Point3D.Zero());
    }

    /**
     * calculates the vector length
     *
     * @return the calculation
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * normalizes the vector so the length will be 1
     * changes the value of the vector
     *
     * @return the normalized vector
     */
    public Vector normalize() {
        endPoint = this.normalized().getEndPoint();
        return this;
    }

    /**
     * normalizes the vector so the length will be 1
     * does'n change the value of the vector
     *
     * @return a new normalized vector
     */
    public Vector normalized() {
        double x = this.getEndPoint().getX().get() / this.length();
        double y = this.getEndPoint().getY().get() / this.length();
        double z = this.getEndPoint().getZ().get() / this.length();
        return new Vector(x, y, z);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Objects.equals(endPoint, vector.endPoint);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "endPoint=" + endPoint +
                '}';
    }
}
