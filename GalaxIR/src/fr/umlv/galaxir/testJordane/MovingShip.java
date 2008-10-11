package fr.umlv.galaxir.testJordane;

import java.awt.geom.Point2D;

public class MovingShip implements Runnable {

	private final TriangleItem item;
	public static Point2D destination;
	
	public MovingShip(TriangleItem item) {
		this.item = item;
	}
	
	@Override
	public void run() {
		for(;;) {
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Point2D center = item.getLocation();
			item.editCenter(center.getX()+5, center.getY()+5);
			if(center.getX()>640)
				item.editCenter(0, center.getY());
			if(center.getY()>480)
				item.editCenter(center.getX(), 0);
		}
		
	}

}
