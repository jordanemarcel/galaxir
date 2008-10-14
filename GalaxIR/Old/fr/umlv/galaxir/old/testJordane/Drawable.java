package fr.umlv.galaxir.old.testJordane;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;

public interface Drawable {

	public Color getColor();

	public int getWidth();

	public Point2D getLocation();

	public void swap();

	public void draw(Graphics2D g);
	
	public boolean contains(Point2D location);
	
	public void setOver(boolean b);

}
