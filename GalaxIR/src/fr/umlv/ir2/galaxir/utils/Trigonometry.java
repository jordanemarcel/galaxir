package fr.umlv.ir2.galaxir.utils;

import java.awt.geom.Point2D;

/**
 * The Trigonometry class implements static methods used for
 * compute geometric formulas and so.
 * @author Clément Lebreton, Jordane Marcel
 *
 */
public class Trigonometry {

	/**
	 * This method return a point that is, compared to the given {@link Point2D} point,
	 * at the same x coordinate but at a y coordinate equals to 0.
	 * @param point - a given point
	 * @return The upper point
	 */
	public static Point2D.Double findUpperPoint(Point2D.Double point) {
		double x = point.getX();
		double y = 0;
		return new Point2D.Double(x, y);
	}
	
	/**
	 * This method computes the length of a vector.
	 * @param v - a given vector
	 * @return The vector length
	 */
	public static double vectorLength(double[] v)
	{
		return Math.sqrt(v[0]*v[0] + v[1]*v[1]);
	}
	
	/**
	 * This method computes the scalar product of two vectors.
	 * @param v0 - the fist vector
	 * @param v1 - the second vector
	 * @return The scalar product
	 */
	public static double scalarProduct(double[] v0, double[] v1) {
		return v0[0] * v1[0] + v0[1] * v1[1];
	}
	
	/**
	 * This method creates a vector from two {@link Point2D} points.
	 * @param p1 - the first point
	 * @param p2 - the second point
	 * @return The vector
	 */
	public static double[] createVector(Point2D.Double p1, Point2D.Double p2) {
		double v[] = {(p2.getX() - p1.getX()), (p2.getY() - p1.getY())};
		return v;
	}

	/**
	 * This method finds the angle between 3 points.
	 * @param anchor - {@link Point2D} where is located the angle to find
	 * @param p1 - The first point
	 * @param p2 - the second point
	 * @return The angle
	 */
	public static double findAngle (Point2D.Double anchor, Point2D.Double p1, Point2D.Double p2) {
		double[] v0 = Trigonometry.createVector(anchor, p1);
		double[] v1 = Trigonometry.createVector(anchor, p2);
		double dotProduct = Trigonometry.scalarProduct(v0, v1);
		double length1 = Trigonometry.vectorLength(v0);
		double length2 = Trigonometry.vectorLength(v1);
		double denominator = length1 * length2;
		double product = denominator != 0.0 ? dotProduct / denominator : 0.0;
		double angle = Math.acos(product);
		return angle;
	}

}
