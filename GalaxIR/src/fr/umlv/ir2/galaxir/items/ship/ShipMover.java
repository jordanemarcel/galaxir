package fr.umlv.ir2.galaxir.items.ship;

import java.util.Iterator;
import java.util.LinkedList;

import fr.umlv.ir2.galaxir.core.AuthoritativeItemManager;
import fr.umlv.ir2.galaxir.items.Explosion;

public class ShipMover {
	private final AuthoritativeItemManager authoritativeItemManager;
	//private LinkedList<Ship> shipList;
	//private LinkedList<GalaxyItem> itemToDelete;
	//private LinkedList<Planet> planetList;

	public ShipMover(AuthoritativeItemManager authoritativeItemManager) {
		this.authoritativeItemManager = authoritativeItemManager;
	}

	public void update() {
		Iterator<Ship> shipIterator = authoritativeItemManager.shipIterator();
		LinkedList<Ship> shipToBeDeleted = new LinkedList<Ship>();
		while(shipIterator.hasNext()) {
			Ship ship = shipIterator.next();
			if(ship.toBeDeleted()) {
				shipToBeDeleted.add(ship);
			}
		}
		for(Ship toDeleteShip: shipToBeDeleted) {
			toDeleteShip.getOwner().addAnExplosion(toDeleteShip);
			authoritativeItemManager.removeShip(toDeleteShip);
		}

		Iterator<Explosion> explosionIterator = authoritativeItemManager.explosionIterator();
		LinkedList<Explosion> explosionToBeDeleted = new LinkedList<Explosion>();
		while(explosionIterator.hasNext()) {
			Explosion explosion = explosionIterator.next();
			if(explosion.toBeDeleted()) {
				explosionToBeDeleted.add(explosion);
			}
		}
		for(Explosion toDeleteExplosion: explosionToBeDeleted) {
			authoritativeItemManager.removeExplosion(toDeleteExplosion);
		}
	}

	public void run() {
		update();
		Iterator<Ship> shipIterator = authoritativeItemManager.shipIterator();
		while(shipIterator.hasNext()) {
			Ship ship = shipIterator.next();
			ship.move(authoritativeItemManager.planetIterator());
		}
	}
}
