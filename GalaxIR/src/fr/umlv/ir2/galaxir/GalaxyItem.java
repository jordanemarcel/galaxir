package fr.umlv.ir2.galaxir;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface GalaxyItem {
	public abstract void draw(Graphics2D g);
	public abstract boolean contains(Point2D p);
	public abstract void selected(Player player);
	public abstract void unselected(Player player);
	public abstract void moveShipTowards(Planet p, int percentage, ArrayList<GalaxyItem> itemList);
	public abstract void selectAndAdd(Player player);
	public abstract void unselectAndRemove(Player player);
}
