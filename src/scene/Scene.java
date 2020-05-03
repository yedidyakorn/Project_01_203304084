package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

/**
 * a class that represents a scene
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Scene {

    /**
     * name- scene name
     * background- scene background color
     * ambientLight- scene ambientLight
     * geometries- list of geometry shapes
     * camera- the camera that is watching the scene
     */
    String name;
    Color background;
    AmbientLight ambientLight;
    Geometries geometries;
    Camera camera;
    double distance;

    /**
     * ctor sets the name only
     *
     * @param name
     */
    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
    }

    /**
     * getter for camera
     *
     * @return
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * getter for AmbientLight
     *
     * @return
     */
    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    /**
     * getter for Background
     *
     * @return
     */
    public Color getBackground() {
        return background;
    }

    /**
     * getter for Distance between camera and view plane
     *
     * @return
     */
    public double getDistance() {
        return distance;
    }

    /**
     * getter for Geometries list
     *
     * @return
     */
    public Geometries getGeometries() {
        return geometries;
    }

    /**
     * getter for name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * setter for Background
     *
     * @param background
     */
    public void setBackground(Color background) {
        this.background = background;
    }

    /**
     * setter for ambientLight
     *
     * @param ambientLight
     */
    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }

    /**
     * setter for camera
     *
     * @param camera
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * setter for Distance between camera and view plane
     *
     * @param distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * adds shapes to the scene
     *
     * @param geometries
     */
    void addGeometries(Intersectable... geometries) {
        this.geometries.add(geometries);
    }
}
