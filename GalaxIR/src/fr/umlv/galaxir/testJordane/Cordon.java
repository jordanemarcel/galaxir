package fr.umlv.galaxir.testJordane;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Cordon implements GalaxyItem {
	private final Point2D location;
	private final Point2D destination;
	
	public Cordon(Point2D src, Point2D dst) {
		location = src;
		destination = dst;
	}
	@Override
	public boolean contains(Point2D p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.blue);
		g.drawLine((int)location.getX(), (int)location.getY(), (int)destination.getX(), (int)destination.getY());
		
	}

	@Override
	public Rectangle2D getRectangleArea() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selected() {
		// TODO Auto-generated method stub
		
	}

}
