package fr.umlv.ir2.galaxir;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Trigo {
	private static final ArrayList<Double> cos = new ArrayList<Double>();
	private static final ArrayList<Double> sin = new ArrayList<Double>();
	static {
		for (int i = 0; i <= 360; i++){
			cos.add(i, Math.cos(i));
			sin.add(i, Math.sin(i));
		}
	}
	public static double cos(int angle){
		return cos.get(angle);
	}
	public static double sin(int angle){
		return sin.get(angle);
	}
	public static Point2D.Double findUpperPoint(Point2D.Double p) {
		double x = p.getX();
		double y = 0;
		return new Point2D.Double(x, y);
	}

	public static double length (double[] v)
	{
		return Math.sqrt(v[0]*v[0] + v[1]*v[1]);
	}
	
	public static double scalarProduct(double[] v0, double[] v1) {
		return v0[0] * v1[0] + v0[1] * v1[1];
	}
	
	public static double[] createVector(Point2D.Double p1, Point2D.Double p2) {
		double v[] = {(p2.getX() - p1.getX()), (p2.getY() - p1.getY())};
		return v;
	}

	public static double computeAngle (Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
		double[] v0 = Trigo.createVector(p1, p2);
		double[] v1 = Trigo.createVector(p1, p3);

		double dotProduct = Trigo.scalarProduct(v0, v1);

		double length1 = Trigo.length(v0);
		double length2 = Trigo.length(v1);

		double denominator = length1 * length2;

		double product = denominator != 0.0 ? dotProduct / denominator : 0.0;

		double angle = Math.acos(product);

		return angle;
	}
}
