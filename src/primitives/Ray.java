package primitives;


  public class Ray {

	Point3D p;
	Vector v;
	
	public Ray(Coordinate xP, Coordinate yP, Coordinate zP, Coordinate xV, Coordinate yV, Coordinate zV )
		{
			 p = new  Point3D(xP,yP,zP);
		        
			 Vector tempV= new Vector(xV, yV, zV);    
		        
		         if(tempV.length() != 1) {
		        	 throw new IllegalArgumentException("a vector Whose lengh is different from 1 is illegal");
		         }
		         v = tempV;
		 }
		
	
	public Point3D getP(){
		return p;
	}
	
	public Vector getV(){
		return v;
	}
	
	public Ray (Ray other){
        this.p=other.p;
        this.v=other.v;
       
    }
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Ray Ray = (Ray) o;
	        return Objects.equals(p, Ray.p) &&
	                Objects.equals(v, Ray.v);
	    }
	 @Override
	    public String toString(){
	        return ("point: "+p + " vector:"  +v +"\n");
	    }
}

