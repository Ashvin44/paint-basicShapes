package Transformations;




// All transformation has a matrix which will be multiplied to the x and y co-ordinate of the shapes to get the final shape which the user wants

public abstract class Transformation {

	//3x3 matrix for 2d shapes
	private double transformationMatrix[][] = new double[3][3];
	private Type type;
	
	//getters for Type
	public Type getType() {
		return type;
	}
	//setter for type
	public void setType(Type type) {
		this.type = type;
	}

	//getters for the 3x3 matrix
	public double[][] getTransformationMatrix() {
		return transformationMatrix;
	}
	//setters for the 3x3 matrix
	public void setTransformationMatrix(double[][] transformationMatrix) {
		this.transformationMatrix = transformationMatrix;
	}
	
	
	// The type of transformations
	public enum Type {
        Translation, Rotation, Rotation_fixed_point, Scaling, Scaling_fixed_point, Reflection, Shear
     }

	
	//Function to Multiply two 3x3 matrices only, and returns the result 3x3 matrix
	public static double[][] multiply3x3Matrices(double leftMatrix[][], double rightMatrix[][]){
		
		double result[][] = new double[3][3];
		

		for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    result[i][j] += leftMatrix[i][k] * rightMatrix[k][j];
                }
            }
        }
		return result;
	}

	
	//Function to Multiply a 3x3 matrix to a 3x1 matrix only, and returns the result 3x3 matrix
		public static double[][] multiply3x3by3x1Matrices(double leftMatrix[][], double rightMatrix[][]){
			
			double result[][] = new double[3][1];
			

			for(int i = 0; i < 3; i++) {
	            for (int j = 0; j < 1; j++) {
	                for (int k = 0; k < 3; k++) {
	                    result[i][j] += leftMatrix[i][k] * rightMatrix[k][j];
	                }
	            }
	        }
			return result;
		}
	
		
	
		
		
}



