package Transformations;


public class Translation extends Transformation{
	
	private double x;
	private double y;
	
	
	
	public Translation(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		super.setType(Type.Translation);
		updateMatrix();
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
		updateMatrix();
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
		updateMatrix();
	}
	
	
	public void updateMatrix(){			
		double a[][] = {{1,0,getX()},{0,1,-getY()},{0,0,1}};
		 super.setTransformationMatrix(a);
	}
	
}
