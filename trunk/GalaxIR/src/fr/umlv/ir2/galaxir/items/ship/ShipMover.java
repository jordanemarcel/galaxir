package fr.umlv.ir2.galaxir.items.ship;

import java.util.Collection;
import java.util.LinkedList;

import fr.umlv.ir2.galaxir.items.Explosion;
import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.ir2.galaxir.items.Planet;

public class ShipMover {
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
			else if(item instanceof Explosion) {
				Explosion e = (Explosion)item;
				if(e.toBeDeleted())
					itemToDelete.add(item);
			}
		}
		for(GalaxyItem item: itemToDelete) {
			if(item instanceof Ship) {
				Ship s = (Ship)item;
				s.getOwner().addAnExplosion(s);
			}
			itemList.remove(item);
		}
		itemToDelete.clear();
	}
	
	public void run() {
		update();
		for (Ship s : shipList){
			s.move(planetList);
		}
	}
}
