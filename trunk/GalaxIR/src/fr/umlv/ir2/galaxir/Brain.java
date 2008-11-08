package fr.umlv.ir2.galaxir;

import java.util.Iterator;


public class Brain {
	private AuthoritativeItemManager authoritativeItemManager;
	private Player player;

	public Brain(AuthoritativeItemManager authoritativeItemManager, Player p) {
		this.authoritativeItemManager = authoritativeItemManager;
		this.player = p;
	}

	public void run() {
		int min = Integer.MAX_VALUE;
		Planet thisOne = null;
		Iterator<Planet> planetListIterator = authoritativeItemManager.planetIterator();
		while(planetListIterator.hasNext()) {
			Planet planet = planetListIterator.next();
			if(planet.getNbShip()<min && planet.getOwner()!=player) {
				min = planet.getNbShip();
				thisOne = planet;
			}
		}
		planetListIterator = authoritativeItemManager.planetIterator();
		while(planetListIterator.hasNext()) {
			Planet planet = planetListIterator.next();
			if(planet.getOwner()==player) {
				planet.moveShipTowards(thisOne, 50);
			}
		}
		Iterator<Ship> shipListIterator = authoritativeItemManager.shipIterator();
		while(shipListIterator.hasNext()) {
			Ship ship = shipListIterator.next();
			if(ship.getOwner()==player) {
				ship.moveShipTowards(thisOne);
			}
		}
	}
}
