package fr.umlv.ir2.galaxir;

import java.awt.geom.Point2D;

public class Trigo {

	public static Point2D.Double findUpperPoint(Point2D.Double p) {
		double x = p.getX();
		double y = 0;
		return new Point2D.Double(x, y);
	}

	public static double vectorLength(double[] v)
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

	public static double findAngle (Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
		double[] v0 = Trigo.createVector(p1, p2);
		double[] v1 = Trigo.createVector(p1, p3);
		double dotProduct = Trigo.scalarProduct(v0, v1);
		double length1 = Trigo.vectorLength(v0);
		double length2 = Trigo.vectorLength(v1);
		double denominator = length1 * length2;
		double product = denominator != 0.0 ? dotProduct / denominator : 0.0;
		double angle = Math.acos(product);
		return angle;
	}

}
