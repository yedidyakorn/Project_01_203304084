package geometries;

import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * object that includes a few Geometry shapes
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */

public class Geometries implements Intersectable {

    /**
     * a arrayList that includes a few Geometry shapes
     */
    private List<Intersectable> geometries;

    /**
     * ctor that builds a empty list
     */
    public Geometries() {
        geometries = new LinkedList<>();
    }

    /**
     * ctor that builds a list from a given array
     *
     * @param geometries- a group of Geometry shapes given as a array
     */
    public Geometries(Intersectable... geometries) {
        this.geometries = List.of(geometries);
    }

    /**
     * adds a shape (or more) to the list
     *
     * @param geometries - a Geometry shape or a group of shapes given as a array
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable item : geometries) {
            List<GeoPoint> temp = item.findIntersections(ray);
            if (temp != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(temp);
            }
        }
        return intersections;
    }

}
