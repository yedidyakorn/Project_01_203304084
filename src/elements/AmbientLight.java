package elements;

import primitives.Color;


/**
 * a class that represents the Ambient Lighting
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class AmbientLight {

    /**
     * represents the intensity of the Ambient Lighting
     */
    Color intensity;

    /**
     * ctor calculates the Ambient Lighting
     * @param iA
     * @param kA
     */
    public AmbientLight(Color iA, double kA){
        intensity=iA.scale(kA);
    }

    /**
     * getter for the Ambient light
     */
    public Color GetIntensity(){
        return this.intensity;
    }
}
