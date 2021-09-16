import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class SutherLand_HodgmanClipping {

	public static List<double[]> subject, clipper, result;
	
	public static void setClipper(int minX, int maxX, int minY, int maxY) {
		
		double[][] clipPoints = {{minX, minY}, {maxX, minY}, {maxX, maxY}, {minX, maxY}};	
		clipper = new ArrayList<>(Arrays.asList(clipPoints));
    }
	
	
	public static void setPoints(ArrayList<Point2D> p) {
		
		double[][] subjPoints = new double[p.size()][2];
				
		for (int i = 0 ; i < p.size() ; i++)
		{
			subjPoints[i][0] = p.get(i).getX();
			subjPoints[i][1] = p.get(i).getY();
		}
				
		subject = new ArrayList<>(Arrays.asList(subjPoints));
		result  = new ArrayList<>(subject);
	}
	
	public static void clipPolygon() {
        int len = clipper.size();
        for (int i = 0; i < len; i++) {
 
            int len2 = result.size();
            List<double[]> input = result;
            result = new ArrayList<>(len2);
 
            double[] A = clipper.get((i + len - 1) % len);
            double[] B = clipper.get(i);
 
            for (int j = 0; j < len2; j++) {
 
                double[] P = input.get((j + len2 - 1) % len2);
                double[] Q = input.get(j);
 
                if (isInside(A, B, Q)) {
                    if (!isInside(A, B, P))
                        result.add(intersection(A, B, P, Q));
                    result.add(Q);
                } else if (isInside(A, B, P))
                    result.add(intersection(A, B, P, Q));
            }
        }
    }
 
    private static boolean isInside(double[] a, double[] b, double[] c) {
        return (a[0] - c[0]) * (b[1] - c[1]) > (a[1] - c[1]) * (b[0] - c[0]);
    }
 
    private static double[] intersection(double[] a, double[] b, double[] p, double[] q) {
        double A1 = b[1] - a[1];
        double B1 = a[0] - b[0];
        double C1 = A1 * a[0] + B1 * a[1];
 
        double A2 = q[1] - p[1];
        double B2 = p[0] - q[0];
        double C2 = A2 * p[0] + B2 * p[1];
 
        double det = A1 * B2 - A2 * B1;
        double x = (B2 * C1 - B1 * C2) / det;
        double y = (A1 * C2 - A2 * C1) / det;
 
        return new double[]{x, y};
    }
	
}
