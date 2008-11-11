package fr.umlv.ir2.galaxir.items;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public interface GalaxyItem {
	public abstract void draw(Graphics2D g);
	public abstract boolean contains(Point2D p);
}
