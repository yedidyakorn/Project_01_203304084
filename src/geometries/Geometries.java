package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
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
        geometries = new ArrayList<>();
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
        for (Intersectable item : geometries) {
            this.geometries.add(item);
        }
    }


    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = null;
        for (Intersectable item : geometries) {
            List<Point3D> temp = item.findIntersections(ray);
            if (temp != null){
                if(intersections==null)
                    intersections=new ArrayList<Point3D>();
                intersections.addAll(temp);
            }
        }
        return intersections;
    }

    int arr[]={1,2,3};

}
