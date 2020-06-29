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
    private Point3D endPoint;

    /**
     * Vector ctor receives three Coordinates
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        Point3D temp = new Point3D(x, y, z);
        if (temp.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("a vector from zero point to zero point is illegal");
        }
        endPoint = temp;
    }

    /**
     * Vector ctor receives three numbers
     */
    public Vector(double x, double y, double z) {
        Point3D temp = new Point3D(x, y, z);
        if (temp.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("a vector from zero point to zero point is illegal");
        }
        endPoint = temp;
    }

    /**
     * Vector ctor a endig point
     */
    public Vector(Point3D point) {
        if (point.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("a vector from zero point to zero point is illegal");
        }
        endPoint = new Point3D(point);
    }

    /**
     * Vector copy ctor
     */
    public Vector(Vector vec) {
        endPoint = new Point3D(vec.endPoint);
    }

    /**
     * add two vectors
     *
     * @param other-Vector
     * @return a new vector
     */
    public Vector add(Vector other) {
        return new Vector(other.endPoint.add(this));
    }

    /**
     * subs two vectors
     *
     * @param other-Vector
     * @return a new vector
     */
    public Vector subtract(Vector other) {
        return endPoint.subtract(other.endPoint);
    }

    /**
     * getter for ending point
     *
     */
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
        double x = endPoint.getX().get() * num;
        double y = endPoint.getY().get() * num;
        double z = endPoint.getZ().get() * num;
        return new Vector(x, y, z);
    }

    /**
     * dot Product between two vectors
     *
     * @param other-Vector
     * @return a number
     */
    public double dotProduct(Vector other) {
        double xx = endPoint.getX().get() * other.endPoint.getX().get();
        double yy = endPoint.getY().get() * other.endPoint.getY().get();
        double zz = endPoint.getZ().get() * other.endPoint.getZ().get();
        return xx + yy + zz;
    }

    /**
     * cross Product between two vectors
     *
     * @param other-Vector
     * @return a Vector
     */
    public Vector crossProduct(Vector other) {
        double thisX = endPoint.getX().get();
        double thisY = endPoint.getY().get();
        double thisZ = endPoint.getZ().get();
        double otherX = other.endPoint.getX().get();
        double otherY = other.endPoint.getY().get();
        double otherZ = other.endPoint.getZ().get();
        return new Vector(thisY * otherZ - thisZ * otherY, thisZ * otherX - thisX * otherZ, thisX * otherY - thisY * otherX);
    }

    /**
     * calculates the vector length squared
     *
     * @return the calculation
     */
    public double lengthSquared() {
        double x = endPoint.getX().get();
        double y = endPoint.getY().get();
        double z = endPoint.getZ().get();
        return x * x + y * y + z * z;
    }

    /**
     * calculates the vector length
     *
     * @return the calculation
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * normalizes the vector so the length will be 1
     * changes the value of the vector
     *
     * @return the normalized vector
     */
    public Vector normalize() {
        double length = this.length();
        double x = endPoint.getX().get() / length;
        double y = endPoint.getY().get() / length;
        double z = endPoint.getZ().get() / length;
        endPoint = new Point3D(x, y, z);
        return this;
    }

    /**
     * normalizes the vector so the length will be 1
     * does'n change the value of the vector
     *
     * @return a new normalized vector
     */
    public Vector normalized() {
        return new Vector(this.normalize());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Vector)) return false;
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
