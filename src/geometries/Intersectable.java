package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * interface that will be used in all Geometry shapes
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */

public interface Intersectable {
    /**
     * function that gets a ray and checks the Intersections of the ray with the shape or shapes
     *
     * @param ray- a ray from @primitives.Ray
     * @return a List of 3D Points with the values of all the Intersections points of the ray and the shape or shapes.
     * if there are no Intersections points, the function returns null
     */

    List<GeoPoint> findIntersections(Ray ray);

    /**
     * GeoPoint class a point on a shape
     * geometry - a geometry shape
     * point - a point on the shape
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * ctor for GeoPoint
         * @param geometry - a geometry shape
         * @param point - a point on the shape
         */
        public GeoPoint(Geometry geometry,Point3D point){
            this.geometry=geometry;
            this.point=point;
        }



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) &&
                    Objects.equals(point, geoPoint.point);
        }
    }
}
