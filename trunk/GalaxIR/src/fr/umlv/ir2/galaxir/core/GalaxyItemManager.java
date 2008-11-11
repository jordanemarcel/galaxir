package fr.umlv.ir2.galaxir.core;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;

import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.remix.ItemManager;

public class GalaxyItemManager implements ItemManager<GalaxyItem> {

	@Override
	public boolean contains(Point2D location , GalaxyItem item) {
		return item.contains(location);
	}

	@Override
	public void draw(Graphics2D g, GalaxyItem item) {
		item.draw(g);
	}

	@Override
	public boolean intersects(Shape selection, GalaxyItem item) {
		return false;
	}

	@Override
	public boolean isContained(Shape selection, GalaxyItem item) {
		return false;
	}

}
