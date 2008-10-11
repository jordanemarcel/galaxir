package fr.umlv.galaxir.testJordane;

import java.awt.geom.Point2D;
import java.util.Iterator;

public class ShipMover implements Runnable {

	private TriangleItem item;
	public static Point2D destination;

	public ShipMover(Iterator<? extends Drawable> it) {
		Drawable d;
		while(it.hasNext()) {
			if((d=it.next()) instanceof TriangleItem)
				this.item = (TriangleItem)d;
		}
	}

	@Override
	public void run() {
		Point2D center = item.getLocation();
		item.editCenter(center.getX()+5, center.getY()+5);
		if(center.getX()>640)
			item.editCenter(0, center.getY());
		if(center.getY()>480)
			item.editCenter(center.getX(), 0);
	}

}
