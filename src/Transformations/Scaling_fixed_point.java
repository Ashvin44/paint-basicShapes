package Transformations;

import Transformations.Transformation.Type;

public class Scaling_fixed_point extends Transformation{
	
	private double scaleX;
	private double scaleY;
	private int xOffset;
	private int yOffset;
	
	
	public Scaling_fixed_point(double scaleX, double scaleY, int xOff, int yOff) {
		super();
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.xOffset = xOff;
		this.yOffset = yOff;
		super.setType(Type.Scaling_fixed_point);
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

	
	public double getScaleX() {
		return scaleX;
	}
	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
		updateMatrix();
	}
	public double getScaleY() {
		return scaleY;
	}
	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
		updateMatrix();
	}
	
	public void updateMatrix(){			
		
		double invTranslation[][] = {{1,0,-getxOffset()},{0,1,-getyOffset()},{0,0,1}};
		
		double Translation[][] = {{1,0,getxOffset()},{0,1,getyOffset()},{0,0,1}};
		
		double Scaling[][] = {{getScaleX(),0,0},{0,getScaleY(),0},{0,0,1}};
		
		double result[][] = new double[3][3];
		
		result =  multiply3x3Matrices( Translation,multiply3x3Matrices(Scaling,invTranslation));
		 super.setTransformationMatrix(result);
	}
	
}

