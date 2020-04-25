package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;

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
    Point3D p;
    Vector vUp;
    Vector vTo;
    Vector vRight;

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
     * @param up - up vector
     * @param to - vector to the view plane
     * @param p - the camera position
     */
    public Camera( Point3D p, Vector to, Vector up){
        if(up.dotProduct(to)!=0)
            throw new IllegalArgumentException("the vectors must be orthogonal");
        this.p=new Point3D(p);
        vTo=to.normalized();
        vUp=up.normalized();
        vRight=vTo.crossProduct(vUp).normalize();
    }

    public Ray constructRayThroughPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight){
        return null;
    }



}
