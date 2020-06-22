package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Ray.rayRandomBeam;
import static primitives.Util.isZero;

/**
 * a class that represents a camera
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Camera {

    /*
     * p- position of camera
     * three vectors that start from the camera to up, right and to the view plane
     */
    private Point3D p;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;

    /*
     *  focalDistance - the distance of the  focus.
     *  aperture      - the radius of the aperture.
     *  numOfRays     - number of rays that will be in the beam from every pixels area (in addition to the original ray).
     */
    private double focalDistance;
    private double aperture;
    private int numOfRays;

    /**
     * getter for camera position
     */
    public Point3D getP() {
        return p;
    }

    /**
     * getter for right vector
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * getter for vector to the view plane
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * getter for vector going up
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * getter for aperture size
     */
    public double getAperture() {
        return aperture;
    }

    /**
     * getter for the number of rays
     */
    public int getNumOfRays() {
        return numOfRays;
    }

    /**
     * getter for the focal distance
     */
    public double getFocalDistance() {
        return focalDistance;
    }

    /**
     * ctor for camera that gets two vectors and the position and calculates the third vector
     *
     * @param up - up vector
     * @param to - vector to the view plane
     * @param p  - the camera position
     */
    public Camera(Point3D p, Vector to, Vector up) {
        if (up.dotProduct(to) != 0)
            throw new IllegalArgumentException("the vectors must be orthogonal");
        this.p = new Point3D(p);
        vTo = to.normalized();
        vUp = up.normalized();
        vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * setter of Depth of filed. if Depth of filed function is called the camera will be focused for a specific distance.
     * if Depth of filed will not be called the camera will be focused on the whole scene equally.
     *
     * @param focalDistance - the distance of the  focus.
     * @param aperture      - the radius of the aperture.
     * @param numOfRays     - number of rays that will be in the beam from every pixels area (in addition to the original ray).
     */
    public void setDepthOfFiled(double focalDistance, double aperture, int numOfRays) {
        this.focalDistance = focalDistance;
        this.aperture = aperture;
        this.numOfRays = numOfRays;
    }

    /**
     * builds a Ray from the camera point through a specific pixel on view plane
     *
     * @param nX             - number of cells left to right
     * @param nY             - number of cells up to down
     * @param j              - index of width cell
     * @param i              - index of height cell
     * @param screenDistance - the distance between the camera and the view plane
     * @param screenWidth    - width of view plane in pixels
     * @param screenHeight   - height of view plane in pixels
     * @return - a new Ray
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight) {
        double rY = screenHeight / nY;
        double rX = screenWidth / nX;
        double yi = ((i - (nY) / 2d) * rY + rY / 2d);
        double xj = ((j - (nX) / 2d) * rX + rX / 2d);
        Point3D pij = p.add(vTo.scale(screenDistance));
        if (!isZero(xj))
            pij = pij.add(vRight.scale(xj));
        if (!isZero(yi))
            pij = pij.add(vUp.scale(-yi));
        Vector vij = pij.subtract(p);
        return new Ray(p, vij);
    }

    /**
     * builds a beam of Rays from the area of a pixel through a specific point on the focal plane
     *
     * @param nX             - number of cells left to right
     * @param nY             - number of cells up to down
     * @param j              - index of width cell
     * @param i              - index of height cell
     * @param screenDistance - the distance between the camera and the view plane
     * @param screenWidth    - width of view plane in pixels
     * @param screenHeight   - height of view plane in pixels
     * @return - a list of rays that contains the beam of rays
     */
    public List<Ray> constructRaysThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight) {
        Ray ray = constructRayThroughPixel(nX, nY, j, i, screenDistance, screenWidth, screenHeight);
        Point3D pij = ray.getPoint(screenDistance / (vTo.dotProduct(ray.getDirection())));
        Point3D f = ray.getPoint((focalDistance + screenDistance) / (vTo.dotProduct(ray.getDirection())));//focal point
        List<Ray> result = rayRandomBeam(pij, f, aperture, numOfRays, vRight, vUp);
        result.add(new Ray(pij, ray.getDirection()));
        return result;
    }
}
