package renderer;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * a class that represents the Render engine
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Render {
    /**
     * imageWriter- the image that we are printing to
     * scene- the scene that the camera is looking at
     */
    private ImageWriter imageWriter;
    private Scene scene;

    /**
     * DELTA- represents a small move of rays to calculate she shade
     */
    private static final double DELTA = 0.1;

    /**
     * ctor that gets all parameters
     *
     * @param img
     * @param sc
     */
    public Render(ImageWriter img, Scene sc) {
        imageWriter = img;
        scene = sc;
    }

    /**
     * writes the scene on the ImageWriter
     * the function constructs a Ray Through every Pixel and writes the color in ImageWriter
     * using {link@constructRayThroughPixel}
     */
    public void renderImage() {
        Camera camera = scene.getCamera();
        Intersectable geometries = scene.getGeometries();
        java.awt.Color background = scene.getBackground().getColor();

        // nX,nY number of pixels
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        // width, height size in units
        double width = imageWriter.getWidth();
        double height = imageWriter.getHeight();
        double distance = scene.getDistance();

        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                Ray ray = camera.constructRayThroughPixel(nX, nY, i, j, distance, width, height);
                List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    imageWriter.writePixel(i, j, background);
                } else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    imageWriter.writePixel(i, j, calcColor(closestPoint).getColor());
                }
            }
        }
    }

    /**
     * calculates the Ambient Light in the scene
     *
     * @param p - a point that we want to calculate it's color
     * @return color of shape or of Ambient Light if there is no shape
     */
    public primitives.Color calcColor(GeoPoint p) {
        Color color = scene.getAmbientLight().getIntensity();
        color = color.add(p.geometry.getEmission());
        Vector v = p.point.subtract(scene.getCamera().getP()).normalize();
        Vector n = p.geometry.getNormal(p.point);
        int nShininess = p.geometry.getMaterial().getnShininess();
        double kD = p.geometry.getMaterial().getkD();
        double kS = p.geometry.getMaterial().getkS();

        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(p.point);
            double nl = alignZero(n.dotProduct(l));
            double nv = alignZero(n.dotProduct(v));
            if ((nl > 0 && nv > 0) || (nl < 0 && nv < 0)) {
                if (unshaded(l, n, p, lightSource)) {
                    Color lightIntensity = lightSource.getIntensity(p.point);
                    color = color.add(calcDiffusive(kD, nl, lightIntensity),
                            calcSpecular(kS, l, n, nl, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * Calculate Specular component of light reflection.
     *
     * @param ks         specular component
     * @param l          direction from light to point
     * @param n          normal to surface at the point
     * @param nl         dot-product n*l
     * @param v          direction from point of view to point
     * @param nShininess shininess level
     * @param ip         light intensity at the point
     * @return specular component light effect at the point
     */
    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
        double p = nShininess;
        Vector r = l.add(n.scale(-2 * nl)).normalize();
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Color.BLACK;
        return ip.scale(ks * Math.pow(minusVR, p));
    }

    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusive component
     * @param nl dot-product n*l
     * @param ip light intensity at the point
     * @return diffusive of light reflection
     */
    private Color calcDiffusive(double kd, double nl, Color ip) {
        if (nl < 0)
            nl = nl * (-1);
        return ip.scale(nl * kd);
    }

    /**
     * calculates the Closest Point to camera from list of intersection Points
     *
     * @param intersectionPoints- list of intersection Points of a ray in a single pixel
     * @return Closest Point to camera
     */
    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
        double min = Double.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < intersectionPoints.size(); i++) {
            double temp = intersectionPoints.get(i).point.distance(scene.getCamera().getP());
            if (temp < min) {
                min = temp;
                result = i;
            }
        }
        return intersectionPoints.get(result);
    }

    /**
     * prints a grid over the picture
     *
     * @param interval- spacious between the lines
     * @param color-    grid color
     */
    public void printGrid(int interval, java.awt.Color color) {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 1; i < nX; i++) {
            for (int j = 1; j < nY; j++) {
                if ((i % interval == 0) || (j % interval == 0))
                    imageWriter.writePixel(i, j, color);
            }
        }
    }

    /**
     * checks if a point on a shape is shaded
     *
     * @param l  - the vector from the  the light source to point
     * @param n  - the vector that represents shapes normal
     * @param gP - the point on a specific shape
     * @ true if the point in not shaded and false if not
     */
    private boolean unshaded(Vector l, Vector n, GeoPoint gP, LightSource lightSource) {
        Vector lightDirection = l.scale(-1);
        Vector deltaVector = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point3D point = gP.point.add(deltaVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.getGeometries().findIntersections(lightRay);
        if (intersections == null)
            return true;
        double lightDistance = lightSource.getDistance(gP.point);
        for (GeoPoint geoPoint : intersections) {
            if (alignZero((geoPoint.point.distance(point)) - lightDistance) <= 0)
                return false;
        }
        return true;
    }

    /**
     * writes the Render view To Image
     * used after {link @renderImage}
     */
    public void writeToImage() {
        imageWriter.writeToImage();
    }
}
