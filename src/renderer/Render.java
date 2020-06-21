package renderer;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Ray.rayRandomBeam;
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
    private int _threads = 1;
    private final int SPARE_THREADS = 2;
    private boolean _print = false;

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
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
     */
    public void renderImage() {
        final int nX = imageWriter.getNx();
        final int nY = imageWriter.getNy();
        final double dist = scene.getDistance();
        final double width = imageWriter.getWidth();
        final double height = imageWriter.getHeight();
        final Camera camera = scene.getCamera();

        final Pixel thePixel = new Pixel(nY, nX);

        // Generate threads
        Thread[] threads = new Thread[_threads];
        for (int i = _threads - 1; i >= 0; --i) {
            threads[i] = new Thread(() -> {
                Pixel pixel = new Pixel();
                while (thePixel.nextPixel(pixel)) {
//                    List<Ray> rays = camera.constructRaysThroughPixel(nX, nY, pixel.col, pixel.row, //
//                            dist, width, height);
//                    imageWriter.writePixel(pixel.col, pixel.row, calcColor(rays).getColor());
                    Ray ray = camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row, dist, width, height);
                    imageWriter.writePixel(pixel.col, pixel.row, superSampling(ray).getColor());
                    System.out.print("\n");
                }
            });
        }

        // Start threads
        for (Thread thread : threads) thread.start();

        // Wait for all threads to finish
        for (Thread thread : threads)
            try {
                thread.join();
            } catch (Exception e) {
            }
        if (_print) System.out.printf("\r100%%\n");
    }

    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
     */
    public void oldrenderImage() {
        final int nX = imageWriter.getNx();
        final int nY = imageWriter.getNy();
        final double dist = scene.getDistance();
        final double width = imageWriter.getWidth();
        final double height = imageWriter.getHeight();
        final Camera camera = scene.getCamera();

        final Pixel thePixel = new Pixel(nY, nX);

        // Generate threads
        Thread[] threads = new Thread[_threads];
        for (int i = _threads - 1; i >= 0; --i) {
            threads[i] = new Thread(() -> {
                Pixel pixel = new Pixel();
                while (thePixel.nextPixel(pixel)) {
                    List<Ray> rays = camera.constructRaysThroughPixel(nX, nY, pixel.col, pixel.row, //
                            dist, width, height);
                    imageWriter.writePixel(pixel.col, pixel.row, calcColor(rays).getColor());

                }
            });
        }

        // Start threads
        for (Thread thread : threads) thread.start();

        // Wait for all threads to finish
        for (Thread thread : threads)
            try {
                thread.join();
            } catch (Exception e) {
            }
        if (_print) System.out.printf("\r100%%\n");
    }

    //TODO
    public Color superSampling(Ray ray) {
        Color result = scene.getBackground();
        Point3D pij = ray.getPoint(scene.getDistance() / (scene.getCamera().getvTo().dotProduct(ray.getDirection())));
        Point3D f = ray.getPoint((scene.getCamera().getFocalDistance() + scene.getDistance()) / (scene.getCamera().getvTo().dotProduct(ray.getDirection())));//focal point
        Color color = rec(scene.getCamera().getAperture(), scene.getCamera().getNumOfRays(), pij, f, 3);
        return result.add(color.reduce(scene.getCamera().getNumOfRays()));
    }

    //TODO
    private Color rec(double radius, int num, Point3D center, Point3D target, int k) {
        if (k == 0) {
            System.out.print("$");
            return addColors(rayRandomBeam(center, target, radius, num, scene.getCamera().getvRight(), scene.getCamera().getvUp()));
        }
        Ray a = new Ray(center.add(scene.getCamera().getvUp().scale(radius)), target.subtract(center.add(scene.getCamera().getvUp().scale(radius))));
        Ray b = new Ray(center.add(scene.getCamera().getvRight().scale(radius)), target.subtract(center.add(scene.getCamera().getvRight().scale(radius))));
        Ray c = new Ray(center.add(scene.getCamera().getvUp().scale(-radius)), target.subtract(center.add(scene.getCamera().getvUp().scale(-radius))));
        Ray d = new Ray(center.add(scene.getCamera().getvRight().scale(-radius)), target.subtract(center.add(scene.getCamera().getvRight().scale(-radius))));
        Ray rCenter = new Ray(center, target.subtract(center));
        Color colorA = calcColor(List.of(a));
        Color colorB = calcColor(List.of(b));
        Color colorC = calcColor(List.of(c));
        Color colorD = calcColor(List.of(d));
        Color cCenter = calcColor(List.of(rCenter));
        double newRad = radius / (1d + 1.414);
        if (colorA != cCenter)
            cCenter = cCenter.add(rec(newRad, num / 4, center.add(scene.getCamera().getvUp().scale(radius - newRad)), target, k - 1));
        else
            cCenter = cCenter.add(colorA.scale(num / 4));
        if (colorB != cCenter)
            cCenter = cCenter.add(rec(newRad, num / 4, center.add(scene.getCamera().getvRight().scale(radius - newRad)), target, k - 1));
        else
            cCenter = cCenter.add(colorB.scale(num / 4));
        if (colorC != cCenter)
            cCenter = cCenter.add(rec(newRad, num / 4, center.add(scene.getCamera().getvUp().scale(-(radius - newRad))), target, k - 1));
        else
            cCenter = cCenter.add(colorC.scale(num / 4));
        if (colorD != cCenter)
            cCenter = cCenter.add(rec(newRad, num / 4, center.add(scene.getCamera().getvRight().scale(-(radius - newRad))), target, k - 1));
        else
            cCenter = cCenter.add(colorD.scale(num / 4));
        return cCenter;
    }

    //TODO
    private Color addColors(List<Ray> rays) {
        Color background = scene.getBackground();
        Color sum = background;
        for (Ray ray : rays) {
            GeoPoint intersectionPoint = findClosestIntersection(ray);
            sum = sum.add(intersectionPoint == null ? background : calcColor(intersectionPoint, ray));
        }
        return sum;
    }

    /**
     * calculates the color of a specific point, by calculating the average color from the list of rays- beam of rays.
     * the average gives the effect of depth of filed.
     * calls the recursive calcColor function
     *
     * @param rays - beam of rays from the area of one pixel
     * @return - the average color from all rays- the final color that will be whited.
     */
    private Color calcColor(List<Ray> rays) {
        return addColors(rays).reduce(rays.size());
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
     * Pixel is an internal helper class whose objects are associated with a Render object that
     * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
     * its progress.<br/>
     * There is a main follow up object and several secondary objects - one in each thread.
     *
     * @author Dan
     */
    private class Pixel {
        private long _maxRows = 0;
        private long _maxCols = 0;
        private long _pixels = 0;
        public volatile int row = 0;
        public volatile int col = -1;
        private long _counter = 0;
        private int _percents = 0;
        private long _nextCounter = 0;

        /**
         * The constructor for initializing the main follow up Pixel object
         *
         * @param maxRows the amount of pixel rows
         * @param maxCols the amount of pixel columns
         */
        public Pixel(int maxRows, int maxCols) {
            _maxRows = maxRows;
            _maxCols = maxCols;
            _pixels = maxRows * maxCols;
            _nextCounter = _pixels / 100;
            if (Render.this._print) System.out.printf("\r %02d%%", _percents);
        }

        /**
         * Default constructor for secondary Pixel objects
         */
        public Pixel() {
        }

        /**
         * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
         * critical section for all the threads, and main Pixel object data is the shared data of this critical
         * section.<br/>
         * The function provides next pixel number each call.
         *
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
         * finished, any other value - the progress percentage (only when it changes)
         */
        private synchronized int nextP(Pixel target) {
            ++col;
            ++_counter;
            if (col < _maxCols) {
                target.row = this.row;
                target.col = this.col;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            ++row;
            if (row < _maxRows) {
                col = 0;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            return -1;
        }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         *
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return true if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
            int percents = nextP(target);
            if (percents > 0)
                if (Render.this._print) System.out.printf("\r %02d%%", percents);
            if (percents >= 0)
                return true;
            if (Render.this._print) System.out.printf("\r %02d%%", 100);
            return false;
        }
    }

    /**
     * Set multithreading <br>
     * - if the parameter is 0 - number of coress less 2 is taken
     *
     * @param threads number of threads
     * @return the Render object itself
     */
    public Render setMultithreading(int threads) {
        if (threads < 0)
            throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
        if (threads != 0)
            _threads = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            if (cores <= 2)
                _threads = 1;
            else
                _threads = cores;
        }
        return this;
    }

    /**
     * Set debug printing on
     *
     * @return the Render object itself
     */
    public Render setDebugPrint() {
        _print = true;
        return this;
    }

    /**
     * writes the Render view To Image
     * used after {link @renderImage}
     */
    public void writeToImage() {
        imageWriter.writeToImage();
    }
}
