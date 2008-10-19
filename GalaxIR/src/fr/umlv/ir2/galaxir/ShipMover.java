package fr.umlv.ir2.galaxir;


import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class ShipMover implements Runnable {

	private LinkedList<Ship> shipList;
	private LinkedList<Planet> planetList;
	public static Point2D destination;
	
	public ShipMover(Collection<? extends GalaxyItem> itemList) {
		shipList = new LinkedList<Ship>();
		planetList = new LinkedList<Planet>();
		for (GalaxyItem item : itemList){
			if(item instanceof Ship)
				shipList.add( (Ship) item );
			else if(item instanceof Planet)
				planetList.add((Planet)item);
		}
	}
	@Override
	public void run() {
		for (Ship s : shipList){
			s.move(planetList);
		}
	}
}
