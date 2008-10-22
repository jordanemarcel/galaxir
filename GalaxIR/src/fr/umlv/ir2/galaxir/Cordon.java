package fr.umlv.ir2.galaxir;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

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

	/*@Override
	public Rectangle2D getRectangleArea() {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public void selected(Player player) {
		// TODO Auto-generated method stub
		
	}
	public void unselected(Player player) {
	}
	
	public void moveShipTowards(Planet p, int percentage, ArrayList<GalaxyItem> itemList) {

	}
	public void selectAndAdd(Player player) {
	}
	public void unselectAndRemove(Player player) {
	}

}
