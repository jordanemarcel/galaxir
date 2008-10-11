package fr.umlv.galaxir.testClement;


import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
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
		return selection.intersects(item.getRectangleArea());
	}

	@Override
	public boolean isContained(Shape selection, GalaxyItem item) {
		return selection.contains(item.getRectangleArea());
	}

}
