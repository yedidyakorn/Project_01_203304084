package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * a class that represents a camera
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Camera {

    /**
     * p- position of camera
     * three vectors that start from the camera to up, right and to the view plane
     */
    private Point3D p;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;

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
        Point3D pC = p.add(vTo.scale(screenDistance));
        double rY = screenHeight / nY;
        double rX = screenWidth / nX;
        double yi = ((i - (nY) / 2d) * rY + rY / 2d);//10-250*2+2/2=-489
        double xj = ((j - (nX) / 2d) * rX + rX / 2d);//10-400*2+2/2=-789
        Point3D Pij = pC;
        if (!isZero(xj))
            Pij = pC.add(vRight.scale(xj));
        if (!isZero(yi))
            Pij = Pij.add(vUp.scale(-yi));
        Vector Vij = Pij.subtract(p);
        return new Ray(p, Vij.normalize());
    }

}
