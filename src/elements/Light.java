package elements;

import primitives.Color;

/**
 * a class that represents Light intensity
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public abstract class Light {

    /**
     * intensity - Color type. represents the intensity of thr light
     */
    protected Color intensity;

    /**
     * ctor for light
     *
     * @param intensity - Color
     */
    public Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for intensity
     *
     * @return Color type
     */
    public Color getIntensity() {
        return intensity;
    }
}
