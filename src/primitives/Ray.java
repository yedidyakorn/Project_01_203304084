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
     * DELTA- represents a small move of rays point
     */
    private static final double DELTA = 0.1;

    /**
     * Ray ctor receives a point and a vector
     */
    public Ray(Point3D p, Vector v) {
        this.p = new Point3D(p);
        this.v = v.normalized();
    }

    /**
     * Ray ctor receives a point and a vector and a normal vector
     * moves of rays point in  direction of normal by DELTA
     *
     * @param p      - rays point
     * @param v      - rays vector
     * @param normal - normal vector
     */
    public Ray(Point3D p, Vector v, Vector normal) {
        this.v = new Vector(v).normalized();
        double nv = normal.dotProduct(v);
        if (nv == 0)
            this.p = new Point3D(p);
        else
            this.p = new Point3D(p.add(normal.scale(nv > 0 ? DELTA : -DELTA)));
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

    /**
     * finds a point on the ray
     * @param t - the distance between the base point and the wanted point
     * @return the result point
     */
    public Point3D getPoint(double t){
        return p.add(v.scale(t));
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

