package primitives;

public class Vector {

    Point3D endPoint;

    public Vector(Coordinate x,Coordinate y,Coordinate z){
        Point3D temp= new Point3D(x,y,z);
        if (temp.equals(temp.Zero())) {
            throw new IllegalArgumentException("a vector from zero point to zero point is illegal");
        }
        endPoint=temp;
    }

    public Vector (double x, double y, double z){
        Point3D temp= new Point3D(x,y,z);
        if (temp.equals(temp.Zero())) {
            throw new IllegalArgumentException("a vector from zero point to zero point is illegal");
        }
        endPoint=temp;
    }

    public Vector (Point3D point){
        if (point.equals(point.Zero())) {
            throw new IllegalArgumentException("a vector from zero point to zero point is illegal");
        }
        endPoint=new Point3D(point);
    }

    public Vector(Vector vec){
        endPoint=new Point3D(vec.getEndPoint());
    }

    public Vector add(Vector other){
        return new Vector(other.getEndPoint().add(this));
    }

    public Vector subtract(Vector other){
        return new Vector(this.subtract(other));
    }

    public Vector scale (double num){
        double x=this.endPoint.getX().get()*num;
        double y=this.endPoint.getY().get()*num;
        double z=this.endPoint.getZ().get()*num;
        return new Vector(x,y,z);
    }

    public double dotProduct(Vector vec){
        double x=this.endPoint.getX().get()*vec.getEndPoint().getX().get();
        double y=this.endPoint.getY().get()*vec.getEndPoint().getY().get();
        double z=this.endPoint.getZ().get()*vec.getEndPoint().getZ().get();
        return x+y+z;
    }

    public Vector crossProduct (Vector vec){
        double x=this.endPoint.getY().get()*vec.getEndPoint().getZ().get()-this.endPoint.getZ().get()*vec.getEndPoint().getY().get();
        double y=this.endPoint.getZ().get()*vec.getEndPoint().getX().get()-this.endPoint.getX().get()*vec.getEndPoint().getZ().get();
        double z=this.endPoint.getX().get()*vec.getEndPoint().getY().get()-this.endPoint.getY().get()*vec.getEndPoint().getX().get();
        return new Vector(x,y,z);
    }

    public double lengthSquared (){
        return this.getEndPoint().distanceSquared(this.getEndPoint().Zero());
    }

    public double length (){
        return Math.sqrt(this.lengthSquared());
    }

    public Vector normalize (){
        double x=this.endPoint.getX().get()/this.length();
        double y=this.endPoint.getY().get()/this.length();
        double z=this.endPoint.getZ().get()/this.length();
        endPoint=new Point3D(x,y,z);
        return this;
    }

    public Vector normalized(){
        return new Vector(this.normalize()) ;
    }


    public Point3D getEndPoint() {
        return endPoint;
    }
}
