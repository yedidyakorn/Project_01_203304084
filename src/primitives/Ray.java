package primitives;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.randomRange;

/**
 * class Ray is the basic class representing a ray for Cartesian
 * coordinate system.
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Ray {

    /*
     * p begging point
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
     * moves of rays point on normal vector by DELTA
     *
     * @param p      - rays point
     * @param v      - rays vector
     * @param normal - normal vector
     */
    public Ray(Point3D p, Vector v, Vector normal) {
        this.v = v.normalized();
        double nv = alignZero(normal.dotProduct(v));
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
     *
     * @param t - the distance between the base point and the wanted point
     * @return the result point
     */
    public Point3D getPoint(double t) {
        return p.add(v.scale(t));
    }

    /**
     * function that creates a beam of rays in random positions. all the rays point to one point
     *
     * @param center    - the center of the base of the beam
     * @param target    - the target point that all rays point to
     * @param rad       - the radius of the base of the beam
     * @param numOfRays - number of rays that will be created
     * @param vRight    - a vector orthogonal to the original ray
     * @param vUp       -  a vector orthogonal to the original ray and the right vector
     * @return - a list that contains all the new rays
     */
    public static List<Ray> rayRandomBeam(Point3D center, Point3D target, double rad, int numOfRays, Vector vRight, Vector vUp) {
        List<Ray> result = new LinkedList<>();
        for (int k = 0; k < numOfRays; k++) {
            double x = randomRange(-rad, rad);
            double cosX = Math.sqrt(rad - x * x);
            double y = randomRange(-cosX, cosX);
            Point3D pC = center.add(vRight.scale(x));//a point on view plane around the pixel
            pC = pC.add(vUp.scale(y));
            Ray focalRay = new Ray(pC, target.subtract(pC));
            result.add(focalRay);
        }
        return result;
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

