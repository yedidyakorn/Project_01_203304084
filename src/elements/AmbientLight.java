package elements;

import primitives.Color;


/**
 * a class that represents the Ambient Lighting extends @Light class
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class AmbientLight extends Light {

    /**
     * ctor calculates the Ambient Lighting
     * @param iA
     * @param kA
     */
    public AmbientLight(Color iA, double kA){
        super(iA.scale(kA));
    }

}
