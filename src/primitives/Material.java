package primitives;

/**
 * a class that represents the Material of objects
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class Material {

    /*
     * kD- Material Diffuse
     * kS - Material Specular
     * nShininess - Material Shininess
     * kR - Material reflection
     * kT - Material refraction
     */
    private double kD;
    private double kS;
    private int nShininess;
    private double kR;
    private double kT;

    /**
     * ctor for Material
     * refraction and reflection value are 0
     *
     * @param Diffuse   - Material Diffuse
     * @param Specular  - Material Specular
     * @param Shininess - Material Shininess
     */
    public Material(double Diffuse, double Specular, int Shininess) {
        this(Diffuse, Specular, Shininess, 0, 0);
    }

    /**
     * ctor for Material
     *
     * @param Diffuse    - Material Diffuse
     * @param Specular   - Material Specular
     * @param Shininess  - Material Shininess
     * @param reflection - Material reflection
     * @param refraction - Material refraction
     */
    public Material(double Diffuse, double Specular, int Shininess, double refraction, double reflection) {
        kD = Diffuse;
        kS = Specular;
        nShininess = Shininess;
        kT = refraction;
        kR = reflection;
    }

    /**
     * getter for Material Specular
     *
     */
    public double getkS() {
        return kS;
    }

    /**
     * getter for Material Diffuse
     *
     */
    public double getkD() {
        return kD;
    }

    /**
     * getter for Material Shininess
     *
     */
    public int getnShininess() {
        return nShininess;
    }

    /**
     * getter for Material reflection
     *
     */
    public double getkR() {
        return kR;
    }

    /**
     * getter for Material refraction
     *
     */
    public double getkT() {
        return kT;
    }
}
