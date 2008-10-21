package fr.umlv.ir2.galaxir;


import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class ShipMover implements Runnable {
	private Collection<? extends GalaxyItem> itemList;
	private LinkedList<Ship> shipList;
	private LinkedList<GalaxyItem> itemToDelete;
	private LinkedList<Planet> planetList;
	
	public ShipMover(Collection<? extends GalaxyItem> itemList) {
		this.itemList = itemList;
		shipList = new LinkedList<Ship>();
		itemToDelete = new LinkedList<GalaxyItem>();
		planetList = new LinkedList<Planet>();
		update();
	}
	
	public void update() {
		shipList.clear();
		planetList.clear();
		for (GalaxyItem item : itemList) {
			if(item instanceof Ship) {
				Ship s = (Ship)item;
				if(s.toBeDeleted())
					itemToDelete.add(item);
				else
					shipList.add(s);
			}
			else if(item instanceof Planet)
				planetList.add((Planet)item);
		}
		for(GalaxyItem item: itemToDelete) {
			itemList.remove(item);
		}
		itemToDelete.clear();
	}
	
	@Override
	public void run() {
		update();
		for (Ship s : shipList){
			s.move(planetList);
		}
	}
}
