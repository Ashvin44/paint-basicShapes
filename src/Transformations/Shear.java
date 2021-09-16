package Transformations;

import Transformations.Transformation.Type;

public class Shear extends Transformation{
	
	private double x;
	private double y;
	private int xOffset;
	private int yOffset;
	
	
	
	
	public Shear(double x, double y, int xOffset, int yOffset) {
		super();
		this.x = x;
		this.y = y;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		super.setType(Type.Shear);
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
	
	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
		updateMatrix();
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
		updateMatrix();
	}
	
	
	public void updateMatrix(){			
		double invTranslation[][] = {{1,0,-getxOffset()},{0,1,-getyOffset()},{0,0,1}};
		
		double Translation[][] = {{1,0,getxOffset()},{0,1,getyOffset()},{0,0,1}};
		
		double Shear[][] = {{1, getX(), 0},{ getY(), 1, 0},{0,0,1}};
		
		double result[][] = new double[3][3];
		
		result =  multiply3x3Matrices( Translation,multiply3x3Matrices(Shear,invTranslation));
		 super.setTransformationMatrix(result);
	}
	
}