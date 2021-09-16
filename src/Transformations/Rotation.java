package Transformations;

import Transformations.Transformation.Type;

public class Rotation extends Transformation{
	
	private double angle;
	private int xOffset;
	private int yOffset;
	
	public Rotation(double angle, int xOff, int yOff) {
		super();
		this.angle = angle;	
		this.xOffset = xOff;
		this.yOffset = yOff;
		super.setType(Type.Rotation);
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


	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
		updateMatrix();
	}	
	
	public void updateMatrix(){	
		double invTranslation[][] = {{1,0,-getxOffset()},{0,1,-getyOffset()},{0,0,1}};
		
		double Translation[][] = {{1,0,getxOffset()},{0,1,getyOffset()},{0,0,1}};
		
		double Rotation[][] = {{Math.cos(Math.toRadians(getAngle())),-Math.sin(Math.toRadians(getAngle())),0},{Math.sin(Math.toRadians(getAngle())),Math.cos(Math.toRadians(getAngle())),0},{0,0,1}};
		
		double result[][] = new double[3][3];
		
		result =  multiply3x3Matrices( Translation,multiply3x3Matrices(Rotation,invTranslation));
		 super.setTransformationMatrix(result);
	}
}