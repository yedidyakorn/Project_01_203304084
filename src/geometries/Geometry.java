package geometries;

import primitives.Point3D;
import primitives.Vector;

public interface Geometry {
    
        public Vector getNormal(Point3D point){
        Vector temp = new Point3D(point.z, 0, -point.x);
        return temp;
    }
}
