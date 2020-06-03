package renderer;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.*;
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
     * MAX_CALC_COLOR_LEVEL - the maximum level of color
     * MIN_CALC_COLOR_K - the minimum level of color
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * ctor that gets all parameters
     *
     * @param img - Image Writer
     * @param sc  - Scene
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
        //Intersectable geometries = scene.getGeometries();
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
                GeoPoint intersectionPoint = findClosestIntersection(ray);
                imageWriter.writePixel(i, j, intersectionPoint == null ? background : calcColor(intersectionPoint, ray).getColor());
            }
        }
    }

    /**
     * calculates the color of a specific point. calls the recursive calcColor function
     *
     * @param geoPoint - the current point that is calculated
     * @param ray      - the current ray of point of view
     * @return - color value
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        Color color = calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, 1.0);
        color = color.add(scene.getAmbientLight().getIntensity());
        return color;
    }

    /**
     * recursive function that calculates the color of a specific point.
     * the recursive call is with every reflection and refraction ray
     *
     * @param geoPoint - the current point that is calculated
     * @param inRay    - the current ray of point of view
     * @param level    - level of how many times the function will run
     * @param k        - the level of k
     * @return - color value
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k) {
        Color result = geoPoint.geometry.getEmission();
        Point3D p = geoPoint.point;

        Vector v = inRay.getDirection();
        Vector n = geoPoint.geometry.getNormal(p);

        Material material = geoPoint.geometry.getMaterial();
        int nShininess = material.getnShininess();
        double kd = material.getkD();
        double ks = material.getkS();
        double nv = alignZero(n.dotProduct(v));

        List<LightSource> lightSources = scene.getLights();
        if (lightSources != null) {
            for (LightSource lightSource : lightSources) {
                Vector l = lightSource.getL(p);
                double nl = alignZero(n.dotProduct(l));
                if (nl * nv > 0) {
                    double ktr = transparency(lightSource, l, n, geoPoint);
                    if (ktr * k > MIN_CALC_COLOR_K) {
                        Color lightIntensity = lightSource.getIntensity(p).scale(ktr);
                        result = result.add(calcDiffusive(kd, nl, lightIntensity),
                                calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
                    }
                }
            }
        }

        double kr = geoPoint.geometry.getMaterial().getkR();
        double kt = geoPoint.geometry.getMaterial().getkT();
        double kkr = k * kr;
        double kkt = k * kt;
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = calcReflectionRay(geoPoint, v, n, nv);
            if (reflectedRay != null) {
                GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
                if (reflectedPoint != null)
                    result = result.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }

        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = calcRefractionRay(geoPoint, v, n);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null)
                result = result.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return result;
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
        Vector r = l.add(n.scale(-2 * nl)).normalize();
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Color.BLACK;
        return ip.scale(ks * Math.pow(minusVR, nShininess));
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
     * finds closest intersection point from a given rays point
     *
     * @param ray - the ray that is checked
     * @return the closest intersection point. if there is no intersection returns null
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersectionPoints = scene.getGeometries().findIntersections(ray);
        if (intersectionPoints == null)
            return null;
        double min = Double.MAX_VALUE;
        int result = 0;
        Point3D p0 = ray.getPoint();
        for (int i = 0; i < intersectionPoints.size(); i++) {
            double temp = intersectionPoints.get(i).point.distance(p0);
            if (temp < min) {
                min = temp;
                result = i;
            }
        }
        return intersectionPoints.get(result);
    }

    /**
     * calculates the Reflection Ray
     *
     * @param gp point on a shape
     * @param n  normal to surface at the point
     * @param v  direction from point of view to point
     * @param nv dot-product n*v
     * @return a Ray of Reflection direction
     */
    private Ray calcReflectionRay(GeoPoint gp, Vector v, Vector n, double nv) {
        if (nv == 0)
            return null;
        Vector vec = v.subtract(n.scale(2 * nv));
        return new Ray(gp.point, vec, n);
    }

    /**
     * calculates the Refraction Ray
     *
     * @param gp point on a shape
     * @param n  normal to surface at the point
     * @param v  direction from point of view to point
     * @return a Ray of Refraction direction
     */
    private Ray calcRefractionRay(GeoPoint gp, Vector v, Vector n) {
        return new Ray(gp.point, v, n);
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
     * calculates the transparency of the light
     *
     * @param ls - LightSource
     * @param l  - light direction
     * @param n  - normal of shape
     * @param gp - point
     * @return - transparency value
     */
    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint gp) {
        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        List<GeoPoint> intersections = scene.getGeometries().findIntersections(lightRay);
        if (intersections == null) {
            return 1;
        }
        double lightDistance = ls.getDistance(gp.point);
        double ktr = 1;
        for (GeoPoint geoPoint : intersections) {
            if (alignZero(geoPoint.point.distance(gp.point) - lightDistance) <= 0) {
                ktr *= geoPoint.geometry.getMaterial().getkT();
                if (ktr < MIN_CALC_COLOR_K)
                    return 0;
            }
        }
        return ktr;
    }

    /**
     * writes the Render view To Image
     * used after {link @renderImage}
     */
    public void writeToImage() {
        imageWriter.writeToImage();
    }
}
