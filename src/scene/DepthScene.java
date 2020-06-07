package scene;

public class DepthScene extends Scene {

    private double focalDistance;
    private double aperture;
    private int numOfRays;

    public DepthScene(String name) {
        super(name);

    }

    //TODO
    public double getFocalDistance() {
        return focalDistance;
    }

    //TODO
    public void setFocalDistance(double focalDistance) {
        this.focalDistance = focalDistance;
    }

    public void setNumOfRays(int numOfRays) {
        this.numOfRays = numOfRays;
    }

    public int getNumOfRays() {
        return numOfRays;
    }

    public double getAperture() {
        return aperture;
    }

    public void setAperture(double aperture) {
        this.aperture = aperture;
    }

}
