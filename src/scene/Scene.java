package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

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
     * lights- list of the Light Sources
     */
    private String name;
    private Color background;
    private AmbientLight ambientLight;
    private Geometries geometries;
    private Camera camera;
    private double distance;
    private List<LightSource> lights;


    /**
     * ctor sets the name only
     *
     * @param name
     */
    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
        lights = new LinkedList<LightSource>();
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
     * add light sources to scene
     *
     * @param lights- collection of lights
     */
    public void addLights(LightSource... lights) {
        this.lights.addAll(List.of(lights));
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
     * getter for lights
     *
     * @return returns a linkedList
     */
    public List<LightSource> getLights() {
        return lights;
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
    public void addGeometries(Intersectable... geometries) {
        this.geometries.add(geometries);
    }
}
