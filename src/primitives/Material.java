package primitives;

/**
 * a class that represents the Material of objects
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Material {

    /**
     * kD- Material Diffuse
     * kS - Material Specular
     * nShininess - Material Shininess
     */
    private double kD;
    private double kS;
    private int nShininess;

    /**
     * ctor for Material
     *
     * @param Diffuse - Material Diffuse
     * @param Specular - Material Specular
     * @param Shininess - Material Shininess
     */
    public Material(double Diffuse, double Specular, int Shininess) {
        kD = Diffuse;
        kS = Specular;
        nShininess = Shininess;
    }

    /**
     * getter for Material Specular
     *
     * @return
     */
    public double getkS() {
        return kS;
    }

    /**
     * getter for Material Diffuse
     *
     * @return
     */
    public double getkD() {
        return kD;
    }

    /**
     * getter for Material Shininess
     *
     * @return
     */
    public int getnShininess() {
        return nShininess;
    }


}
