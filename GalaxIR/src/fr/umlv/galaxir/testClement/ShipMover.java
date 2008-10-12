package fr.umlv.galaxir.testClement;


import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class ShipMover implements Runnable {

	private LinkedList<Ship> shipList;
	public static Point2D destination;

	public ShipMover(Collection<? extends GalaxyItem> itemList) {
		shipList = new LinkedList<Ship>();
		for (GalaxyItem item : itemList){
			if(item instanceof Ship)
				shipList.add( (Ship) item );
		}
	}
	@Override
	public void run() {
		for (Ship s : shipList){
			s.move();
		}
	}
}
