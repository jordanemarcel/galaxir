package fr.umlv.ir2.galaxir;


import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public interface GalaxyItem {
	public abstract void draw(Graphics2D g);
	public abstract boolean contains(Point2D p);
	//public abstract Rectangle2D getRectangleArea();
	public abstract void selected(Player player);
}
