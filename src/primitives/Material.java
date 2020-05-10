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
     * @param d - Material Diffuse
     * @param s - Material Specular
     * @param n - Material Shininess
     */
    public Material(double d, double s, int n) {
        kD = d;
        kS = s;
        nShininess = n;
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
