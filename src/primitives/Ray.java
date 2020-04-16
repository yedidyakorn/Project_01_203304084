package primitives;


import java.util.Objects;

/**
 * class Ray is the basic class representing a ray for Cartesian
 * coordinate system.
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Ray {

    /**
     * p begaing point
     * v direction
     */
    private Point3D p;
    private Vector v;

    /**
     * Ray ctor receives a point and a vector
     */
    public Ray(Point3D p, Vector v) {
        this.p = new Point3D(p);
        this.v = v.normalized();
    }

    /**
     * Ray copy ctor
     */
    public Ray(Ray other) {
        this.p = new Point3D(other.p);
        this.v = new Vector(other.v);
    }

    /**
     * getter for beginning point
     */
    public Point3D getPoint() {
        return p;
    }

    /**
     * getter for direction vector
     */
    public Vector getDirection() {
        return v;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Ray)) return false;
        Ray Ray = (Ray) o;
        return Objects.equals(p, Ray.p) &&
                Objects.equals(v, Ray.v);
    }

    @Override
    public String toString() {
        return ("point: " + p + " vector:" + v + "\n");
    }
}

