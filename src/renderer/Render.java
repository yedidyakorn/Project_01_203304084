package renderer;

import elements.Camera;
import geometries.Intersectable;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.awt.*;
import java.util.List;

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
    ImageWriter imageWriter;
    Scene scene;

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
                List<Point3D> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    imageWriter.writePixel(i, j, background);
                } else {
                    Point3D closestPoint = getClosestPoint(intersectionPoints);
                    imageWriter.writePixel(i, j, calcColor(closestPoint).getColor());
                }
            }
        }
    }

    /**
     * calculates the Ambient Light in the scene
     *
     * @param p - a point that we want to calculate it's color
     * @return color of Ambient Light
     */
    public primitives.Color calcColor(Point3D p) {
        return scene.getAmbientLight().GetIntensity();
    }

    /**
     * calculates the Closest Point to camera from list of intersection Points
     *
     * @param intersectionPoints- list of intersection Points of a ray in a single pixel
     * @return Closest Point to camera
     */
    private Point3D getClosestPoint(List<Point3D> intersectionPoints) {
        double min = Double.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < intersectionPoints.size(); i++) {
            double temp = intersectionPoints.get(i).distance(scene.getCamera().getP());
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
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                if ((i % interval == 0) && (i > 0) || (j % interval == 0) && (j > 0))
                    imageWriter.writePixel(i, j, new Color(188, 250, 94));
            }
        }
    }

    /**
     * writes the Render view To Image
     * used after {link @renderImage}
     */
    public void writeToImage() {
        imageWriter.writeToImage();
    }
}
